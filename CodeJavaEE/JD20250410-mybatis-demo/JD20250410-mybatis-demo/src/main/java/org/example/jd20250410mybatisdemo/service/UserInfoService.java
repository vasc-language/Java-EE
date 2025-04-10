package org.example.jd20250410mybatisdemo.service;

import org.example.jd20250410mybatisdemo.mapper.UserInfoMapper;
import org.example.jd20250410mybatisdemo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-10
 * Time: 7:59
 */
@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    // 1. 传递参数
    // 全列查询
    public List<UserInfo> getAllUser() {
        return userInfoMapper.selectAll();
    }

    // 根据 ID 来查询
    public UserInfo getAllById(Integer id) {
        return userInfoMapper.selectAllById(id);
    }

    public UserInfo getUserInfoById2(Integer id) {
        return userInfoMapper.selectAllById2(id);
    }

    
    // 2. 传递多个参数
    public List<UserInfo> selectByNameAndPassword(String userName, String password) {
        return userInfoMapper.selectByNameAndPassword(userName, password);
    }
}
