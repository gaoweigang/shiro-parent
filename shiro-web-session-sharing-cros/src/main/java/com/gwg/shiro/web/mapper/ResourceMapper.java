package com.gwg.shiro.web.mapper;

import com.gwg.shiro.web.model.Resource;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ResourceMapper extends Mapper<Resource> {

    public List<Resource> queryUserResourceByUserid(@Param("userid") String userid, @Param("type") String type);
}