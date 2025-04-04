package org.example.je20250402springcaptcha.controller;

import jakarta.annotation.PostConstruct;
import org.example.je20250402springcaptcha.configuration.CaptchaProperties;
import org.example.je20250402springcaptcha.model.DbTypes;
import org.example.je20250402springcaptcha.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-02
 * Time: 20:13
 */

@Controller
public class YmlController {
    // 方式一
    @Value("${my.key3}")
    private String myKey3;
    @Value("${my.key4}")
    private String myKey4;
    @Value("${person.age}")
    private Integer personAge;

//    @Value("${string.value}")
//    private String hello;
//    @Value("${null.value}")
//    private String nullValue;

    // 方式二
    @Autowired
    private Person person;
    @Autowired
    private DbTypes dbTypes;
    @Autowired
    private CaptchaProperties captchaProperties;

    @PostConstruct
    public void init() {
        System.out.println("myKey3: " + myKey3);
        System.out.println("personAge: " + personAge);
        System.out.println("==========================");
        System.out.println("person: " + person);
        System.out.println("dbTypes: " + dbTypes);
        System.out.println("==========================");
        System.out.println("captchaProperties: " + captchaProperties);
    }

}
