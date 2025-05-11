package org.example.springblogdemo2.common.util;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-11
 * Time: 11:48
 */
public class SecurityUtils {
    /**
     * 加密
     * md5(盐值 + 明文) = 密文
     * 盐值 + 密文 = sqlPassword（数据库中最终存储的密码）
     * @param password 明文
     * @return
     */
    public static String encrypt(String password) {
        String salt = UUID.randomUUID().toString().replace("-", "");
        String securityPassword = DigestUtils.md5DigestAsHex((salt + password).getBytes(StandardCharsets.UTF_8));
        // 最终返回 sqlPassword
        return salt + securityPassword;
    }

    /**
     * 解密
     * @param inputPassword 明文
     * @param sqlPassword 盐值 + md5(盐值 + 明文)
     * @return
     */
    public static boolean verify(String inputPassword, String sqlPassword) {
        if (!StringUtils.hasLength(inputPassword)) {
            return false;
        }
        if (sqlPassword == null || sqlPassword.length() != 64) {
            return false;
        }

        String salt = sqlPassword.substring(0, 32);
        String securityPassword = DigestUtils.md5DigestAsHex((salt + inputPassword).getBytes(StandardCharsets.UTF_8));

        return sqlPassword.equals(salt + securityPassword);
    }
}
