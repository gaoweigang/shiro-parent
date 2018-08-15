package com.gwg.shiro.web.controller;

import com.alibaba.fastjson.JSON;
import com.gwg.shiro.web.common.Result;
import com.gwg.shiro.web.common.ReturnCode;
import com.gwg.shiro.web.dto.RoleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Home redirection to swagger api documentation
 */
@RestController
@Api(value = "role", tags = "权限管理")
@RequestMapping({ "/role" })
@Slf4j
public class RoleController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);



	@ApiOperation(value = "添加权限")
	@RequestMapping(value = "/ajaxAddRole", method = RequestMethod.POST)
	public Result ajaxAddRole(@RequestBody RoleDto dto) {
		logger.info("添加权限 请求参数：{}", JSON.toJSON(dto));
		return new Result(true, "添加权限成功", null, ReturnCode.SUCCESS.getCode());

	}

}
