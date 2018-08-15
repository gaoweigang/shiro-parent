package com.gwg.shiro.web.service.impl;

import com.gwg.shiro.web.dao.ResourceDao;
import com.gwg.shiro.web.model.Resource;
import com.gwg.shiro.web.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author
 *
 */
@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;

	@Override
	public List<Resource> getResourceByUserid(String userid) {

		return resourceDao.queryResourceByUserid(userid);

	}

	/**
	 * 查询所有的资源
	 */
	public List<Resource> queryAllResources() {

		return null;

	}


}
