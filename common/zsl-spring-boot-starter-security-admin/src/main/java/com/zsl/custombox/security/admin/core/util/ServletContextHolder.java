package com.zsl.custombox.security.admin.core.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SecurityFilter中存储
 *
 * @Author zsl
 * @Date 2022/5/16 21:28
 * @Email 249269610@qq.com
 */
public class ServletContextHolder {
    // 用户凭证
    private static ThreadLocal<HttpServletRequest> SERVLET_REQUEST = new ThreadLocal<>();
    private static ThreadLocal<HttpServletResponse> SERVLET_RESPONSE = new ThreadLocal<>();

    /**
     * 获取ServletRequest
     */
    public static HttpServletRequest getRequest() {
        return SERVLET_REQUEST.get();
    }

    /**
     * 获取ServletResponse
     */
    public static HttpServletResponse getResponse() {
        return SERVLET_RESPONSE.get();
    }

    /**
     * 获取ServletRequest
     */
    public static void setRequest(HttpServletRequest request) {
        SERVLET_REQUEST.set(request);
    }

    /**
     * 获取ServletResponse
     */
    public static void setResponse(HttpServletResponse response) {
        SERVLET_RESPONSE.set(response);
    }

    public static void clear() {
        SERVLET_REQUEST.remove();
        SERVLET_RESPONSE.remove();
    }
}
