package com.zsl.custombox.authentication.controller.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @Author zsl
 * @Date 2022/6/9 16:27
 * @Email 249269610@qq.com
 */
@Data
@Builder
public class TokenVo {
    private String accessToken;
    private String refreshToken;
}
