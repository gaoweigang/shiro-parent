package com.gwg.shiro.web.service;

import com.gwg.shiro.web.model.Resource;

import java.util.List;

/**
 *
 */
public interface ResourceService {

	public List<Resource> getResourceByUserid(String username);

}
