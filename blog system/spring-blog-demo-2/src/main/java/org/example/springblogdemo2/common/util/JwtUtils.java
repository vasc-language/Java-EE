package org.example.springblogdemo2.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-09
 * Time: 20:27
 */
@Slf4j
public class JwtUtils {
    private static String SECRET_STRING = "kGmiTrem5gU1+BDOlwPssDpkP50fNObF/wygI8oEPTk=";

    private static Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_STRING));

    /**
     * 生成令牌
     * @param claims
     * @return
     */
    public static String genToken(Map<String, Object> claims){
        String compact = Jwts.builder()
                .setClaims(claims)
                .signWith(key)
                .compact();
        return compact;
    }

    /**
     * 解析身份令牌
     * @param token
     * @return
     */
    public static Claims parseToken(String token){
        if (!StringUtils.hasLength(token)) {
            return null;
        }
        JwtParser build = Jwts.parserBuilder().setSigningKey(key).build();
        // 异常进行捕获

        Claims claims = null;
        try {
            claims = build.parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("token 解析失败，token：" + token);
        }

        return claims;
    }
}
