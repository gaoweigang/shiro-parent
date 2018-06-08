Apache shiro集群实现 （二） shiro 的INI配置
参考：https://blog.csdn.net/lishehe/article/details/45218605

一、概述
INI配置文件是一种key/value的键值对配置，提供了分类的概念，每一个类中的key不可重复。在这个示例中我们使用一个INI文件来配置Shiro SecurityManager，首先，在pom.xml同目录中创建一个src/main/resources子目录，在该子目录中创建一个shiro.ini文件，内容如下：

例如：
# =======================
# Shiro INI 配置
# =======================
[main]
# 对象和它们的属性在这里定义
# 例如 SecurityManager, Realms 等。
[users]
# 用户在这里定义，如果只是一小部分用户。（实际使用中，使用这种配置显然不合适）
[roles]
# 角色在这里定义，（实际应用中也不会在这里定义角色，一般都是存储于数据库中）
[urls]
# web系统中，基于url的权限配置，web章节会介绍。
二、详细介绍
[main]部分
主要是SecurityManager及其依赖项的配置，例如Realm。
下边是main的一个配置的例子。
[main]
sha256Matcher = org.apache.shiro.authc.credential.Sha256CredentialsMatcher
myRealm = com.company.security.shiro.DatabaseRealm
myRealm.connectionTimeout = 30000
myRealm.username = jsmith
myRealm.password = secret
myRealm.credentialsMatcher = $sha256Matcher
securityManager.sessionManager.globalSessionTimeout = 1800000
定义对象：
[main]
myRealm = com.company.shiro.realm.MyRealm
...
设置对象的值：
...
myRealm.connectionTimeout = 30000
myRealm.username = jsmith
...
上面的配置会转化为方法的调用
...
myRealm.setConnectionTimeout(30000);
myRealm.setUsername("jsmith");
...
对象的注入：
...
sha256Matcher = org.apache.shiro.authc.credential.Sha256CredentialsMatcher
...
myRealm.credentialsMatcher = $sha256Matcher
...
会调用myRealm.setCredentialsMatcher(sha256Matcher)，完成对象的注入。

嵌套属性赋值：
...
securityManager.sessionManager.globalSessionTimeout = 1800000
...
转化为java代码
securityManager.getSessionManager().setGlobalSessionTimeout(1800000);
数组类型注入：

# 'cipherKey' 是数组类型的默认使用Base64编码
securityManager.rememberMeManager.cipherKey = kPH+bIxk5D2deZiIxcaaaA==
...
也可以设置为以0x开头的十六进制
securityManager.rememberMeManager.cipherKey = 0x3707344A4093822299F31D008
集合类型的注入：

Array/Set/List setter，多个值通过逗号分隔

sessionListener1 = com.company.my.SessionListenerImplementation
...
sessionListener2 = com.company.my.other.SessionListenerImplementation
...
securityManager.sessionManager.sessionListeners = $sessionListener1, $sessionListener2
Map键值通过冒号分隔
object1 = com.company.some.Class
object2 = com.company.another.Class
...
anObject = some.class.with.a.Map.property
anObject.mapProperty = key1:$object1, key2:$object2
key也可以是引用类型的。
anObject.map = $objectKey1:$objectValue1, $objectKey2:$objectValue2
...
注入顺序：

后边的会覆盖前面的值。
...
myRealm = com.company.security.MyRealm
...
myRealm = com.company.security.DatabaseRealm
...
默认值：

在上面的例子中，我们没有定义SecurityManager却可以对它赋值，这是因为shiro会创建一个SecurityManager默认实现，而对于用户来说，无需关注，如果你要显示的指定自定义的SecurityManager也是可以的。
...
securityManager = com.company.security.shiro.MyCustomSecurityManager
...
[users]
用户账户的静态配置，例如配置用户名/密码以及角色
[users]
admin = 123
lonestarr = 123, role1, role2
darkhelmet = 123, role1, role2
默认规则
username = password, roleName1, roleName2, ..., roleNameN
密码的加密
[main]
...
sha256Matcher = org.apache.shiro.authc.credential.Sha256CredentialsMatcher
...
iniRealm.credentialsMatcher = $sha256Matcher
...
[users]
# user1 = sha256-hashed-hex-encoded password, role1, role2, ...
user1 = 2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b, role1, role2, ...
如果你想让你的密码是base64编码，则如下配置。
[main]
...
# true = hex, false = base64:
sha256Matcher.storedCredentialsHexEncoded = false
[roles]
[roles]
# 'admin' 拥有所有权限
admin = *
# 'schwartz' 拥有lightsaber下所有权限
schwartz = lightsaber:*
# 'goodguy' 拥有winnebago下的'drive' 下的 'eagle5'权限
goodguy = winnebago:drive:eagle5
规则
rolename = permissionDefinition1, permissionDefinition2, ..., permissionDefinitionN
更多请参考授权章节
[urls]
配置url及相应的拦截器之间的关系，格式：“url=拦截器[参数]，拦截器[参数]，如：
[urls]  
/admin/** = authc, roles[admin], perms["permission1"]
具体参考web章节