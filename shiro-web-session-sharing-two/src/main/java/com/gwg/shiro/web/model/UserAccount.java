package com.gwg.shiro.web.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tbl_user_account")
public class UserAccount implements Serializable {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 用户登录id
     */
    @Column(name = "USERID")
    private String userid;

    /**
     * 密码连续错误次数
     */
    @Column(name = "ERRPWDCOUNT")
    private Double errpwdcount;

    /**
     * 最近成功登陆时间
     */
    @Column(name = "LASTLOGINTIME")
    private Date lastlogintime;

    /**
     * 最近密码更换时间
     */
    @Column(name = "LASTUPDATEPWDTIME")
    private Date lastupdatepwdtime;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 用户起用日期
     */
    @Column(name = "STARTDATE")
    private Date startdate;

    /**
     * 用户停用日期
     */
    @Column(name = "STOPTDATE")
    private Date stoptdate;

    /**
     * 用户当前状态(0：正常，1：密码过期，2：账户已冻结，3：已销户)
     */
    @Column(name = "USERSTATUS")
    private String userstatus;

    /**
     * 有效标志 1：有效，0：无效 表示停用
     */
    @Column(name = "VALIDFLAG")
    private String validflag;

    /**
     * 口令发送时间
     */
    @Column(name = "codeSendTime")
    private Date codesendtime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户登录id
     *
     * @return USERID - 用户登录id
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置用户登录id
     *
     * @param userid 用户登录id
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取密码连续错误次数
     *
     * @return ERRPWDCOUNT - 密码连续错误次数
     */
    public Double getErrpwdcount() {
        return errpwdcount;
    }

    /**
     * 设置密码连续错误次数
     *
     * @param errpwdcount 密码连续错误次数
     */
    public void setErrpwdcount(Double errpwdcount) {
        this.errpwdcount = errpwdcount;
    }

    /**
     * 获取最近成功登陆时间
     *
     * @return LASTLOGINTIME - 最近成功登陆时间
     */
    public Date getLastlogintime() {
        return lastlogintime;
    }

    /**
     * 设置最近成功登陆时间
     *
     * @param lastlogintime 最近成功登陆时间
     */
    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    /**
     * 获取最近密码更换时间
     *
     * @return LASTUPDATEPWDTIME - 最近密码更换时间
     */
    public Date getLastupdatepwdtime() {
        return lastupdatepwdtime;
    }

    /**
     * 设置最近密码更换时间
     *
     * @param lastupdatepwdtime 最近密码更换时间
     */
    public void setLastupdatepwdtime(Date lastupdatepwdtime) {
        this.lastupdatepwdtime = lastupdatepwdtime;
    }

    /**
     * 获取密码
     *
     * @return PASSWORD - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户起用日期
     *
     * @return STARTDATE - 用户起用日期
     */
    public Date getStartdate() {
        return startdate;
    }

    /**
     * 设置用户起用日期
     *
     * @param startdate 用户起用日期
     */
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    /**
     * 获取用户停用日期
     *
     * @return STOPTDATE - 用户停用日期
     */
    public Date getStoptdate() {
        return stoptdate;
    }

    /**
     * 设置用户停用日期
     *
     * @param stoptdate 用户停用日期
     */
    public void setStoptdate(Date stoptdate) {
        this.stoptdate = stoptdate;
    }

    /**
     * 获取用户当前状态(0：正常，1：密码过期，2：账户已冻结，3：已销户)
     *
     * @return USERSTATUS - 用户当前状态(0：正常，1：密码过期，2：账户已冻结，3：已销户)
     */
    public String getUserstatus() {
        return userstatus;
    }

    /**
     * 设置用户当前状态(0：正常，1：密码过期，2：账户已冻结，3：已销户)
     *
     * @param userstatus 用户当前状态(0：正常，1：密码过期，2：账户已冻结，3：已销户)
     */
    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }

    /**
     * 获取有效标志 1：有效，0：无效 表示停用
     *
     * @return VALIDFLAG - 有效标志 1：有效，0：无效 表示停用
     */
    public String getValidflag() {
        return validflag;
    }

    /**
     * 设置有效标志 1：有效，0：无效 表示停用
     *
     * @param validflag 有效标志 1：有效，0：无效 表示停用
     */
    public void setValidflag(String validflag) {
        this.validflag = validflag;
    }

    /**
     * 获取口令发送时间
     *
     * @return codeSendTime - 口令发送时间
     */
    public Date getCodesendtime() {
        return codesendtime;
    }

    /**
     * 设置口令发送时间
     *
     * @param codesendtime 口令发送时间
     */
    public void setCodesendtime(Date codesendtime) {
        this.codesendtime = codesendtime;
    }
}