package org.example.springblogdemo2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.example.springblogdemo2.common.exception.BlogException;
import org.example.springblogdemo2.common.util.BeanTransUtils;
import org.example.springblogdemo2.mapper.UserInfoMapper;
import org.example.springblogdemo2.pojo.dataobject.BlogInfo;
import org.example.springblogdemo2.pojo.dataobject.UserInfo;
import org.example.springblogdemo2.pojo.request.UserLoginRequest;
import org.example.springblogdemo2.pojo.response.UserInfoResponse;
import org.example.springblogdemo2.pojo.response.UserLoginResponse;
import org.example.springblogdemo2.service.BlogService;
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

    @Resource(name = "blogServiceImpl")
    private BlogService blogService;
    @Override
    public UserLoginResponse checkPassword(UserLoginRequest userLoginRequest) {
        // 构造器
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UserInfo::getUserName, userLoginRequest.getUserName()) // 使用 username 来查
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

    /**
     * 利用 userId 属性获取用户信息
     * @param userId
     * @return
     */
    @Override
    public UserInfoResponse getUserInfo(Integer userId) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UserInfo::getDeleteFlag, 0)
                .eq(UserInfo::getId, userId);
        UserInfo userInfo = userInfoMapper.selectOne(queryWrapper);
        // 对 userInfo 对象进行二次处理 变成 UserInfoResponse
        return BeanTransUtils.trans(userInfo);
    }

    /**
     * 获取作者的信息
     * @param blogId
     * @return
     */
    @Override
    public UserInfoResponse getAuthorInfo(Integer blogId) {
        // 1. 通过 blogId 获取博客详情（包括 作者ID）
        BlogInfo blogInfo = blogService.getBlogInfo(blogId);
        if (blogInfo == null || blogInfo.getUserId() < 1) {
            throw new BlogException("博客不存在");
        }
        // 2. 通过作者ID 获取作者的信息
        return getUserInfo(blogInfo.getUserId());
    }
}
