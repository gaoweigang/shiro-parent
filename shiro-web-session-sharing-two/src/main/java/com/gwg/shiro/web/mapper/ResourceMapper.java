package com.gwg.shiro.web.mapper;

import com.gwg.shiro.web.exception.BusinessException;
import com.gwg.shiro.web.model.Resource;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ResourceMapper extends Mapper<Resource> {

    public List<Resource> queryResourceByUserid(String userid) throws BusinessException;
}