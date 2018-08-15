package com.gwg.shiro.web.dao;

import com.gwg.shiro.web.model.Resource;

import java.util.List;

public interface ResourceDao {

    public List<Resource> queryResourceByUserid(String userid);

}
