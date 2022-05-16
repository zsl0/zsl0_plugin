package com.zsl.custombox.common.config.webmvc;

import com.zsl.custombox.common.core.filter.SecurityContextFilter;
import com.zsl.custombox.common.core.filter.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

/**
 * @Author zsl
 * @Date 2022/5/16 20:18
 * @Email 249269610@qq.com
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {



    /**
     * 注册TokenFilter
     */
    @Bean
    public FilterRegistrationBean<Filter> securityFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(new SecurityContextFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 20);
        return filterRegistrationBean;
    }

    /**
     * 注册TokenFilter
     */
    @Bean
    public FilterRegistrationBean<Filter> tokenFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(new TokenFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 10);
        return filterRegistrationBean;
    }
}
