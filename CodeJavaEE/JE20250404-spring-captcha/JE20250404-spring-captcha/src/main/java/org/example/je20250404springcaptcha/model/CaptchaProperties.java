package org.example.je20250404springcaptcha.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-04
 * Time: 9:52
 */
@ConfigurationProperties(prefix = "captcha")
@Configuration
@Data
public class CaptchaProperties {
    // 验证码的长和宽 验证的时间长短
    private Integer width;
    private Integer height;
    private Session session;

    @Data
    public static class Session {
        private String key;
        private String date;
    }
}
