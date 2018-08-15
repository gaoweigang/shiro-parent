package com.gwg.shiro.web.dto;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "角色名称", required = true)
    private String roleCode;

    @ApiModelProperty(value = "角色描述", required = true)
    private String roleName;

    @ApiModelProperty(value = "资源ID", required = true)
    private Integer resourceId;
}
