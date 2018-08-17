package com.gwg.shiro.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author
 *
 */
@Data
public class RoleDto implements Serializable{

    private Integer id;

    //角色名称
    private String roleName;

    //角色描述
    private String roleDesc;
}
