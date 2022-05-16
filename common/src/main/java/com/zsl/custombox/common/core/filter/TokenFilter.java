package com.zsl.custombox.common.core.filter;

import com.zsl.custombox.common.core.authentication.AccessDeniedHandler;
import com.zsl.custombox.common.core.authentication.Authentication;
import com.zsl.custombox.common.core.authentication.AuthenticationEntryPoint;
import com.zsl.custombox.common.core.exception.NotAccessTokenException;
import com.zsl.custombox.common.core.service.cache.TokenServer;
import com.zsl.custombox.common.util.ApplicationUtil;
import com.zsl.custombox.common.util.JsonUtil;
import com.zsl.custombox.common.util.SecurityContextHolder;
import com.zsl.custombox.common.util.TokenUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 校验Token是否有效
 *
 * @Author zsl
 * @Date 2022/5/15 21:12
 * @Email 249269610@qq.com
 */
public class TokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("tokenFilter exec...");

        // 白名单 todo 认证是否留到最后

        // 获取token
        String token = request.getHeader("Authentication");
        if (token == null || !token.startsWith("Bearer ")) {
            new AccessDeniedHandler().handle(request, response);
            return;
        }

        // 根据token获取uuid
        String uuid;
        try {
            uuid = TokenUtil.getAccessTokenUuid(token.split(" ")[1]);
        } catch (NotAccessTokenException e) {
            new AuthenticationEntryPoint().failed(request, response);
            return;
        }

        // 根据UUID获取认证信息
        TokenServer tokenServer = ApplicationUtil.getBean(TokenServer.class);
        Authentication authentication = JsonUtil.getJsonObject(tokenServer.get(uuid), Authentication.class);

        // 将凭证放入全局结构体中
        SecurityContextHolder.setAuth(authentication);

        filterChain.doFilter(request, response);
    }

/*    try {
        try {
            uuid = TokenUtil.getAccessTokenUuid(token);
        } catch (NotAccessTokenException e) {
            uuid = TokenUtil.getRefreshTokenUuid(token);
        }
    } catch (NotAccessTokenException e) {
        new AuthenticationEntryPoint().failed(request, response);
    }*/
}


