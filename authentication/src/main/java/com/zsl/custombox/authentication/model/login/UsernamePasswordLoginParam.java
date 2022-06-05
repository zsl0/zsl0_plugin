package com.zsl.custombox.authentication.model.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Author zsl
 * @Date 2022/6/5 11:21
 * @Email 249269610@qq.com
 */
@ApiModel("用户名密码登录")
@Valid
public class UsernamePasswordLoginParam {
    @ApiModelProperty("用户名称")
    @NotBlank
    private String username;
    @ApiModelProperty("用户密码")
    @NotBlank
    private String password;
    @ApiModelProperty("验证码")
    @NotBlank
    private String code;
}
