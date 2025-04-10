package org.example.jd20250410mybatisdemo.controller;

import org.example.jd20250410mybatisdemo.model.UserInfo;
import org.example.jd20250410mybatisdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-10
 * Time: 8:01
 */
@RequestMapping("/user")
@RestController
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/getAllUser")
    public List<UserInfo> getAllUser() {
        return userInfoService.getAllUser();
    }

    @RequestMapping("/getAllById")
    public UserInfo getAllById(Integer id) {
        return userInfoService.getAllById(id);
    }

    @RequestMapping("/getUserInfoById")
    public UserInfo getUserInfoById(Integer id) {
        return userInfoService.getUserInfoById2(id);
    }

    @RequestMapping("/getUserInfoByNameAndPassword")
    public List<UserInfo> getUserInfoByNameAndPassword(String userName, String password) {
        return userInfoService.selectByNameAndPassword(userName, password);
    }
}
