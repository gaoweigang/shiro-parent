package com.gwg.shiro.custom.realm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.gwg.shiro.tutorial.Tutorial;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.text.IniRealm;
//默认使用的Realm是IniRealm
public class UserRealmOne extends AuthenticatingRealm {

    private static Log log = LogFactory.getLog(UserRealmOne.class);

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        log.info("Realm one start.................");

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        log.info("usernmae :"+username);
        String password = "";
        //创建一个用户list存放所有用户信息
        List<Map<String,String>> userList = new ArrayList<Map<String,String>>();
        //用户1
        Map<String,String> user1 = new HashMap<String, String>();
        user1.put("username", "zhao");
        user1.put("password", "111");
        //用户2
        Map<String,String> user2 = new HashMap<String, String>();
        user2.put("username", "zhang");
        user2.put("password", "222");
        userList.add(user1);
        userList.add(user2);

        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        //遍历所有用户查询是否存在请求认证的用户，如果存在获取正确的密码
        boolean flag=false;
        for(Map<String,String> user:userList){
            if(username.equals(user.get("username"))){
                password = user.get("password");
                flag=true;
                break;
            }
        }

        if(!flag){
            throw new UnknownAccountException("没有找到用户 [" + username + "]");
        }
        //将正确的用户信息，请求登录用户的用户名和正确的密码，创建AuthenticationInfo对象并返回
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName());
        return info ;
    }
}