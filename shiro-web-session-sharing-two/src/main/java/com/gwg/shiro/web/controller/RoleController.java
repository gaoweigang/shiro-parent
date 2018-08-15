package com.gwg.shiro.web.controller;

import com.gwg.shiro.web.common.Result;
import com.gwg.shiro.web.dto.RoleDto;
import com.gwg.shiro.web.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({ "/role" })
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Result addRole(@RequestBody RoleDto dto) {
		return new Result();
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result updateRole(@RequestBody RoleDto dto) {
		return new Result();
	}

	/**
	 * 根据条件查询角色信息
	 */
  	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Result qureyRoleList(@RequestBody RoleDto dto) {

		return new Result();
	}


	/**
	 * 根据角色id查询资源的集合
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getRoleById", method = RequestMethod.GET)
	public Result getResourceListByRoleId(@RequestParam("id") Integer id) {
		return new Result();
	}


	/**
	 * 删除角色
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result delRole(@RequestBody RoleDto dto) {
		return new Result();
	}

	@RequestMapping(value = "/allResources", method = RequestMethod.GET)
	public Result allResources() {
		return new Result();
	}
	
	@ApiOperation(value = "授予权限")
	@RequestMapping(value = "/grantResources", method = RequestMethod.POST)
	public Result<?> grantResources(@RequestBody RoleDto dto) {
		//roleService.grantResources(request);
		return new Result();
	}
}
