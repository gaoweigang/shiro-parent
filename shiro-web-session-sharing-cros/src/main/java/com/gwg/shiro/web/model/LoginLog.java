package com.gwg.shiro.web.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tbl_login_log")
public class LoginLog implements Serializable {
    /**
     * 主键流水号
     */
    @Id
    @Column(name = "ID")
    private Long id;

    /**
     * 客户端登陆IP
     */
    @Column(name = "CLIENTIP")
    private String clientip;

    /**
     * 分机号
     */
    @Column(name = "EXTNO")
    private String extno;

    /**
     * 登入时间
     */
    @Column(name = "LOGINTIME")
    private Date logintime;

    /**
     * 登出时间
     */
    @Column(name = "LOGOUTTIME")
    private Date logouttime;

    /**
     * 服务器IP
     */
    @Column(name = "SERVERIP")
    private String serverip;

    /**
     * 服务器端口
     */
    @Column(name = "SERVERPORT")
    private String serverport;

    /**
     * 服务系统 0 业务系统 1支撑系统
     */
    @Column(name = "SERVICEID")
    private String serviceid;

    /**
     * 团队ID
     */
    @Column(name = "TEAMID")
    private String teamid;

    /**
     * 坐席工号
     */
    @Column(name = "USERID")
    private String userid;

    /**
     * 坐席姓名
     */
    @Column(name = "USERNAME")
    private String username;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键流水号
     *
     * @return ID - 主键流水号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键流水号
     *
     * @param id 主键流水号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取客户端登陆IP
     *
     * @return CLIENTIP - 客户端登陆IP
     */
    public String getClientip() {
        return clientip;
    }

    /**
     * 设置客户端登陆IP
     *
     * @param clientip 客户端登陆IP
     */
    public void setClientip(String clientip) {
        this.clientip = clientip;
    }

    /**
     * 获取分机号
     *
     * @return EXTNO - 分机号
     */
    public String getExtno() {
        return extno;
    }

    /**
     * 设置分机号
     *
     * @param extno 分机号
     */
    public void setExtno(String extno) {
        this.extno = extno;
    }

    /**
     * 获取登入时间
     *
     * @return LOGINTIME - 登入时间
     */
    public Date getLogintime() {
        return logintime;
    }

    /**
     * 设置登入时间
     *
     * @param logintime 登入时间
     */
    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    /**
     * 获取登出时间
     *
     * @return LOGOUTTIME - 登出时间
     */
    public Date getLogouttime() {
        return logouttime;
    }

    /**
     * 设置登出时间
     *
     * @param logouttime 登出时间
     */
    public void setLogouttime(Date logouttime) {
        this.logouttime = logouttime;
    }

    /**
     * 获取服务器IP
     *
     * @return SERVERIP - 服务器IP
     */
    public String getServerip() {
        return serverip;
    }

    /**
     * 设置服务器IP
     *
     * @param serverip 服务器IP
     */
    public void setServerip(String serverip) {
        this.serverip = serverip;
    }

    /**
     * 获取服务器端口
     *
     * @return SERVERPORT - 服务器端口
     */
    public String getServerport() {
        return serverport;
    }

    /**
     * 设置服务器端口
     *
     * @param serverport 服务器端口
     */
    public void setServerport(String serverport) {
        this.serverport = serverport;
    }

    /**
     * 获取服务系统 0 业务系统 1支撑系统
     *
     * @return SERVICEID - 服务系统 0 业务系统 1支撑系统
     */
    public String getServiceid() {
        return serviceid;
    }

    /**
     * 设置服务系统 0 业务系统 1支撑系统
     *
     * @param serviceid 服务系统 0 业务系统 1支撑系统
     */
    public void setServiceid(String serviceid) {
        this.serviceid = serviceid;
    }

    /**
     * 获取团队ID
     *
     * @return TEAMID - 团队ID
     */
    public String getTeamid() {
        return teamid;
    }

    /**
     * 设置团队ID
     *
     * @param teamid 团队ID
     */
    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    /**
     * 获取坐席工号
     *
     * @return USERID - 坐席工号
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置坐席工号
     *
     * @param userid 坐席工号
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取坐席姓名
     *
     * @return USERNAME - 坐席姓名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置坐席姓名
     *
     * @param username 坐席姓名
     */
    public void setUsername(String username) {
        this.username = username;
    }
}