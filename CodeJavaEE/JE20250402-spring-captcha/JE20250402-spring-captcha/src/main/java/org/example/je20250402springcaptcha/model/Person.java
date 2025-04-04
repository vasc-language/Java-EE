package org.example.je20250402springcaptcha.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-02
 * Time: 20:51
 */
// @Component -> @ConfigurationProperties -> @Autowired
    // 1. 将 Person 的对象交给 Spring 容器管理
    // 2. 将 yml 配置文件的对应数据对 Person对象 赋值
    // 3. 从 Spring 容器中拿出来
@ConfigurationProperties(prefix = "person")
@Component
@Data
public class Person {
    private Integer id;
    private String name;
    private Integer age;
}
