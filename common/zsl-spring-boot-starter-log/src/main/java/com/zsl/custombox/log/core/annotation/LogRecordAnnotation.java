package com.zsl.custombox.log.core.annotation;

import java.lang.annotation.*;

/**
 * @Author zsl
 * @Date 2022/5/23 23:21
 * @Email 249269610@qq.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogRecordAnnotation {
    String value();
}
