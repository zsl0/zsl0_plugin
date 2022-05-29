package com.zsl.custombox.security.admin.core.interceptor;

import com.zsl.custombox.common.core.exception.AuthenticationFailedException;
import com.zsl.custombox.common.core.exception.NotAccessTokenException;
import com.zsl.custombox.common.core.exception.NotLoginException;
import com.zsl.custombox.common.model.authentication.Authentication;
import com.zsl.custombox.common.core.service.cache.TokenServer;
import com.zsl.custombox.security.admin.core.model.DefaultUserDetails;
import com.zsl.custombox.common.util.JsonUtil;
import com.zsl.custombox.common.util.SecurityContextHolder;
import com.zsl.custombox.common.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token拦截器
 *
 * @Author zsl
 * @Date 2022/5/22 20:01
 * @Email 249269610@qq.com
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    TokenServer tokenServer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // todo 白名单

        // 获取token
        String token = request.getHeader("Authentication");
        if (token == null || !token.startsWith("Bearer ")) {
            throw new NotLoginException();
        }

        // 根据token获取uuid
        String uuid;
        try {
            uuid = TokenUtil.getAccessTokenUuid(token.split(" ")[1]).trim();
        } catch (NotAccessTokenException e) {
            throw new AuthenticationFailedException("认证access_token失败！");
        }

        // 根据UUID获取认证信息
//        TokenServer tokenServer = ApplicationUtil.getBean(TokenServer.class);
//        Authentication authentication = JsonUtil.getJsonObject(tokenServer.get(uuid), Authentication.class);
        // todo redis缓存内容是否加密
        Authentication authentication = JsonUtil.getJsonObject(tokenServer.get(uuid), DefaultUserDetails.class);

        // 将凭证放入全局结构体中
        SecurityContextHolder.setAuth(authentication);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
