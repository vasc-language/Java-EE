package org.example.springblogdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.springblogdemo.exception.BlogException;
import org.example.springblogdemo.mapper.UserInfoMapper;
import org.example.springblogdemo.pojo.dataobject.UserInfo;
import org.example.springblogdemo.pojo.request.UserLoginRequest;
import org.example.springblogdemo.pojo.response.UserLoginResponse;
import org.example.springblogdemo.service.UserService;
import org.example.springblogdemo.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-05
 * Time: 10:39
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * @param userLoginRequest 前端发送过来的请求对象
     * @return
     */
    @Override
    public UserLoginResponse checkPassword(UserLoginRequest userLoginRequest) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .lambda()
                .eq(UserInfo::getDeleteFlag, 0)
                .eq(UserInfo::getUserName, userLoginRequest.getUserName());
        // 查询数据库 userInfo 是数据库中的对象
        // TODO 添加 try catch
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        if (userInfo == null) {
            // 用户名不存在
            throw new BlogException("用户名不存在");
        }
        // 判断密码是否正确
        if (!userLoginRequest.getPassword().equals(userInfo.getPassword())) {
            throw new BlogException("用户输入密码错误");
        }
        // 密码正确
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userInfo.getId());
        claims.put("name", userInfo.getUserName());
        String token = JwtUtils.genToken(claims); // 令牌技术
        return new UserLoginResponse(userInfo.getId(), token);
    }
}
