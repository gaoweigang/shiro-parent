package com.gwg.shiro.web.service;

/**
 * 
 * @author
 *
 */
public interface UserRoleService {

	/**
	 * 判断用户是不是坐席，如果是坐席，则允许登陆系统
	 */
	public boolean isSaleStuff(String userid);

}
