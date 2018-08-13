package com.gwg.shiro.web.config;

import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author April.Chen
 */
@Configuration
public class ShiroConfig {

	@Autowired
	private MyShiroRealm myShiroRealm;

	@Autowired
	private RedisSessionDAO sessionDAO;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/*	@Bean
		public MyShiroRealm getUserRealm() {
			MyShiroRealm myShiroRealm = new MyShiroRealm();
			myShiroRealm.setCachingEnabled(true);
			myShiroRealm.setAuthenticationCachingEnabled(true);
			myShiroRealm.setAuthenticationCacheName("authenticationCache");
			myShiroRealm.setAuthorizationCachingEnabled(true);
			myShiroRealm.setAuthorizationCacheName("authorizationCache");
			return myShiroRealm;
		}*/

	@Bean
	public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public CustomShiroCacheManager redisCacheManager() {
		CustomShiroCacheManager redisCacheManager = new CustomShiroCacheManager();
		redisCacheManager.setRedisTemplate(redisTemplate);
		return redisCacheManager;
	}

	@Bean
	public SessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(this.sessionDAO);
		sessionManager.setCacheManager(redisCacheManager());
		return sessionManager;
	}

	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setSessionManager(sessionManager());
		securityManager.setRealm(myShiroRealm);
		securityManager.setCacheManager(redisCacheManager());
		return securityManager;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager());
		return new AuthorizationAttributeSourceAdvisor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		shiroFilterFactoryBean.setLoginUrl("/shiro/user/ajaxLogin");
		shiroFilterFactoryBean.setSuccessUrl("/success");
		Map<String, Filter> filters = new HashMap<String, Filter>();
		filters.put("myauthc", new MyAccessControlFilter());
		shiroFilterFactoryBean.setFilters(filters);
		
		filterChainDefinitionMap.put("/operator/createSubscription", "anon"); 
		filterChainDefinitionMap.put("/operator/phoneCallBack", "anon"); 
		filterChainDefinitionMap.put("/operator/updateOperator", "anon");
		//在这里可以配置拥有哪些角色的用户可以登陆系统
        //filterChainDefinitionMap.put("/user/ajaxLogin", "perms[test]");
		filterChainDefinitionMap.put("/user/ajaxLogin", "authc");
		filterChainDefinitionMap.put("/shiro/user/ajaxLogin", "authc");
		filterChainDefinitionMap.put("/basic/**", "anon");
        filterChainDefinitionMap.put("/orderManage/queryProductTerm", "anon");
        filterChainDefinitionMap.put("/weixin/**", "anon");
        filterChainDefinitionMap.put("/saleFollow/weixinsubmitToReturnState", "anon");
		filterChainDefinitionMap.put("/creditRemoteService/saveCreditInfoRemote", "anon");
		filterChainDefinitionMap.put("/saleFollow/receiveAmount", "anon");
		filterChainDefinitionMap.put("/alertManage/queryFinalExamineAmount", "anon");
		filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/swagger-ui.html", "anon"); 
        filterChainDefinitionMap.put("/webjars/**", "anon"); 
        filterChainDefinitionMap.put("/swagger-resources/**", "anon"); 
        filterChainDefinitionMap.put("/v2/**", "anon"); 
        filterChainDefinitionMap.put("/ricky-websocket/**", "anon"); 
        filterChainDefinitionMap.put("/topic/**", "anon"); 
        filterChainDefinitionMap.put("/ricky/**", "anon"); 
        filterChainDefinitionMap.put("/app/**", "anon"); 
        filterChainDefinitionMap.put("/msg/**", "anon"); 
		filterChainDefinitionMap.put("/**", "myauthc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}



}