package com.gwg.shiro.web.service.impl;

import com.gwg.shiro.web.config.AuthUser;
import com.gwg.shiro.web.dao.AccountDao;
import com.gwg.shiro.web.dao.UserDao;
import com.gwg.shiro.web.dto.UserDto;
import com.gwg.shiro.web.model.User;
import com.gwg.shiro.web.model.UserAccount;
import com.gwg.shiro.web.service.UserService;
import com.gwg.shiro.web.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
	private AccountDao userAccountDao;

	public User getUserByUserid(String userid) {
		return userDao.queryUserByUserid(userid);
	}

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

	@Override
	public List<UserVo> queryUserInfoByLimit(UserDto dto) {
		return null;
	}

	@Override
	public void addUser(UserDto dto) {

	}

	@Override
	public UserVo queryUserInfoById(UserDto dto) {
		return null;
	}
}
