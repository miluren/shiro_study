package com.z.shiro_study.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr zhang
 */
@Configuration
public class ShiroConfig {

    /**
     * 设置session
     * @return DefaultWebSessionManager
     */
    @Bean
    public DefaultWebSessionManager getDefaultSessionManager() {
        DefaultWebSessionManager defaultSessionManager = new DefaultWebSessionManager();

        System.out.println("--> "+ defaultSessionManager.getGlobalSessionTimeout());
        // 配置session管理器, 设置过期时间（单位：毫秒）
        defaultSessionManager.setGlobalSessionTimeout(10*1000);
        return defaultSessionManager;
    }

    /**
     * 设置缓存
     * @return EhCacheManager
     */
    @Bean
    public EhCacheManager getEhCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }

    /**
     * 让shiro的注解能够得到加载和执行
     * @return advisorAutoProxyCreator
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 注解的解析器
     * @param defaultWebSecurityManager 安全管理器
     * @return advisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(defaultWebSecurityManager);

        return advisor;
    }

    /**
     * 加密规则
     * @return HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        // 用来指定加密规则
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 加密方法
        matcher.setHashAlgorithmName("md5");
        // hash次数
        matcher.setHashIterations(3);

        return matcher;
    }

    /**
     * 把realm配置到Spring当中
     * @return IniRealm
     */
    @Bean
    public MyRealm getIniRealm(HashedCredentialsMatcher matcher) {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(matcher);

        return myRealm;
    }

    /**
     * 配置 安全管理器到Spring中
     * @param myRealm realm
     * @return DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm myRealm, EhCacheManager ehCacheManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // SecurityManager要完成校验，需要Realm
        defaultWebSecurityManager.setRealm(myRealm);
        // 设置缓存管理器
        defaultWebSecurityManager.setCacheManager(ehCacheManager);
        // 设置session管理器
        defaultWebSecurityManager.setSessionManager(getDefaultSessionManager());

        return defaultWebSecurityManager;
    }


    /**
     * 配置shiro过滤器
     * @param defaultWebSecurityManager 安全管理器
     * @return  ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean  filterFactoryBean = new ShiroFilterFactoryBean();

        // 过滤器就是shiro进行权限校验的核心，进行认证授权是需要SecurityManager的
        filterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 设置拦截规则
        Map<String, String> filterMap = new HashMap<>(16);
        // anon 表示匿名用户（不拦截）
        // authc 认证用户 才能访问
        // user 使用RemeberMe的用户可访问
        // perms 对应权限可访问
        // roles 对应的角色可访问

        filterMap.put("/", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/regist", "anon");
        filterMap.put("/user/login", "anon");
        filterMap.put("/user/register", "anon");
        filterMap.put("/static/**", "anon");
        filterMap.put("/lesspermission.html", "anon");
        // 必须具有 sys:c:add 权限才能访问 c_add.html, 也可以是路径
        // 角色控制 roles[], 权限控制 perms[]
        filterMap.put("/c_add.html", "perms[sys:c:add]");
        // 退出
        filterMap.put("/exit", "logout");
        // 所有路径都要拦截
        filterMap.put("/**", "authc");

        filterFactoryBean.setFilterChainDefinitionMap(filterMap);
        // 设置为登录的时候跳转到 /login路径
        filterFactoryBean.setLoginUrl("/login.html");

        // 设置未授权访问的页面路径
        filterFactoryBean.setUnauthorizedUrl("/lesspermission.html");

        return filterFactoryBean;
    }
}
