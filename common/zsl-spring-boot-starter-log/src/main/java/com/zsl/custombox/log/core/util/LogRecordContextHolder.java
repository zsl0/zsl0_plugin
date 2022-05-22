package com.zsl.custombox.log.core.util;


import com.zsl.custombox.log.core.model.LogRecordContext;

/**
 * 线程安全日志记录
 *
 * @Author zsl
 * @Date 2022/5/22 16:57
 * @Email 249269610@qq.com
 */
public class LogRecordContextHolder {
    private static final ThreadLocal<LogRecordContext> LOG_RECORD_CONTEXT = new ThreadLocal<>();

    public static LogRecordContext get() {
        return LOG_RECORD_CONTEXT.get();
    }

    public static void set(LogRecordContext logRecordContext) {
        LOG_RECORD_CONTEXT.set(logRecordContext);
    }

    public static void clear() {
        LOG_RECORD_CONTEXT.remove();
    }
}
