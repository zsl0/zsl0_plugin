package com.zsl.custombox.common.core.advice;

import com.zsl.custombox.common.core.exception.*;
import com.zsl.custombox.common.core.http.ResponseResult;
import com.zsl.custombox.common.core.http.ResponseResultStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @Author zsl
 * @Date 2022/5/15 19:26
 * @Email 249269610@qq.com
 */
@RestControllerAdvice
//@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(Throwable.class)
    public ResponseResult<String> global(Throwable t) {
        // 特殊处理
        if (t instanceof ApiException) {
            return ResponseResult.custom(ResponseResultStatus.SUCCESS.getCode(), t.getMessage(), null);
        } else if (t instanceof GlobalException) {
            return ResponseResult.custom(((GlobalException) t).getCode(), t.getMessage(), null);
        }

        // 打印错误信息
        t.printStackTrace();
        return ResponseResult.success(t.getMessage());
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseResult<String> globalException(GlobalException t) {
        // 打印错误信息
        t.printStackTrace();
        return ResponseResult.custom(((GlobalException) t).getCode(), t.getMessage(), null);
    }

    /**
     * 捕捉认证异常
     */
    @ExceptionHandler(AbstractAuthenticationException.class)
    public ResponseResult<Void> auth(AbstractAuthenticationException e) {
        ResponseResultStatus status = null;
        if (e instanceof AuthenticationFailedException) {
            status = ResponseResultStatus.UNAUTHORIZED;
        } else if (e instanceof NotLoginException) {
            status = ResponseResultStatus.NOT_LOGIN;
        } else {
            status = ResponseResultStatus.UNAUTHORIZED;
        }

        return ResponseResult.custom(status, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult methodArgumentNotValid(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        return ResponseResult.custom(ResponseResultStatus.BAD_REQUEST, fieldErrors);
    }
}
