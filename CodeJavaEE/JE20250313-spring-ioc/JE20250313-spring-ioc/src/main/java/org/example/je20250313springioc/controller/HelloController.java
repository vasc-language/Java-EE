package org.example.je20250313springioc.controller;

import org.springframework.stereotype.Controller;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-20
 * Time: 21:19
 */

/**
 * BeanName 默认的类名
 * HelloController ——> helloController（BeanName 变成小驼峰）
 * BookDao ——> bookDao（BeanName 变成小驼峰）
 * USController ——>USController（BeanName 等于本身）
 *
 * 也可以通过注释进行指定
 */
@Controller("HelloController")
public class HelloController {
    public void print() {
        System.out.println("do helloController");
    }
}
