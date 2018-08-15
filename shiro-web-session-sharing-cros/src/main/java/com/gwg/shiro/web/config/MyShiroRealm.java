package com.gwg.shiro.web.config;


import com.gwg.shiro.web.model.Resource;
import com.gwg.shiro.web.model.User;
import com.gwg.shiro.web.service.ResourceService;
import com.gwg.shiro.web.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyShiroRealm.class);

	@Autowired
	private UserService userService;

	@Autowired
	private ResourceService resourceService;

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();//User{id=1, username='admin', password='3ef7164d1f6167cb9f2658c07d3c2f0a', enable=1}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getUserid());
		List<Resource> resources = resourceService.getResourceByUserid(user.getUserid());
		// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for (Resource resource : resources) {
			LOGGER.info("用户：{}，有权限访问的功能：{}",user.getUserid(), resource.getResUrl());
			info.addStringPermission(resource.getResUrl());
		}
		return info;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取用户的输入的账号.
		String username = (String) token.getPrincipal();
		AuthUser authUser = userService.getAuthUserByUserid(username);
		if (authUser == null)
			throw new UnknownAccountException();
		if (!"1".equals(authUser.getValidflag())) {
			throw new LockedAccountException(); // 帐号锁定
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(authUser, //用户
				authUser.getPassword(), //密码
				ByteSource.Util.bytes(username), getName() //realm name
		);
		// 当验证都通过后，把用户信息放在session里
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("userSession", authUser);
		LOGGER.info("登陆用户sessionId:{}", session.getId());
		session.setAttribute("userSessionId", session.getId());
		return authenticationInfo;
	}

}