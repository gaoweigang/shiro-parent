package com.gwg.shiro.web.service.impl;

import com.gwg.shiro.web.config.AuthUser;
import com.gwg.shiro.web.dao.UserAccountDao;
import com.gwg.shiro.web.dao.UserDao;
import com.gwg.shiro.web.model.User;
import com.gwg.shiro.web.model.UserAccount;
import com.gwg.shiro.web.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserAccountDao userAccountDao;

	@Override
	public User getUserByUserid(String userid) {
		return userDao.queryUserByUserid(userid);
	}

	@Override
	public AuthUser getAuthUserByUserid(String userid) {
		AuthUser authUser = new AuthUser();
		User user = userDao.queryUserByUserid(userid);
		if(user == null){
			return null;
		}
		UserAccount userAccount = userAccountDao.queryUserAccountByUserid(userid);
		if(userAccount ==null){
			return null;
		}
		BeanUtils.copyProperties(user, authUser);
		authUser.setPassword(userAccount.getPassword());
		return authUser;
	}

}
