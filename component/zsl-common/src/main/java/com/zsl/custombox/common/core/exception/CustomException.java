package com.zsl.custombox.common.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Api异常
 *
 * @Author zsl
 * @Date 2022/5/15 19:29
 * @Email 249269610@qq.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException {
    private String msg;

    public CustomException() {
    }

    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
