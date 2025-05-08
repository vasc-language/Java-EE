package org.example.springblogdemo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.Signature;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-06
 * Time: 9:11
 */
public class JwtTest {
    /**
     * 主要功能是生成 JWT 令牌
     */
    @Test
    void getToken() {
        /**
         * 创建签名密钥
         *   - 首先使用 Keys.hmacShaKeyFor() 创建一个 HMAC-SHA 密钥
         *   - 密钥基于一个 Base64 编码的字符串，通过 getByte() 转换为字符数组
         *   - 这个密钥用于对 JWT 进行签名，确保令牌的完整性和真实性
         */
        // "q79wkbVX95O2Y8NA4BVOQX0DggZSJOpTQcp86Mf01a0=" 是一串密钥
        // 通过 Keys.hmacShaKeyFor() 方法将这个Base64 字符串转换为 HMAC-SHA 签名密钥
        Key key = Keys.hmacShaKeyFor("l9O1suC3S7vP9AUHcwrVyoouX+X6Xe7vXjc7NQft+z4=".getBytes(StandardCharsets.UTF_8));

        // 设置声明（Claims）这些信息将成为 JWT 有效载荷部分
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 45);
        claims.put("name", "zhangsan");

        /**
         * JWT 的组成部分
         * - 头部(Header)：包括令牌类型和算法信息
         * - 负载(Payload)：包含声明信息，如用户ID等信息
         * - 签名(Signature)：确保数据完整性
         */
        String compact = Jwts
                .builder() // 创建 JWT 构建起
                .setClaims(claims) // 设置 JWT 的有效载荷内容
                .signWith(key) // 使用前面创建的密钥对 JWT 进行签名
                .compact(); // 将 JWT 转换为紧凑的字符串形式
        System.out.println(compact);
    }

    /**
     * 主要功能是生成并输出用于 JWT 签名的密钥
     * 1. 创建随机密钥
     *   - 使用 Keys.secretKeyFor(SignatureAlgorithm.HS256) 生成一个适用于 HMAC-SHA256 算法的随机密钥
     * 2. 编码密钥：
     *   - 使用 key.getEncoded() 获取密钥的原始字节表示
     *   - 然后通过 Encoders.BASE64.encode() 将其编码为 Base64 字符串
     *   - 这种格式便于存储和运输
     */
    @Test
    void getKey() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String encode = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(encode);
    }
}
