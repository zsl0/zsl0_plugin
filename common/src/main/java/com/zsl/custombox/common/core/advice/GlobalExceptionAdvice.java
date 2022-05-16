package com.zsl.custombox.common.core.advice;

import com.zsl.custombox.common.core.exception.ApiException;
import com.zsl.custombox.common.core.http.ResponseResult;
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
        }

        // 打印错误信息
        t.printStackTrace();
        return ResponseResult.success(t.getMessage());
    }
}
