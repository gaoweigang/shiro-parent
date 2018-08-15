package com.gwg.shiro.web.dao;

import java.util.List;

public interface UserRoleDao {
    /**
     * 根据用户ID获取用户角色
     */
    public List<String> queryRoleListByUserid(String userid);
}
