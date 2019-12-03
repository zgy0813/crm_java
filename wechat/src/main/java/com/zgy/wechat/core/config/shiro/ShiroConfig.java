package com.zgy.wechat.core.config.shiro;

import com.zgy.wechat.core.Constants;
import com.zgy.wechat.core.filter.ShiroLoginFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;

@Configuration
public class ShiroConfig {

    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher =
                new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName(Constants.HASH_ALGORITH_NAME);
        //加密次数
        credentialsMatcher.setHashIterations(Constants.HASH_ITERRATIONS);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    @Bean("userRealm")
    public UserRealm userRealm(@Qualifier("hashedCredentialsMatcher")
                                       HashedCredentialsMatcher matcher) {

        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    @Bean("phoneRealm")
    public PhoneRealm phoneRealm() {
        PhoneRealm phoneRealm = new PhoneRealm();
        return phoneRealm;
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager")
                                                     DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
        bean.setSecurityManager(securityManager);

        bean.setLoginUrl("/login");
        Map<String,Filter> filterMap2 = bean.getFilters();
        filterMap2.put("authc", new ShiroLoginFilter());
        bean.setFilters(filterMap2);

        /**
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * role：对应角色权限可访问
         **/
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login","anon");
        filterMap.put("/logon/**","anon");
        filterMap.put("/web/**","anon");
        filterMap.put("/msg/identify","anon");
        //swagger接口权限 开放
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");

        // 全部不需要权限
        filterMap.put("/**","anon");
        // filterMap.put("/**","authc");

        filterMap.put("/logout","logout");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    /**
     * 注入 securityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(
            HashedCredentialsMatcher hashedCredentialsMatcher) {

        DefaultWebSecurityManager securityManager =
                new DefaultWebSecurityManager();
        // 设置realms
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm(hashedCredentialsMatcher));
        realms.add(phoneRealm());
        securityManager.setRealms(realms);
//        securityManager.setRealm(userRealm(hashedCredentialsMatcher));
        return securityManager;
    }

    @Bean(name = "shiroLoginFilter")
    public ShiroLoginFilter shiroLoginFilter(){
        ShiroLoginFilter shiroLoginFilter = new ShiroLoginFilter();
        return shiroLoginFilter;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager")
                                                                                               DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public FilterRegistrationBean registration(ShiroLoginFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

}
