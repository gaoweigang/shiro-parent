参考：https://blog.csdn.net/lishehe/article/details/45219023
Apache shiro集群实现 （三）shiro身份认证（Shiro Authentication）

一、术语介绍
Authentication：身份认证，即用户提供一些信息来证明自己的身份。如用户名和密码，licence等。

Principals ：主体的“标识属性”，可以是任意标识，例如用户名，身份证号码，手机号码等。Principals
可以有多个，但是必须有一个主要的Principal（Primary Principal），这个标识，必须是唯一的。

Credentials：凭据，即只有主体知道或具有的秘密值，例如密码或数字证书，或者某些生物特征，例如指纹，视网膜等。

Principals/ Credentials的配对，他最常见的例子是用户名/密码。



二、身份认证（Authenticating Subjects）

身份认证可概括为3个步骤。

收集用户提供的身份标识和凭据。<li提交用户的身份标识 凭据信息进行身份验证。
验证通过允许访问，不通过则要求进行重试或者阻止登录。
代码实现。



1.收集用户提供的身份标识和凭据
//使用最常见的用户名密码的方式  
UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
//记住我  
token.setRememberMe(true);

在这个例子中，我们使用UsernamePasswordToken，支持最常见的用户名/口令的认证方式。它是org.apache.shiro.authc.AuthenticationToken
接口的一个实现。

值得注意的是，Shiro并不在乎你是如何获得这些信息的（上面例子中的username,和password）：这两个参数可能是用户通过http请求提交过来的，也可能是其他的方式获得的。



2.提交用户的身份标识/凭据信息进行身份验证。



在身份标识和凭据被收集并存入UsernamePasswordToken实例中，我们需要提交Token给Shiro去尝试执行身份认证。

//获取Subject对象  
Subject currentUser = SecurityUtils.getSubject();  
//传入上一步骤创建的token对象，登录，即进行身份验证操作。  
currentUser.login(token);


3.验证成功或失败。



我们可以通过subject.isAuthenticated()来判断是否验证成功，验证成功返回true,否则返回false。

如果登录失败，例如用户名或者密码错误，或者请求次数过多。我们可以根据Shiro提供的丰富的运行时错误，来来判断是由于什么引起的错误。


try {  
currentUser.login(token);  
} catch ( UnknownAccountException uae ) { //用户名未知...  
} catch ( IncorrectCredentialsException ice ) {//凭据不正确，例如密码不正确 ...  
} catch ( LockedAccountException lae ) { //用户被锁定，例如管理员把某个用户禁用...  
} catch ( ExcessiveAttemptsException eae ) {//尝试认证次数多余系统指定次数 ...  
} catch ( AuthenticationException ae ) {  
//其他未指定异常  
}  
//未抛出异常，程序正常向下执行。

三、已记住和已认证（Remembered vs. Authenticated）

如上例所示，shiro支持在登录过程中执行”remember me”，在此值得指出，一个已记住的Subject（remembered
Subject）和一个正常通过认证的Subject（authenticated Subject）在shiro是完全不同的。



Remembered（已记住）

       一个 remembered 主体，不是匿名的，有一个已知的ID(identity)（subject.getPrincipals()不为空）。而这个记住的过程，发生在之前的身份认证过程中。可以通过subject.isRemembered()来判断是已记住状态，已记住会返true。
   

 Authenticated（已认证）

       一个已认证的Subject是指在当前会话（session）中被成功地验证过了。即在认证过程没有抛出异常，如果是已认证的主体，subject.isAuthenticated()返回true。
    

互斥（Mutually Exclusive）
        以记住和已认证是互斥的，也就对于他们的判断（subject.isRemembered()和subject.isAuthenticated()），一个返回true，则另一个返回false。


为什么有这样的区别


       “身份验证”这个词有很强的证明的意思在里面。也就是说，有一个预期保证Subject 已经证明他们是他们所说的谁。当用户在之前的交互中被程序记住，认证将不复存在。根据已记住的ID(identity)系统可以知道用户很有可能是谁，但在现实中没有办法绝对保证被记住的Subject代表期望的用户。而当这个用户已认证（Authenticated）则这个用户不再被视为已记住的，因为在当前会话（Session）中已得到认证。
        对于高度机密或者重要的系统，例如财务相关的系统，要基于isAuthenticated()来判断，而不要基于isRemembered()来判断，以保证一个预期和核实的身份。这是为了保证安全性。



四、退出登录
认证相反的操作是释放所有已知的识别状态。当主体完成与应用程序交互，你可以调用subject.logout()放弃所有识别信息，即退出登录状态。


currentUser.logout();   
//removes all identifying information and invalidates their session too.  

注：调用了注销的方法，用户的session将会被作废，同时，在web系统中，rememberMe的cookie将会被删除。

因为在Web程序中记住身份信息往往使用Cookies，而Cookies只能在Response提交时才能被删除，所以强烈要求在为最终用户调用subject.logout()之后立即将用户引导到一个新页面，确保任何与安全相关的Cookies如期删除，这是Http本身Cookies功能的限制而不是Shiro的限制。



五、身份认证过程（Authentication Sequence）
shiro认证流程


shiro认证流程

应用程序根据用户的身份和凭证（principals和credentials）来构造出AuthenticationToken实例，并调用Subject.login的方法进行登录，其会自动委托给Security
Manager。
Subject实例通常上都是DelegatingSubject
(或子类)，在验证开始的时候，Subject实例会将验证委托给应用程序配置的SecurityManager，并调用securityManager.login(token)方法进行身份认证。
SecurityManager得到token信息后，通过调用authenticator.authenticate(token)方法，把身份验证委托给内置的Authenticator的实例进行验证。authenticator通常是ModularRealmAuthenticator
实例，支持对一个或多个Realm实例进行适配。ModularRealmAuthenticator提供了一种可插拔的认证风格，你可以在此处插入自定义Realm实现。
如果配置了多个Realm，ModularRealmAuthenticator会根据配置的AuthenticationStrategy（身份验证策略）进行多Realm认证过程。
注：如果应用程序中仅配置了一个Realm，Realm将被直接调用而无需再配置认证策略。
判断每个Realm是否支持提交的token，如果支持Realm就会调用getAuthenticationInfo（token）方法进行认证处理。
Authenticator
默认实现ModularRealmAuthenticator，支持单Realm和多Realm，如果是单Realm会直接调用Realm进行认证，如果配置了多个Realm需要根据认证策略，按照realms指定的顺序进行身份认证。



自定义Authenticator实现

[main]  
authenticator = com.foo.bar.CustomAuthenticator  
securityManager.authenticator = $authenticator

AuthenticationStrategy认证策略
如果是多个Realm,ModularRealmAuthenticator将会根据认证策略来确定认证是否成功。

例如，如果只有一个Realm验证成功，而其他Realm验证失败，那么这次认证是否成功呢？如果大多数的Realm验证成功了，认证是否就认为成功呢？或者，一个Realm验证成功后，是否还需要判断其他Realm的结果？认证策略就是根据应用程序的需要对这些问题作出决断。

AuthenticationStrategy 是个无状态的组件，在认证过程中会进行4次调用。

在所有Realm被调用之前
在调用Realm的getAuthenticationInfo
方法之前
在调用Realm的getAuthenticationInfo 方法之后
在所有Realm被调用之后
认证策略的另外一项工作就是聚合所有Realm的结果信息封装至一个AuthenticationInfo实例中，并将此信息返回，以此作为Subject的身份信息。   



Shiro有3中认证策略的具体实现：

AuthenticationStrategy类	 描述 
AtLeastOneSuccessfulStrategy	只要一个或者多个Realm认证通过，则整体身份认证就会视为成功。
FirstSuccessfulStrategy	只有第一个验证通过，才会视为整体认证通过。其他的会被忽略。
AllSuccessfulStrategy	只有所有的Realm认证成功，才会被视为认证通过。


ModularRealmAuthenticator 某人使用AtLeastOneSuccessfulStrategy 策略，你也可以换成其他的认证策略，ini配置： 



[main]  
...  
authcStrategy = org.apache.shiro.authc.pam.FirstSuccessfulStrategy  
securityManager.authenticator.authenticationStrategy = $authcStrategy  
...

自定义策略：继承org.apache.shiro.authc.pam.AbstractAuthenticationStrategy。
Realm的顺序 
Realm顺序对认证是有影响的。



默认顺序是按照定义的顺序，例如ini文件 中这样配置：



blahRealm = com.company.blah.Realm  
...  
fooRealm = com.company.foo.Realm  
...  
barRealm = com.company.another.Realm

那么将会按照 blahRealm ，fooRealm ，barRealm 的顺序依次调用。

显示指定排序

blahRealm = com.company.blah.Realm  
...  
fooRealm = com.company.foo.Realm  
...  
barRealm = com.company.another.Realm  
#显示指定顺序  
securityManager.realms = $fooRealm, $barRealm, $blahRealm  
...

六、完整的例子
1.在ini文件中配置用户信息。

新建maven项目，pom文件如下。

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">  
  <modelVersion>4.0.0</modelVersion>  
  <groupId>com.api6.shiro</groupId>  
  <artifactId>demo1</artifactId>  
  <version>0.0.1-SNAPSHOT</version>  
  <packaging>jar</packaging>  
  <name>demo1</name>  
  <url>http://maven.apache.org</url>  
  <properties>  
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>  
  </properties>  
  <dependencies>  
    <dependency>  
      <groupId>junit</groupId>  
      <artifactId>junit</artifactId>  
      <version>4.12</version>  
      <scope>test</scope>  
    </dependency>  
     <dependency>  
        <groupId>commons-logging</groupId>  
        <artifactId>commons-logging</artifactId>  
        <version>1.1.3</version>  
    </dependency>  
    <dependency>  
        <groupId>org.apache.shiro</groupId>  
        <artifactId>shiro-core</artifactId>  
        <version>1.2.3</version>  
   </dependency>  
  </dependencies>  
</project></span></span>  

在src/test/resource下新建shiro.ini文件文件配置为：
[users]  
zhao=111  
wang=111</span>  

在src/test/java下新建ShiroSimpleTest。
package com.api6.shiro.demo1;  
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
import org.apache.shiro.subject.Subject;  
import org.apache.shiro.util.Factory;  
import org.junit.Assert;  
import org.junit.Test;  
public class ShiroSimpleTest {  
    private static Log log = LogFactory.getLog(ShiroSimpleTest.class);  
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


执行junit单元测试，查看执行情况。

2.多Realm认证。自定义两个Realm，并指定认证策略。
Realm1

package com.api6.shiro.demo1.Realm;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import org.apache.shiro.authc.AccountException;  
import org.apache.shiro.authc.AuthenticationException;  
import org.apache.shiro.authc.AuthenticationInfo;  
import org.apache.shiro.authc.AuthenticationToken;  
import org.apache.shiro.authc.SimpleAuthenticationInfo;  
import org.apache.shiro.authc.UnknownAccountException;  
import org.apache.shiro.authc.UsernamePasswordToken;  
import org.apache.shiro.realm.AuthenticatingRealm;  
public class UserRealm1 extends AuthenticatingRealm {  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(  
            AuthenticationToken token) throws AuthenticationException {  
         UsernamePasswordToken upToken = (UsernamePasswordToken) token;  
            String username = upToken.getUsername();  
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

Realm2
package com.api6.shiro.demo1.Realm;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import org.apache.shiro.authc.AccountException;  
import org.apache.shiro.authc.AuthenticationException;  
import org.apache.shiro.authc.AuthenticationInfo;  
import org.apache.shiro.authc.AuthenticationToken;  
import org.apache.shiro.authc.SimpleAuthenticationInfo;  
import org.apache.shiro.authc.UnknownAccountException;  
import org.apache.shiro.authc.UsernamePasswordToken;  
import org.apache.shiro.realm.AuthenticatingRealm;  
public class UserRealm2 extends AuthenticatingRealm {  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(  
            AuthenticationToken token) throws AuthenticationException {  
         UsernamePasswordToken upToken = (UsernamePasswordToken) token;  
            String username = upToken.getUsername();  
            String password = "";  
            //创建一个用户list存放所有用户信息  
            List<Map<String,String>> userList = new ArrayList<Map<String,String>>();  
            //用户1  
            Map<String,String> user1 = new HashMap<String, String>();  
            user1.put("username", "zhao");  
            user1.put("password", "123");  
            //用户2   
            Map<String,String> user2 = new HashMap<String, String>();  
            user2.put("username", "zhang");  
            user2.put("password", "123");  
            userList.add(user1);  
            userList.add(user2);  
              
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
              
        return new SimpleAuthenticationInfo(username,password,getName());  
    }  
}


测试代码不变，修改shiro.ini文件如下：



[main]  
#自定义realm  
myRealm1 = com.api6.shiro.demo1.Realm.UserRealm1  
myRealm2 = com.api6.shiro.demo1.Realm.UserRealm2  
#指定realm的顺序  
securityManager.realms  = $myRealm1,$myRealm2  
#策略  
#authcStrategy = org.apache.shiro.authc.pam.FirstSuccessfulStrategy  
#authcStrategy = org.apache.shiro.authc.pam.AllSuccessfulStrategy  
authcStrategy = org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy  
securityManager.authenticator.authenticationStrategy = $authcStrategy  

执行junit单元测试，查看执行情况。