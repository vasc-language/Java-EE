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

    /**
     *用户登陆界面
     * @param username
     * @param password
     * @return true 密码正确 false 密码错误
     */
    @RequestMapping("/login4")
    public boolean login1(String username, String password) {
        UserInfo userInfos = userInfoService.queryUserByNameAndPassword4(username, password);
        return userInfos == null ? false : true;
    }

    @RequestMapping("/login5")
    public boolean login2(String username, String password) {
        UserInfo userInfos = userInfoService.queryUserByNameAndPassword5(username, password);
        return userInfos == null ? false : true;
    }
}
