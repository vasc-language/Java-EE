package org.example.je20250402springcaptcha.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-02
 * Time: 21:41
 */
@ConfigurationProperties(prefix = "captcha")
@Configuration
@Data
public class CaptchaProperties {
    private Integer width;
    private Integer height;
    private Session session;
    @Data
    public static class Session {
        private String Key;
        private String date;
    }

}
