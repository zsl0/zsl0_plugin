package com.zsl.custombox.security.admin.core.util;

import com.zsl.custombox.security.admin.core.model.Authentication;

/**
 * 存储全局用户认证信息 todo 定义凭证对象接口
 *
 * @Author zsl
 * @Date 2022/5/15 21:12
 * @Email 249269610@qq.com
 */
public class SecurityContextHolder {
    private static ThreadLocal<Authentication> SECURITY_CONTEXT = new ThreadLocal<>();

    public static Authentication getAuth() {
        return SECURITY_CONTEXT.get();
    }

    public static void setAuth(Authentication authentication) {
        SECURITY_CONTEXT.set(authentication);
    }

    public static void clear() {
        SECURITY_CONTEXT.remove();
    }
}
