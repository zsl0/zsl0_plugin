package com.zsl.custombox.common.core.http;

/**
 * 自定义返回状态
 *
 * @Author zsl
 * @Date 2022/5/15 18:58
 * @Email 249269610@qq.com
 */
public enum ResponseResultStatus {
    SUCCESS(2000, "操作成功"),
    FAILED(4000, "操作失败"),
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
