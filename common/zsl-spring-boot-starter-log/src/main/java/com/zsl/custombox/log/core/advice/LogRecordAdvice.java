package com.zsl.custombox.log.core.advice;

import com.zsl.custombox.log.core.annotation.LogRecordAnnotation;
import com.zsl.custombox.log.core.model.MethodExceptionResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @Author zsl
 * @Date 2022/5/23 23:20
 * @Email 249269610@qq.com
 */
@Aspect
public class LogRecordAdvice {
    @Around("@annotation(com.zsl.custombox.log.core.annotation.LogRecordAnnotation)")
    public Object logRecord(ProceedingJoinPoint point) throws Throwable {
        Object ref = null;

        Method method = getMethod(point);
        LogRecordAnnotation annotation = getAnnotation(method);

        MethodExceptionResult exceptionResult = new MethodExceptionResult(true, null, "");
        // 解析日志注解信息可按自定义规则处理

        try {
            ref = point.proceed();
        } catch (Throwable throwable) {
            exceptionResult = new MethodExceptionResult(false, throwable, throwable.getMessage());
        }
        // 解析SpEL

        // 存储日志

        // 清理 Context

        // 执行失败将异常抛出
        if (!exceptionResult.isSuccess()) {
            throw exceptionResult.getThrowable();
        }
        return ref;
    }

    /**
     * 获取方法
     */
    private Method getMethod(ProceedingJoinPoint point) {
        return ((MethodSignature) point.getSignature()).getMethod();
    }

    /**
     * 获取注解
     */
    private LogRecordAnnotation getAnnotation(Method method) {
        LogRecordAnnotation annotation = method.getAnnotation(LogRecordAnnotation.class);
        return annotation;
    }
}
