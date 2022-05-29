package com.zsl.custombox.common;

import com.zsl.custombox.common.model.annotation.EnableDemoAnnotation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @Author zsl
 * @Date 2022/5/15 14:39
 * @Email 249269610@qq.com
 */
@SpringBootApplication
@EnableDemoAnnotation(name = "zsl", value = "hyj")
@EnableConfigurationProperties
public class CommonApp {
    public static void main(String[] args) {
        SpringApplication.run(CommonApp.class);
    }
}
