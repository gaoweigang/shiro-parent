package com.gwg.shiro.web.dao.impl;

import com.gwg.shiro.web.dao.LoginLogDao;
import com.gwg.shiro.web.mapper.LoginLogMapper;
import com.gwg.shiro.web.model.LoginLog;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class LoginLogDaoImpl implements LoginLogDao{

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public LoginLog queryloginLogByUserid(String userid) {
        Example example = new Example(LoginLog.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid", userid);
        List<LoginLog> logList = loginLogMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(logList)){
            return null;
        }
        return logList.get(0);

    }

    @Override
    public void insertLoginLog(LoginLog loginLog) {

        loginLogMapper.insertSelective(loginLog);

    }

    @Override
    public void updateLoginLog(LoginLog loginLog) {
        loginLogMapper.updateByPrimaryKey(loginLog);
    }
}
