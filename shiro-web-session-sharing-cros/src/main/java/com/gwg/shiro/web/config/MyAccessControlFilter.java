package com.gwg.shiro.web.config;

import com.alibaba.fastjson.JSONObject;
import com.gwg.shiro.web.common.Result;
import com.gwg.shiro.web.common.ReturnCode;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 */
public class MyAccessControlFilter extends FormAuthenticationFilter {

	@Override
	protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			Result<?> result = new Result();
			result.setStatus(false);
			result.setStatusCode(ReturnCode.NO_LOGON.getCode());
			result.setMessage(ReturnCode.NO_LOGON.getMessage());
			writer.write(JSONObject.toJSONString(result));
		} catch(Exception e){
			e.printStackTrace();
		}
		return;
	}

}