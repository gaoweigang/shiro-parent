package com.gwg.shiro.web.dao.impl;

import com.gwg.shiro.web.dao.UserDao;
import com.gwg.shiro.web.mapper.UserMapper;
import com.gwg.shiro.web.model.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByUserid(String userid) {
        if(StringUtils.isEmpty(userid)){
            return null;
        }
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid", userid);
        List<User> userList = userMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(userList)){
            return null;
        }

        return userList.get(0);
    }
}
