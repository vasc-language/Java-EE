package org.example.springblogdemo2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.springblogdemo2.common.exception.BlogException;
import org.example.springblogdemo2.mapper.UserInfoMapper;
import org.example.springblogdemo2.pojo.dataobject.UserInfo;
import org.example.springblogdemo2.pojo.request.UserLoginRequest;
import org.example.springblogdemo2.pojo.response.UserLoginResponse;
import org.example.springblogdemo2.service.UserService;
import org.example.springblogdemo2.common.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-08
 * Time: 21:42
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public UserLoginResponse checkPassword(UserLoginRequest userLoginRequest) {
        // 构造器
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UserInfo::getUserName, userLoginRequest.getUsername()) // 使用 username 来查
                .eq(UserInfo::getDeleteFlag, 0);

        // 查询数据库
        UserInfo userInfo = null;
        try {
            userInfo = userInfoMapper.selectOne(queryWrapper);
        } catch (Exception e) {
            throw new BlogException("数据库查询失败：" + e.getMessage());
        }

        // 进行验证
        /**
         * 使用 userLoginRequest 中的用户名和密码（username，password）
         * 返回一个用户ID和身份令牌（userId，token）
         */
        if (userInfo == null) {
            // 用户不存在
            throw new BlogException("用户不存在");
        }

        if (!userLoginRequest.getPassword().equals(userInfo.getPassword())) {
            throw new BlogException("用户密码错误");
        }

        // 密码正确
        Map<String ,Object> map = new HashMap<>();
        map.put("userId", userInfo.getId());
        map.put("name", userInfo.getUserName());

        // 生成一个身份令牌
        String token = JwtUtils.genToken(map);

        return new UserLoginResponse(userInfo.getId(), token);
    }
}
