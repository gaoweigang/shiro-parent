package com.gwg.shiro.web.service;

import com.gwg.shiro.web.dto.RoleDto;
import com.gwg.shiro.web.model.Role;

import java.util.List;

public interface RoleService {

    /**
     * 新增角色
     */
    public void addRole(RoleDto dto);

    /**
     * 更新角色
     */
    public void updateRole(RoleDto dto);

    /**
     * 批量获取
     * @return
     */
    List<Role> queryRoleList(RoleDto dto);


    /**
     * 删除角色
     */
    void delRole(RoleDto dto);


}
