package com.gwg.shiro.web.common;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回码枚举类
 * @author laijianbo 2017年9月27日 上午10:51:11
 *
 */
public enum ReturnCode {

	SUCCESS("success", "0000"), //
	PRESTATION_FAIL("添加预站式队列失败，请重新操作一次", "1001"), //
	NO_LOGON("该用户未登录", "9560"), //
	PASSWORD_ERROR("密码错误", "9563"), //
	LOGIN_FAIED("登录失败，该用户已被冻结", "9564"), //
	LOGIN_ERR("登录用户权限不对,只允许坐席登录", "9566"),
	UNKOWN_USER("该用户不存在", "9565"), //
	INSERT_OR_UPDATE_ERROR("insert/update error", "9561"), //
	PARAMETER_IS_EMPTY("parameter is empty", "9562"), //
	BUSSINESS_ERROR("业务异常", "8000"), //
	ERROR("something is error....", "9999");

	@Setter
	@Getter
	private String message;
	@Setter
	@Getter
	private String code;

	private ReturnCode(String message, String code) {
		this.message = message;
		this.code = code;
	}

	public static String getMessage(String code) {
		for (ReturnCode c : ReturnCode.values()) {
			if (c.getCode().equals(code)) {
				return c.message;
			}
		}
		return null;
	}
}
