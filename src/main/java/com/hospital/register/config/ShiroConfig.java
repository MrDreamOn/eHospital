package com.hospital.register.config;

import java.util.LinkedHashMap;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hospital.register.shiro.RegisterAuthRealm;
import com.hospital.register.shiro.RegisterCredentialsMatcher;

/**
 * 
 * @author paul
 *
 */
@Configuration
public class ShiroConfig {

	@Bean(name="ehCacheManager")
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return em;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		// 配置登录的url和登录成功的url
		bean.setLoginUrl("/login");
		bean.setSuccessUrl("/index");
		bean.setUnauthorizedUrl("/403");
		// 配置访问权限
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/login*", "anon"); // 表示可以匿名访问,anon表示不需要拦截
		filterChainDefinitionMap.put("/loginUser", "anon");
		
		filterChainDefinitionMap.put("/druid/", "anon");
		filterChainDefinitionMap.put("/user/*", "anon");
		filterChainDefinitionMap.put("/schedule/*", "anon");
		filterChainDefinitionMap.put("/static*", "anon");
		filterChainDefinitionMap.put("/error*", "anon");
		filterChainDefinitionMap.put("/index*", "authc");
		filterChainDefinitionMap.put("/*", "authc");// 表示需要认证才可以访问
		filterChainDefinitionMap.put("/**", "authc");// 表示需要认证才可以访问
		filterChainDefinitionMap.put("/*.*", "authc");
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}

	// 配置核心安全事务管理器
	@Bean(name = "securityManager")
	public SecurityManager securityManager(@Qualifier("authRealm") RegisterAuthRealm authRealm) {
		System.err.println("--------------shiro已经加载----------------");
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(authRealm);
		//<!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
		manager.setCacheManager(getEhCacheManager());
		return manager;
	}

	// 配置自定义的权限登录器
	@Bean(name = "authRealm")
	public RegisterAuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher,@Qualifier("ehCacheManager") EhCacheManager cacheManager) {
		RegisterAuthRealm authRealm = new RegisterAuthRealm();
		authRealm.setCredentialsMatcher(matcher);
		authRealm.setCacheManager(cacheManager);
		return authRealm;
	}

	// 配置自定义的密码比较器
	@Bean(name = "credentialsMatcher")
	public RegisterCredentialsMatcher credentialsMatcher() {
		return new RegisterCredentialsMatcher();
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		// creator.setOrder(1);
		return creator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager manager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(manager);
		// advisor.setOrder(0);
		return advisor;
	}
}
