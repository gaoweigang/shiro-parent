package com.gwg.shiro.web.dao;

import com.gwg.shiro.web.model.UserAccount;

public interface AccountDao {

    public UserAccount queryUserAccountByUserid(String userid);

}
