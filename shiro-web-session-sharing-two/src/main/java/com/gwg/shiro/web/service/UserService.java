package com.gwg.shiro.web.service;

import com.gwg.shiro.web.config.AuthUser;
import com.gwg.shiro.web.dto.UserDto;
import com.gwg.shiro.web.model.User;
import com.gwg.shiro.web.vo.UserVo;

import java.util.List;

/**
 * Created by
 */
public interface UserService{


    /**
     * 根据userid得到用户信息
     * @return
     */
    public User getUserByUserid(String userid);

    /**
     * 根据用户名得到用户认证信息
     */
    public AuthUser getAuthUserByUserid(String userid);


    /**
     * 分页查询-根据条件查询用户信息
     */
    public List<UserVo> queryUserInfoByLimit(UserDto dto);

    /**
     * 新增-用户
     */
    public void addUser(UserDto dto);

    /**
     * 根据userid查询用户信息
     */
    public UserVo queryUserInfoById(UserDto dto);




}
