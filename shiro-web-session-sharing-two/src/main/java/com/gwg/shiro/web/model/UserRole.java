package com.gwg.shiro.web.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "tbl_user_role")
public class UserRole implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "BID")
    private Long bid;

    /**
     * 角色id
     */
    @Column(name = "ROLEID")
    private String roleid;

    /**
     * 座席编号
     */
    @Column(name = "USERID")
    private String userid;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return BID - 主键
     */
    public Long getBid() {
        return bid;
    }

    /**
     * 设置主键
     *
     * @param bid 主键
     */
    public void setBid(Long bid) {
        this.bid = bid;
    }

    /**
     * 获取角色id
     *
     * @return ROLEID - 角色id
     */
    public String getRoleid() {
        return roleid;
    }

    /**
     * 设置角色id
     *
     * @param roleid 角色id
     */
    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    /**
     * 获取座席编号
     *
     * @return USERID - 座席编号
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置座席编号
     *
     * @param userid 座席编号
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
}