package org.example.springblogdemo2;

import org.example.springblogdemo2.common.util.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-11
 * Time: 11:24
 */
public class MD5Test {
    /**
     * 加密
     * 盐值 + 明文（密码） -> 进行加密 -> 密文 -> 明文（密码） + 密文 -> sqlPassword -> 存储到数据库中
     */
    @Test
    public void encrypt() {
        String password = "123456";
        String salt = UUID.randomUUID().toString().replace("-", ""); // 将生成的32位密文中间的 "-" 去掉
        System.out.println("盐值：" + salt);
        // (盐值 + 密码) -> 进行加密
        String digestAsHex = DigestUtils.md5DigestAsHex((salt + password).getBytes(StandardCharsets.UTF_8));
        System.out.println("密文：" + digestAsHex);
    }

    /**
     * 解密
     * sqlPassword = 盐值 + 密文（盐值 + 明文（密码））
     */
    @Test
    public void verify() {
        String sqlPassword = "0b741daf40dd4a759a8869ea3ad262dc78eeeff48cc49984a39d664028775cd7";
        String inputPassword = "123456";
        String salt = sqlPassword.substring(0, 32); // 0b741daf40dd4a759a8869ea3ad262dc
        String digestAsHex = DigestUtils.md5DigestAsHex((salt + inputPassword).getBytes(StandardCharsets.UTF_8)); // 密文（盐值 + 密码）
        System.out.println(sqlPassword.equals(salt + digestAsHex));
    }

    /**
     * 生成 sqlPassword 放到数据中
     */
    @Test
    void test() {
        String encrypt = SecurityUtils.encrypt("123456");
        // ab126a8df12940c6a7bc5e2fb61340f618f7de5232d1ece0329d5a2141d88a46
        // 139c8eaaba314c2db5dd8b137279a62bd8b4c6912e6af10c8799eb7c6f0d3621
        System.out.println(encrypt);
        boolean verify = SecurityUtils.verify("123456", encrypt);
        System.out.println(verify);
    }
}
