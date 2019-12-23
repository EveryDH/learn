
package com.learn.shiro.config;

import com.learn.shiro.realm.ShiroRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.DependsOn;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hao.dai
 * @date 2019/12/22
 */

@Configuration
public class ShiroConfig {

    @ConditionalOnMissingBean
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        //打印日志便于查看是否进入该拦截器
        System.out.println("LoginFilterBean.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filterMap = new HashMap<>();
        //定义拦截器名称
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainMap = new HashMap<>(20);
        filterChainMap.put("/swagger-ui.html", "anon");
        filterChainMap.put("/webjars/**", "anon");
        filterChainMap.put("/v2/**", "anon");
        filterChainMap.put("/swagger-resources/**", "anon");
        filterChainMap.put("/images/**", "anon");
        filterChainMap.put("/js/**", "anon");
        filterChainMap.put("/css/**", "anon");
        filterChainMap.put("/lib/**", "anon");
        filterChainMap.put("/fonts/**", "anon");
        filterChainMap.put("/icons/** ", "anon");
//        filterChainMap.put("/", "anon");
        //对所有用户认证
        //shiroFilterFactoryBean.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        //shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        //从配置文件中自定义拦截规则的相关配置信息
        //把自己定义的拦截相关规则交给shiro管理
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(ShiroRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(evaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    /**
     * 加入注解的使用，不加入这个注解不生效
     */

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

}
