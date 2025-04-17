package org.example.jd20250417springbookdemo.service;

import org.example.jd20250417springbookdemo.mapper.UserInfoMapper;
import org.example.jd20250417springbookdemo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-17
 * Time: 21:29
 */
@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo queryUserNameByName(String username) {
        return userInfoMapper.queryUserInfoByName(username);
    }
}
