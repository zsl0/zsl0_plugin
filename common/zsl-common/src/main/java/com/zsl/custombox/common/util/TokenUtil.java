package com.zsl.custombox.common.util;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zsl.custombox.common.core.exception.AuthenticationFailedException;
import com.zsl.custombox.common.core.exception.NotAccessTokenException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.UUID;

/**
 * 基于JWT获取Token令牌
 *
 * @Author zsl
 * @Date 2022/5/15 14:41
 * @Email 249269610@qq.com
 */
public class TokenUtil {

    // todo 四个参数需要初始化
    // 密钥
    public static String secret;
    // 发行人
    public static String issuer;
    // 访问Token过期分钟
    public static Integer ACCESS_TOKEN_MINUTE_EXPIRE;
    // 刷新Token过期分钟
    public static Integer REFRESH_TOKEN_MINUTE_EXPIRE;

    /**
     * 创建唯一Token, 凭借Payload中uuid作为当前用户的唯一凭证，与用户进行绑定
     */
    public static String createAccessToken() {
        Assert.notNull(ACCESS_TOKEN_MINUTE_EXPIRE, "ACCESS_TOKEN_MINUTE_EXPIRE 不能为空!");

        Date expire = DateUtil.offsetMinute(new Date(), ACCESS_TOKEN_MINUTE_EXPIRE);
        // 使用 uuid 作为 键值
        return getToken("access_token", getUUID(), expire);
    }

    public static String createRefreshToken() {
        Assert.notNull(REFRESH_TOKEN_MINUTE_EXPIRE, "REFRESH_TOKEN_MINUTE_EXPIRE 不能为空!");

        Date expire = DateUtil.offsetMinute(new Date(), REFRESH_TOKEN_MINUTE_EXPIRE);
        // 使用 uuid 作为 键值
        return getToken("refresh_token", getUUID(), expire);
    }

    /**
     * 获取唯一凭证uuid
     */
    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成token
     */
    private static String getToken(String subject, String uuid, Date expire) {
        Assert.notNull(secret, "secret(密钥) 不能为空");
        Assert.notNull(issuer, "issuer(发行人) 不能为空");

        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                    .withIssuer(issuer)
                    .withClaim("uuid", uuid)
                    .withSubject(subject)
                    .withExpiresAt(expire)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    /**
     * 获取access_token存储uuid
     */
    @Nullable
    public static String getAccessTokenUuid(String token) {
        String uuid = null;
        String subject = getClaim(token, "subject");
        if ("access_token".equals(subject)) {
            uuid = getClaim(token, "uuid");
        }
        return uuid;
    }

    /**
     * 获取refresh_token存储uuid
     */
    public static String getRefreshTokenUuid(String token) {
        String subject = getClaim(token, "subject");
        if (subject == null || "refresh_token".equals(subject)) {
            throw new NotAccessTokenException("token认证失败，不是access_token!");
        }
        return getClaim(token, "uuid");
    }

    /**
     * 获取过期时间
     */
    public static Long getExpire(String token) {
        DecodedJWT decodedJWT = verityToken(token);
        return decodedJWT == null ? null : decodedJWT.getExpiresAt().getTime();
    }


    /**
     * 获取Payload信息
     */
    private static String getClaim(String token, String key) {
        DecodedJWT decodedJWT = verityToken(token);
        return decodedJWT == null ? null : decodedJWT.getClaim(key).asString();
    }

    /**
     * 解析token
     */
    private static DecodedJWT verityToken(String token) {
        Assert.notNull(secret, "secret(密钥) 不能为空");
        Assert.notNull(issuer, "issuer(发行人) 不能为空");

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build(); //Reusable verifier instance
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
        }
        return null;
    }
}
