package com.zsl.custombox.common.core.authentication;

/**
 * @Author zsl
 * @Date 2022/5/16 22:32
 * @Email 249269610@qq.com
 */
public interface Authentication {

    // 认证信息暂定方法
    Object getUuid();

    Object getDetails();

    boolean isAuthenticated();
}
