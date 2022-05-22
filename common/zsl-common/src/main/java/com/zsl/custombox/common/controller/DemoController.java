package com.zsl.custombox.common.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zsl
 * @Date 2022/5/16 21:18
 * @Email 249269610@qq.com
 */
@RestController
public class DemoController {

    @RequestMapping("test")
    public String demo() {
        return "success";
    }

}
