package spring.book.je20250422springbookdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description: 测试 config 中的 ExceptionAdvice
 * User: 姚东名
 * Date: 2025-04-25
 * Time: 22:01
 */
@RequestMapping("/test")
@RestController
public class TestController {
    // 1. 测试 Exception
    @RequestMapping("/t1")
    public Integer t1() {
        int a = 10 / 0;
        return 1;
    }

    // 2. 测试 NullPointerException
    @RequestMapping("/t2")
    public Boolean t2() {
        int[] a = {1, 2, 3};
        System.out.println(a[3]);
        return true;
    }

    // 3. 测试 IndexOutOfBoundsException
    @RequestMapping("/t3")
    public String t3() {
        String a = null;
        System.out.println(a.length());
        return "t3";
    }
}
