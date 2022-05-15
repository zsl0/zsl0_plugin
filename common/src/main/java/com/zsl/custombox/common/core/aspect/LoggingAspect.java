package com.zsl.custombox.common.core.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 响应日志收集
 *
 * @Author zsl
 * @Date 2022/5/15 19:34
 * @Email 249269610@qq.com
 */
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.zsl.custombox.*..controller.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // todo 实现显示对象 以及日志收集

        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();


        // 单次请求消耗时间
        long idleTime = end - start;

        return proceed;
    }

}
