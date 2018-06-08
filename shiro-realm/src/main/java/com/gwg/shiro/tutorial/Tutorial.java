package com.gwg.shiro.tutorial;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class Tutorial {
    private static Log log = LogFactory.getLog(Tutorial.class);
    @Test
    public void testLogin() {
        //1.获取SecurityManager工厂，加载shiro.Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.获取SecurityManager实例
        SecurityManager securityManager = factory.getInstance();
        //3.将SecurityManager实例，绑定到SecurityUtils。
        SecurityUtils.setSecurityManager(securityManager);

        //4.使用最常见的用户名密码的方式，创建token
        UsernamePasswordToken token = new UsernamePasswordToken("zhao", "111");
        //5.设置记住我
        token.setRememberMe(true);
        //6.获取Subject对象
        Subject currentUser = SecurityUtils.getSubject();
        log.info("is remembered user: "+currentUser.isRemembered());

        try {
            //7.传入上一步骤创建的token对象，登录，即进行身份验证操作。
            currentUser.login(token);
        } catch ( UnknownAccountException uae ) {
            //用户名未知...
            log.info("用户不存在");
        } catch ( IncorrectCredentialsException ice ) {
            //凭据不正确，例如密码不正确 ...
            log.info("密码不正确");
        } catch ( LockedAccountException lae ) {
            //用户被锁定，例如管理员把某个用户禁用...
            log.info("用户被禁用");
        } catch ( ExcessiveAttemptsException eae ) {
            //尝试认证次数多余系统指定次数 ...
            log.info("请求次数过多，用户被锁定");
        } catch ( AuthenticationException ae ) {
            //其他未指定异常
            log.info("未知错误，无法完成登录");
        }
        //未抛出异常，程序正常向下执行。
        Assert.assertEquals(true, currentUser.isAuthenticated());
    }
}