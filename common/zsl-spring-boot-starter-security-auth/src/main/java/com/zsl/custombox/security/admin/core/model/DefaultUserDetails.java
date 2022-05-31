package com.zsl.custombox.security.admin.core.model;

import com.zsl.custombox.common.model.base.BaseBean;
import com.zsl.custombox.common.model.authentication.Authentication;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 默认用户对象
 *
 * @Author zsl
 * @Date 2022/5/22 20:18
 * @Email 249269610@qq.com
 */
@NoArgsConstructor
@AllArgsConstructor
public class DefaultUserDetails extends BaseBean implements Authentication {
    // user对象
    private Object details;
    // uuid
    private String uuid;
    // userId
    private Long userId;
    // 是否官方自定义认证 对象
    private Boolean authenticated = false;



    @Override
    public String getUuid() {
        return uuid;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }
}
