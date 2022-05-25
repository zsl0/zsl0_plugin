package com.zsl.custombox.log.core.advice;

import com.zsl.custombox.log.core.annotation.LogRecord;
import com.zsl.custombox.log.core.model.logrecord.LogRecordContext;
import com.zsl.custombox.log.core.model.MethodExceptionResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author zsl
 * @Date 2022/5/23 23:20
 * @Email 249269610@qq.com
 */
@Aspect
@Component
public class LogRecordAdvice {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(com.zsl.custombox.log.core.annotation.LogRecord)")
    public Object logRecord(ProceedingJoinPoint point) throws Throwable {
        return execute(point, point.getTarget(), ((MethodSignature) point.getSignature()).getMethod(), point.getArgs());
    }

    private Object execute(ProceedingJoinPoint point, Object target, Method method, Object[] args) throws Throwable {
        Object ref = null;

        LogRecordContext.putEmptySpan();

        MethodExceptionResult exceptionResult = new MethodExceptionResult(true, null, "");
        // 拆分注解信息存储为List等待执行
        /**
         * List     Operations          operations
         * List     String              spElTemplates
         * Map      <String, String>    functionNameAndReturnMap
         *
         *         operations = logRecordOperationSource.computeLogRecordOperations(method, targetClass);
         *         List<String> spElTemplates = getBeforeExecuteFunctionTemplate(operations);
         *         //业务逻辑执行前的自定义函数解析
         *         functionNameAndReturnMap = processBeforeExecuteFunctionTemplate(spElTemplates, targetClass, method, args);
         *
         *
         *
         *         if (!CollectionUtils.isEmpty(operations)) {
         *             recordExecute(ret, method, args, operations, targetClass,
         *                     methodExecuteResult.isSuccess(), methodExecuteResult.getErrorMsg(), functionNameAndReturnMap);
         *         }
         */

        // 执行List中自定义函数

        try {
            ref = point.proceed();
        } catch (Throwable throwable) {
            exceptionResult = new MethodExceptionResult(false, throwable, throwable.getMessage());
        }
        // 解析List中SpEL

        try {
            // 存储日志

        } catch (Throwable t) {
            log.error("记录操作日志失败，不影响业务执行！", t);
        } finally {
            // 清理 Context
            LogRecordContext.clear();
        }
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
    private LogRecord getAnnotation(Method method) {
        LogRecord annotation = method.getAnnotation(LogRecord.class);
        return annotation;
    }
}
