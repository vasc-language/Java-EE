package org.example.springblogdemo.service;

import org.example.springblogdemo.pojo.request.UserLoginRequest;
import org.example.springblogdemo.pojo.response.UserLoginResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-05
 * Time: 10:38
 */
public interface UserService {
    UserLoginResponse checkPassword(UserLoginRequest userLoginRequest);

}
