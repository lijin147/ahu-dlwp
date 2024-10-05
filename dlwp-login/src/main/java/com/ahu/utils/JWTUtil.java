package com.ahu.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * 创建和校验JWT   JWT的结构：头部（加密算法），负载（数据信息），签名
 *
 * 具体构成步骤1、使用BASE64对头部和负载进行编码
 *        步骤2、使用头部中的加密算法，对进行BASE64编码的头部和负载以及一个密钥进行加密得到签名
 *        步骤3、将上述三者合并得到JWT
 * HMAC-SHA256(base64UrlEncode(header) + '.'+ base64UrlEncode(payload), secret)
 */
public class JWTUtil {
    /**
     * 创建JWT
     * @param secretKey 头部
     * @param ttlMillis 过期时间
     * @param claims 负载
     * @return JWT
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims){
        //指定头部
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        //指定JWT的过期时间
        long time = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(time);
        //构建JWT
        String JWT = Jwts.builder()
                //头部和密钥
                .signWith(algorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                //负载a
                .setClaims(claims)
                .setExpiration(exp).compact();
        return JWT;
    }

    /**
     * JWT进行解密的时候还需要验证有效性，所以需要secretKey
     * @param secretKey 密钥
     * @param token JWT
     * @return
     */
    public static Claims parseJWT(String secretKey,String token){
        Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
        return claims;
    }
}
