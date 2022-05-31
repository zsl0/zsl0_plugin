package com.zsl.custombox.common.entity;

import com.zsl.custombox.common.config.demo.DemoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author zsl
 * @Date 2022/5/20 23:12
 * @Email 249269610@qq.com
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(DemoConfiguration.class)
public @interface EnableDemoAnnotation {
    String name();

    String value();

}
