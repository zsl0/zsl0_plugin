package com.zsl.custombox.authentication.controller;

import com.zsl.custombox.authentication.model.login.UsernamePasswordLoginParam;
import com.zsl.custombox.security.auth.core.annotation.Anybody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 *
 * @Author zsl
 * @Date 2022/6/5 11:12
 * @Email 249269610@qq.com
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping
    @Anybody
    public String usernamePasswordLogin(@RequestBody @Valid UsernamePasswordLoginParam usernamePasswordLoginParam) {
        System.out.println(usernamePasswordLoginParam);
        return "success!";
    }
}
