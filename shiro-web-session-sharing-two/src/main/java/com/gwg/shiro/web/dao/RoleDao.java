package com.gwg.shiro.web.dao;

import com.gwg.shiro.web.dto.RoleDto;

public interface RoleDao {

    /**
     * 添加角色
     */
    public void addRole(RoleDto dto) throws Exception;


    /**
     * 更新角色
     */
    public void updateRoleById(RoleDto dto) throws Exception;


    /**
     * 根据条件查询用户角色
     */
    public void queryRoleById(RoleDto dto) throws Exception;


    /**
     * 删除用户角色
     */
    public void delRoleById(RoleDto dto) throws Exception;

}
