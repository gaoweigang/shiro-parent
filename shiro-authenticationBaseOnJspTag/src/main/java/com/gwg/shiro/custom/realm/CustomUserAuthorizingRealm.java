package com.gwg.shiro.custom.realm;

import com.gwg.shiro.manage.AccountManager;
import com.gwg.shiro.manage.PermissionManager;
import com.gwg.shiro.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.*;
import org.apache.shiro.cas.CasRealm;

import java.util.Iterator;
import java.util.List;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomUserAuthorizingRealm extends AuthorizingRealm  {

    private static final Log log = LogFactory.getLog(CustomUserAuthorizingRealm.class);
    // 用于获取用户信息及用户权限信息的业务接口
    private PermissionManager permissionManager = new PermissionManager();

    private AccountManager accountManager = new AccountManager();


    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("授权开始 start ..................");
        // 权限名称
        String permissionName;
        try {
            // 查询用户授权信息
            SimpleAuthorizationInfo author = new SimpleAuthorizationInfo();
            // 查找登录用户名称
            String username = (String) principals.getPrimaryPrincipal();
            System.out.println(username);
            // 查询用户对应角色对应的资源
            List<String> lstPermission = permissionManager.queryUserPermission(username);
            log.info("从数据库中查询资源信息："+lstPermission);
            // 迭代查询
            Iterator<String> it = lstPermission.iterator();
            while (it.hasNext()) {
                permissionName = it.next().toString();
                // 把资源名称添加到用户所对于的资源集合中
                author.addStringPermission(permissionName);
            }
            return author;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        log.info("认证开始22222222 start ..................");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = accountManager.findUserByUserName(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
        } else {
            return null;
        }
    }


    public PermissionManager getPermissionManager() {
        return permissionManager;
    }

    public void setPermissionManager(PermissionManager permissionManager) {
        this.permissionManager = permissionManager;
    }
}