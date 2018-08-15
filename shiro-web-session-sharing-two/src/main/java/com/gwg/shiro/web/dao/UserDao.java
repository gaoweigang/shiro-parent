package com.gwg.shiro.web.dao;

import com.gwg.shiro.web.dto.UserDto;
import com.gwg.shiro.web.model.User;
import com.gwg.shiro.web.vo.UserVo;

import java.util.List;

public interface UserDao {

    public User queryUserByUserid(String userid);

    /**
     * 分页查询-根据条件查询用户信息
     */
    public List<UserVo> queryUserInfoByLimit(UserDto dto) throws Exception;

    /**
     * 新增-用户
     */
    public void addUser(UserDto dto) throws Exception;

    /**
     * 根据userid查询用户信息
     */
    public UserVo queryUserInfoById(UserDto dto) throws Exception;

}
