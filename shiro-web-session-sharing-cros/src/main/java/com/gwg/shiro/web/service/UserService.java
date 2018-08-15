package com.gwg.shiro.web.service;

import com.gwg.shiro.web.config.AuthUser;
import com.gwg.shiro.web.model.User;

/**
 * Created by
 */
public interface UserService{

    /**
     * 根据用户名得到用户信息
     * @param userid
     * @return
     */
    public User getUserByUserid(String userid);


    /**
     * 根据用户名得到用户认证信息
     */
    public AuthUser getAuthUserByUserid(String userid);

}
