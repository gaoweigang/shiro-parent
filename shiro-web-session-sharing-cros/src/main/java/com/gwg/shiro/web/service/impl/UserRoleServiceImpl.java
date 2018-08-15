package com.gwg.shiro.web.service.impl;

import com.gwg.shiro.web.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author
 *
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {



	/**
	 * 判断用户是不是坐席人员
	 * @param userid
	 * @return
	 */
	@Override
	public boolean isSaleStuff(String userid) {

		return false;
	}
}
