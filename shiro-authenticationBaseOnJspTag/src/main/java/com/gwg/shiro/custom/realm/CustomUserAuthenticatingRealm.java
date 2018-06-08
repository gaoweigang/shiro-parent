package com.gwg.shiro.custom.realm;

import com.gwg.shiro.manage.AccountManager;
import com.gwg.shiro.model.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;

//默认使用的Realm是IniRealm
public class CustomUserAuthenticatingRealm extends AuthenticatingRealm {

    private static Log log = LogFactory.getLog(CustomUserAuthenticatingRealm.class);

    private AccountManager accountManager = new AccountManager();

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        log.info("认证开始111111 start ..................");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = accountManager.findUserByUserName(token.getUsername());
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
        } else {
            return null;
        }
    }
}