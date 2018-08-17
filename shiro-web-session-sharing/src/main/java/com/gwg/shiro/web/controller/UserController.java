package com.gwg.shiro.web.controller;

import com.gwg.shiro.web.common.Result;
import com.gwg.shiro.web.common.ReturnCode;
import com.gwg.shiro.web.config.AuthUser;
import com.gwg.shiro.web.dto.LoginDto;
import com.gwg.shiro.web.dto.UserDto;
import com.gwg.shiro.web.model.User;
import com.gwg.shiro.web.service.LoginLogService;
import com.gwg.shiro.web.service.UserRoleService;
import com.gwg.shiro.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home redirection to swagger api documentation
 */
@RestController
@Api(value = "user", tags = "用户管理")
@RequestMapping({ "/user" })
@Slf4j
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserService userService;

	@Autowired
	private LoginLogService loginLogService;



	/** 
	 * 登录方法 
	 * @param userInfo 
	 * @return 
	 */
	@ApiOperation(value = "登录")
	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
	public Result<User> ajaxLogin(@RequestBody LoginDto userInfo) {
		LOGGER.info("在这判断用户是否有权限登陆系统，如果没有直接返回了");
		//验证当前登录用户是否为坐席登录
		boolean currentUserFlag = userRoleService.isSaleStuff(userInfo.getUsername());
		if (!currentUserFlag) {
			return new Result(ReturnCode.LOGIN_ERR.getMessage(),null, ReturnCode.LOGIN_ERR.getCode());
		}
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPassword());
		try {
			subject.login(token);
			Session session = SecurityUtils.getSubject().getSession();
			AuthUser authUser = (AuthUser) session.getAttribute("userSession");
			LOGGER.info("根据用户ID获取用户基本信息");
			User user = userService.getUserByUserid(authUser.getUserid());
			session.setAttribute("userSession", authUser);
			LOGGER.info("将用户的登陆信息记录到日志表 start......");
			loginLogService.recordLoginLog(user);
			return new Result<User>(true, ReturnCode.SUCCESS.getMessage(), user, ReturnCode.SUCCESS.getCode());
		} catch (IncorrectCredentialsException e) {
			log.error("IncorrectCredentialsException", e);
			return new Result(false, ReturnCode.PASSWORD_ERROR.getMessage(), null, ReturnCode.PASSWORD_ERROR.getCode());
		} catch (LockedAccountException e) {
			log.error("LockedAccountException", e);
			return new Result(false, ReturnCode.LOGIN_FAIED.getMessage(), null, ReturnCode.LOGIN_FAIED.getCode());
		} catch (AuthenticationException e) {
			log.error("AuthenticationException", e);
			return new Result(false, ReturnCode.UNKOWN_USER.getMessage(), null, ReturnCode.UNKOWN_USER.getCode());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** 
	 * 登出方法
	 * @param
	 * @return 
	 */
	@ApiOperation(value = "登出")
	@RequestMapping(value = "/ajaxLoginOut", method = RequestMethod.POST)
	public Result<?> ajaxLoginOut() {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			subject.logout();
		}
		return new Result(true, ReturnCode.SUCCESS.getMessage(), null, ReturnCode.SUCCESS.getCode());
	}

	/**
	 * 根据条件查询用户信息
	 * @param
	 * @return
	 */
	@ApiOperation(value = "根据条件查询用户信息")
	@RequestMapping(value = "/queryUserInfoById", method = RequestMethod.POST)
	public Result<?> queryUserInfoById(@RequestBody UserDto dto) {
		LOGGER.info("根据条件查询用户信息...");

		return new Result(true, ReturnCode.SUCCESS.getMessage(), null, ReturnCode.SUCCESS.getCode());
	}


	/**
	 * 该用户未授权提示
	 * @param
	 * @return
	 */
	@ApiOperation(value = "该用户未授权提示")
	@RequestMapping(value = "/unauth", method = RequestMethod.GET)
	public Result unauth() {
		LOGGER.info("该用户未授权提示...");

		return new Result(true, ReturnCode.UNAUTH.getMessage(), null, ReturnCode.UNAUTH.getCode());
	}


}
