package org.example.je20250402springcaptcha.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-02
 * Time: 20:50
 */
@ConfigurationProperties(prefix = "dbtypes")
@Component
@Data
public class DbTypes {
    private Integer id;
    private List<String> name;
    private Map<String, String> ball;
}
