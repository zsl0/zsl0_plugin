package com.zsl.custombox.common.core.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 获取全局存储用户信息SecurityContextHolder判断凭证是否正确
 *
 * @Author zsl
 * @Date 2022/5/15 21:12
 * @Email 249269610@qq.com
 */
public class SecurityContextFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*System.out.println("securityFilter exec...");

        // 获取凭证, 无效则返回
        if (!SecurityContextHolder.getAuth().isAuthenticated()) {
            new AuthenticationEntryPoint().failed(request, response);
            return;
        }

        // 全局保存request、response
        ServletContextHolder.setRequest(request);
        ServletContextHolder.setResponse(response);
        filterChain.doFilter(request, response);*/

    }
}
