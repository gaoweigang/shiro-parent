package com.gwg.shiro.web.dao;

import com.gwg.shiro.web.model.User;

public interface UserDao {

    public User queryUserByUserid(String userid);
}
