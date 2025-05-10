package org.example.springblogdemo2.service;

import org.example.springblogdemo2.pojo.request.UserLoginRequest;
import org.example.springblogdemo2.pojo.response.UserInfoResponse;
import org.example.springblogdemo2.pojo.response.UserLoginResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-08
 * Time: 21:40
 */
public interface UserService {
    UserLoginResponse checkPassword(UserLoginRequest userLoginRequest);

    UserInfoResponse getUserInfo(Integer userId);

    UserInfoResponse getAuthorInfo(Integer blogId);
}
