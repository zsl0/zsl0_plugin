package com.zsl.custombox.log.core.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 日志记录上下文
 *
 * @Author zsl
 * @Date 2022/5/22 16:48
 * @Email 249269610@qq.com
 */
@Data
@Accessors(chain = true)
public class LogRecordContext {
    // =========================== request data ===========================
    // 用户id(匿名为0)
    private Long userId;
    // 请求号码，保证请求调用时请求号不变
    private Long requestNo;
    // ip地址
    private String ip;
    // 统一资源接口uri
    private String uri;
    // 参数
    private String[] param;
    // 请求方法(GET、POST...)
    private String method;
    // 请求时间
    private Date startTime;

    // =========================== response data ===========================
    // 响应时间
    private Long respTime;
    // 响应码
    private Integer respCode;
    // 响应信息
    private String respMsg;
    // 响应体
    private String respBody;
}
