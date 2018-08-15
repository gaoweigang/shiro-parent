package com.gwg.shiro.web.service.impl;

import com.gwg.shiro.web.dao.LoginLogDao;
import com.gwg.shiro.web.model.LoginLog;
import com.gwg.shiro.web.model.User;
import com.gwg.shiro.web.service.LoginLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    public void recordLoginLog(User user) {
        if(user == null || StringUtils.isEmpty(user.getUserid())){
            return;
        }
        LoginLog log = loginLogDao.queryloginLogByUserid(user.getUserid());
        if(log == null){
            LoginLog record = new LoginLog();
            record.setLogintime(new Date());
            record.setUserid(user.getUserid());
            record.setUsername(user.getUsername());
            loginLogDao.insertLoginLog(record);
        }else{
            log.setLogintime(new Date());
            loginLogDao.updateLoginLog(log);
        }
    }
}
