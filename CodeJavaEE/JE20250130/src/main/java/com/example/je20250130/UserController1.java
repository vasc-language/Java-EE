package com.example.je20250130;

import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-02-10
 * Time: 16:54
 */
@RestController
@RequestMapping("/UserController1")
public class UserController1 {
    // 既支持GET ，又支持 POST
    @RequestMapping("/m1")
    public String m1() {
        return "m1";
    }

    // 只支持 GET
    @RequestMapping(value = "/m2", method = RequestMethod.GET)
    public String m2() {
        return "m2";
    }
    // 只支持 POST
    @RequestMapping(value = "/m3", method = RequestMethod.POST)
    public String m3() {
        return "m3";
    }

    // 简写，只支持 GET
    @GetMapping("/m4")
    public String m4() {
        return "m4";
    }
    // 简写，只支持 POST
    @PostMapping("/m5")
    public String m5() {
        return "m5";
    }
}
