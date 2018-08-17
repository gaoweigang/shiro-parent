package com.gwg.shiro.web.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    //用户名
    private String userId;

    //身份证号
    private String cardNo;

    //手机号码
    private String mobile;

    //入职时间
    private Date entryStartDate;

    private Date entryEndDate;

    //角色
    private String roleCode;

    //状态
    private Integer status;


}
