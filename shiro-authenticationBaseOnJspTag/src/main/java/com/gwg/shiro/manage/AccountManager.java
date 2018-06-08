package com.gwg.shiro.manage;

import com.gwg.shiro.model.User;

public class AccountManager {

    /**
     * 根据用户名从数据库中查询用户信息
     * @param username
     * @return
     */
    public User findUserByUserName(String username){
        if(username == null || "".equals(username)){
            return null;
        }

        //否则根据用户名查询用户信息
        User user = new User("zhao", "111");
        return user;
    }
}
