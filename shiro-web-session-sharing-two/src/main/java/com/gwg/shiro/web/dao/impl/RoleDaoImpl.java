package com.gwg.shiro.web.dao.impl;

import com.gwg.shiro.web.dao.RoleDao;
import com.gwg.shiro.web.dto.RoleDto;
import com.gwg.shiro.web.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDaoImpl implements RoleDao{

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public void addRole(RoleDto dto) throws Exception {

    }

    @Override
    public void updateRoleById(RoleDto dto) throws Exception {

    }

    @Override
    public void queryRoleById(RoleDto dto) throws Exception {

    }

    @Override
    public void delRoleById(RoleDto dto) throws Exception {

    }
}
