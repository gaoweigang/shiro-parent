package com.gwg.shiro.manage;

import java.util.ArrayList;
import java.util.List;

public class PermissionManager {

    /**
     * 根据用户名从数据库中查询用户资源信息
     * @param username
     * @return
     */
    public List<String> queryUserPermission(String username){
        if(username == null || "".equals(username)){
            return null;
        }
        List<String> permissionList = new ArrayList<String>();
        permissionList.add("user:add");
        permissionList.add("user:delete");
        return permissionList;
    }
}
