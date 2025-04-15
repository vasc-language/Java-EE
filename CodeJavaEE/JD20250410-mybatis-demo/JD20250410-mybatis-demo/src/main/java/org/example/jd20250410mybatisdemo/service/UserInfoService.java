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

    // 比较 #{}  和 ${}
    public UserInfo queryUserByNameAndPassword4(String username, String password) {
        List<UserInfo> userInfos = userInfoMapper.selectByNameAndPassword4(username, password);
        /**
         * 对 #{} 和 ${} 进行测试
         * #{} 按照设想，只能查询出一条数据
         * ${} 按照设想，可以查询出多条数据
         */
        if (userInfos != null && userInfos.size() >= 1) {
            return userInfos.get(0);
        }
        return null;
    }

    public UserInfo queryUserByNameAndPassword5(String username, String password) {
        List<UserInfo> userInfos = userInfoMapper.selectByNameAndPassword5(username, password);
        if (userInfos != null && userInfos.size() >= 1){
            // ${} 按照设想，可以查询出多条数据
            return userInfos.get(0);
        }
        return null;
    }
}
