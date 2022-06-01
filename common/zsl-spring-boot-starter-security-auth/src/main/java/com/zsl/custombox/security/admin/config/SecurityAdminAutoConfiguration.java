package com.zsl.custombox.security.admin.config;

import com.zsl.custombox.log.config.LogAutoConfiguration;
import com.zsl.custombox.security.admin.core.interceptor.SecurityContextInterceptor;
import com.zsl.custombox.security.admin.core.interceptor.AuthSecurityInterceptor;
import com.zsl.custombox.security.admin.core.model.DefaultPermissionServiceImpl;
import com.zsl.custombox.security.admin.core.model.PermissionService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 日志自动配置
 *
 * @Author zsl
 * @Date 2022/5/22 21:26
 * @Email 249269610@qq.com
 */
@Configuration
@AutoConfigureAfter(LogAutoConfiguration.class)
public class SecurityAdminAutoConfiguration implements WebMvcConfigurer {

    // ==========================   拦截器   ==========================
    @Bean
    public SecurityContextInterceptor securityContextInterceptor() {
        return new SecurityContextInterceptor();
    }

    @Bean
    public AuthSecurityInterceptor tokenInterceptor() {
        return new AuthSecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.tokenInterceptor());
        registry.addInterceptor(this.securityContextInterceptor());
    }

    @Bean
    @ConditionalOnMissingBean(PermissionService.class)
    public PermissionService permissionService() {
        return new DefaultPermissionServiceImpl();
    }

}
