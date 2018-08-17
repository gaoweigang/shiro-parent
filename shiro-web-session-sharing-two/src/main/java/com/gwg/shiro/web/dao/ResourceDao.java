package com.gwg.shiro.web.dao;

import com.gwg.shiro.web.exception.BusinessException;
import com.gwg.shiro.web.model.Resource;

import java.util.List;

public interface ResourceDao {

    public List<Resource> queryResourceByUserid(String userid) throws BusinessException;

    public List<Resource> queryAllResources() throws BusinessException;

    public List<Resource> queryResourceListByIds(List<Long> idList) throws BusinessException;

}
