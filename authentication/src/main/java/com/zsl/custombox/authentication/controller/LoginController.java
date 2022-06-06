package com.zsl.custombox.authentication.controller;

import com.zsl.custombox.authentication.model.login.UsernamePasswordLoginParam;
import com.zsl.custombox.security.auth.core.annotation.Anybody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 登录
 *
 * @Author zsl
 * @Date 2022/6/5 11:12
 * @Email 249269610@qq.com
 */
@RestController
@RequestMapping("/login")
@Api(value = "登录模块", tags = "登录模块")
public class LoginController {

    @PostMapping
    @Anybody
    @ApiOperation("用户名密码登录")
    public String usernamePasswordLogin(@ApiParam("账号密码登录") @RequestBody @Valid UsernamePasswordLoginParam usernamePasswordLoginParam) {
        System.out.println(usernamePasswordLoginParam);
        return "success!";
    }
}
