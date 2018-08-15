package com.gwg.shiro.web.service.impl;

import com.gwg.shiro.web.dao.ResourceDao;
import com.gwg.shiro.web.dao.RoleDao;
import com.gwg.shiro.web.dao.RoleResourceDao;
import com.gwg.shiro.web.dao.UserDao;
import com.gwg.shiro.web.dto.RoleDto;
import com.gwg.shiro.web.model.Role;
import com.gwg.shiro.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private RoleResourceDao roleResourceDao;
    @Autowired
    private UserDao userDao;


    @Override
    public void addRole(RoleDto dto) {

    }

    @Override
    public void updateRole(RoleDto dto) {

    }

    @Override
    public List<Role> queryRoleList(RoleDto dto) {
        return null;
    }

    @Override
    public void delRole(RoleDto dto) {

    }
}
