package com.zsl.custombox.log.core.interceptor;

import com.zsl.custombox.common.util.SecurityContextHolder;
import com.zsl.custombox.common.util.ServletContextHolder;
import com.zsl.custombox.common.model.log.SystemLogContext;
import com.zsl.custombox.common.util.SystemLogContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 访问记录(系统日志)
 *  todo 实现@LogRecord注解实现操作日志
 *
 * @Author zsl
 * @Date 2022/5/22 14:55
 * @Email 249269610@qq.com
 */
public class AccessLogInterceptor implements HandlerInterceptor {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 创建全局日志记录上下文 todo 获取数据(实现工具类)

        SystemLogContext systemLogContext = new SystemLogContext()
                .setUserId(SecurityContextHolder.getAuth().getUserId())
                .setRequestNo(0L)// 可以使用雪花算法获取64位唯一id
                .setIp(ServletContextHolder.getIp())
                .setUri(request.getRequestURI())
                .setMethod(request.getMethod())
                .setStartTime(new Date(System.currentTimeMillis()));
        // 存储全局日志记录上下文
        SystemLogContextHolder.set(systemLogContext);
/*        // format log
        StringBuilder requestStr = new StringBuilder();
        List<Object> requestArgs = new ArrayList<>();
        requestStr.append("\n=========================== Request Start ===========================\n");
        requestStr.append(String.format("       %-10s: {}\n", "userId"));
        requestArgs.add(logRecordContext.getUserId());
        requestStr.append(String.format("       %-10s: {}\n", "requestNo"));
        requestArgs.add(logRecordContext.getRequestNo());
        requestStr.append(String.format("       %-10s: {}\n", "ip"));
        requestArgs.add(logRecordContext.getIp());
        requestStr.append(String.format("       %-10s: {}\n", "uri"));
        requestArgs.add(logRecordContext.getUri());
        requestStr.append(String.format("       %-10s: {}\n", "param"));
        requestArgs.add(logRecordContext.getParam());
        requestStr.append(String.format("       %-10s: {}\n", "method"));
        requestArgs.add(logRecordContext.getMethod());
        requestStr.append(String.format("       %-10s: {}\n", "startTime"));
        requestArgs.add(logRecordContext.getStartTime());
        requestStr.append("=========================== Request End ===========================\n");
        log.info(requestStr.toString(), requestArgs.toArray());*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SystemLogContext systemLogContext = SystemLogContextHolder.get();
        systemLogContext.setRespTime(System.currentTimeMillis() - systemLogContext.getStartTime().getTime());

        // format log
        StringBuilder requestStr = new StringBuilder();
        List<Object> requestArgs = new ArrayList<>();
        requestStr.append("\n=========================== AccessLog ===========================\n");
        requestStr.append(String.format("       %-10s: {}\n", "userId"));
        requestArgs.add(systemLogContext.getUserId());
        requestStr.append(String.format("       %-10s: {}\n", "requestNo"));
        requestArgs.add(systemLogContext.getRequestNo());
        requestStr.append(String.format("       %-10s: {}\n", "ip"));
        requestArgs.add(systemLogContext.getIp());
        requestStr.append(String.format("       %-10s: {}\n", "uri"));
        requestArgs.add(systemLogContext.getUri());
        requestStr.append(String.format("       %-10s: {}\n", "method"));
        requestArgs.add(systemLogContext.getMethod());
        requestStr.append(String.format("       %-10s: {}\n", "startTime"));
        requestArgs.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(systemLogContext.getStartTime()));
        requestStr.append(String.format("       %-10s: {} ms\n", "respTime"));
        requestArgs.add(systemLogContext.getRespTime());
        requestStr.append(String.format("       %-10s: {}\n", "respCode"));
        requestArgs.add(systemLogContext.getRespCode());
        requestStr.append(String.format("       %-10s: {}\n", "respMsg"));
        requestArgs.add(systemLogContext.getRespMsg());
//        requestStr.append(String.format("       %-10s: {}\n", "respBody"));
//        requestArgs.add(systemLogContext.getRespBody());
        requestStr.append("=================================================================\n");

        // todo 日志入库
        log.info(requestStr.toString(), requestArgs.toArray());

        // 清理ThreadLocal
        SystemLogContextHolder.clear();
    }
}
