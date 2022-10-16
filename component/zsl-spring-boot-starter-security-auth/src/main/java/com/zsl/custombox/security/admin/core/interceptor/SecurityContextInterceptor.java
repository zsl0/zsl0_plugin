package com.zsl.custombox.security.admin.core.interceptor;

import com.zsl.custombox.common.core.exception.AuthenticationFailedException;
import com.zsl.custombox.common.util.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 获取全局存储用户信息SecurityContextHolder判断凭证是否正确
 *
 * @Author zsl
 * @Date 2022/5/22 20:02
 * @Email 249269610@qq.com
 */
public class SecurityContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (SecurityContextHolder.getAuth().isAuthenticated()) {
            return true;
        }
        // todo 删除redis token
        throw new AuthenticationFailedException("认证失败！");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
