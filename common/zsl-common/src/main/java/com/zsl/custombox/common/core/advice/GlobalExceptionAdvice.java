package com.zsl.custombox.common.core.advice;

import com.zsl.custombox.common.core.exception.*;
import com.zsl.custombox.common.core.http.ResponseResult;
import com.zsl.custombox.common.core.http.ResponseResultStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author zsl
 * @Date 2022/5/15 19:26
 * @Email 249269610@qq.com
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(Throwable.class)
    public ResponseResult<String> global(Throwable t) {
        // 特殊处理
        if (t instanceof ApiException) {
            return ResponseResult.custom(2000, ((ApiException) t).getMsg(), null);
        } else if (t instanceof GlobalException) {
            return ResponseResult.custom(((GlobalException) t).getCode(), t.getMessage(), null);
        }

        // 打印错误信息
        t.printStackTrace();
        return ResponseResult.success(t.getMessage());
    }

    /**
     * 捕捉认证异常
     */
    @ExceptionHandler(AbstractAuthenticationException.class)
    public ResponseResult<Void> auth(AbstractAuthenticationException e) {
        ResponseResultStatus status = null;
        if (e instanceof AuthenticationFailedException) {
            status = ResponseResultStatus.AUTHENTICATION_FAILED;
        } else if (e instanceof NotLoginException) {
            status = ResponseResultStatus.NOT_LOGIN;
        } else {
            status = ResponseResultStatus.AUTHENTICATION_FAILED;
        }

        return ResponseResult.custom(status, null);
    }
}
