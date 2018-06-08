package com.gwg.shiro.web.dao.impl;

import com.gwg.shiro.web.dao.UserRoleDao;
import com.gwg.shiro.web.mapper.UserMapper;
import com.gwg.shiro.web.mapper.UserRoleMapper;
import com.gwg.shiro.web.model.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<String> queryRoleListByUserid(String userid) {
        return userRoleMapper.queryRoleListByUserid(userid);
    }
}
