package org.example.je20250402springcaptcha.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-02
 * Time: 19:58
 */
@RequestMapping("/prop")
@ResponseBody
@Controller
public class PropertiesController {
    @Value("${my.key}")
    private Integer myKey;

    @Value("${my.key2}")
    private boolean myKey2;

    @RequestMapping("/read")
    public String readValue(){
        return "读取配置文件my.key:"+ myKey;
    }


    @PostConstruct
    public void init() {
        System.out.println("mykey: " + myKey);
        System.out.println("mykey: " + myKey2);
    }
}
