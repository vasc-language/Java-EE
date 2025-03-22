package org.example.je20250307springbook.controller;

import org.example.je20250307springbook.model.UserInfo;
import org.example.je20250307springbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 二者是上下游协作关系 @Repository 生产 Bean（标志数据仓库）
 *                               @Autowired 消费 Bean（获取数据仓库）
 * User: 姚东名
 * Date: 2025-03-06
 * Time: 20:24
 */
@RequestMapping("book")
@RestController
public class BookController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getList")
    public List<UserInfo> getList() {
        return userService.getList();
    }
}
