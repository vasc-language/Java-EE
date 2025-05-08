package org.example.springblogdemo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description: 令牌技术
 * User: 姚东名
 * Date: 2025-05-06
 * Time: 8:54
 */
public class JwtUtils {
    private static String SECRET_STRING = "kGmiTrem5gU1+BDOlwPssDpkP50fNObF/wygI8oEPTk=";
    private static Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_STRING));

    public static String genToken(Map<String, Object> claims) {
        String compact = Jwts
                .builder() // 创建 JWT 构建器
                .setClaims(claims) // 设置 JWT 的有效载荷内容
                .signWith(key) // 使用前面静态的密钥对 JWT 进行签名
                .compact(); // 将 JWT 转换为紧凑的字符串形式
        return compact; // 生成一个密钥返回
    }


    /**
     * 方法步骤
     * 1. 创建 JWT 解析器
     *   - Jwts.parserBuilder()：创建一个 JWT 解析器构造器
     *   - .setSigningKey(key)：设置用于验证 JWT 签名的密钥，这里使用的是预先定义的静态密钥变量
     *   - .build()：创建解析器实例
     * 2. 验证并解析令牌
     *   - build.parseClaimsJws(token)：验证 JWT 的签名并解析令牌内容
     *   - 如果签名无效（令牌被篡改）或令牌格式错误，此处会抛出异常
     *   - getBody()：获取令牌的有效载荷部分（Claims 对象）
     * @param token
     * @return
     */
    public static Claims parseToken(String token) {
        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        Claims claims = build.parseClaimsJws(token).getBody();
        return claims; // 解析令牌 进行返回
    }

    /**
     * 生成令牌
     * @param claims
     * @return
     *//*
    public static String genToken(Map<String, Object> claims){
        String compact = Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
        return compact;
    }

    *//**
     * 解析令牌
     * @param token
     * @return
     *//*
    public static Claims parseToken(String token){
        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        Claims claims = build.parseClaimsJws(token).getBody();
        return claims;
    }*/

}
