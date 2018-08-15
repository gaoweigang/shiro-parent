package com.gwg.shiro.web.dao;

import com.gwg.shiro.web.model.UserAccount;

public interface UserAccountDao {

    public UserAccount queryUserAccountByUserid(String userid);

}
