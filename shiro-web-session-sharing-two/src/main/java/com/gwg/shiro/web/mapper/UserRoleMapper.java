package com.gwg.shiro.web.mapper;

import com.gwg.shiro.web.model.UserRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserRoleMapper extends Mapper<UserRole> {

    public List<String> queryRoleListByUserid(@Param("userid") String userid);
}