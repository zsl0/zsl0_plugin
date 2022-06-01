package com.zsl.custombox.common.core.http;

/**
 * 自定义返回状态
 *
 * @Author zsl
 * @Date 2022/5/15 18:58
 * @Email 249269610@qq.com
 */
public enum ResponseResultStatus {
    SUCCESS(2000, "操作成功!"),
    FAILED(4000, "操作失败!"),
    NOT_LOGIN(4011, "登录失败!"),
    AUTHENTICATION_FAILED(4010, "认证失败!"),
    FORBIDDEN(4030, "没有权限，禁止访问!"),
    ;
    private Integer code;
    private String msg;

    ResponseResultStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
}
