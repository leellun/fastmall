package com.newland.mall.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类
 */
public class JwtTokenUtil implements Serializable {

    private static final String CLAIM_KEY_Obj = "sub";

    /**
     * 30分钟(毫秒)
     */
    private static final long EXPIRATION_TIME = 1800000;
    /**
     * JWT密码
     */
    private static final String SECRET = "mini-mall";

    public static String getToken(String content) {
        Map<String, Object> claims = new HashMap<>(16);
        claims.put(CLAIM_KEY_Obj, content);
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + EXPIRATION_TIME * 1000);
        return Jwts.builder()
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, generalKey())
                .compact();
    }

    public static boolean verifyToken(String token) {
        try {
            Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从token中获取用户名
     */
    public static String getContentByToken(String token) {
        return getTokenClaim(token).getSubject();
    }

    /**
     * 从token中获取用户id
     */
    public static String getUserIdByToken(String token) {
        return getTokenClaim(token).getAudience();
    }

    /**
     * 获取token中注册信息
     *
     * @param token
     * @return
     */
    private static Claims getTokenClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(generalKey())
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将secret加密
     *
     * @return
     */
    private static SecretKey generalKey() {
        String stringKey = SECRET;
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}