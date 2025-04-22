package spring.book.je20250422springbookdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.book.je20250422springbookdemo.mapper.UserInfoMapper;
import spring.book.je20250422springbookdemo.model.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-22
 * Time: 21:38
 */
@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo QueryUserInfoByName(String username) {
        return userInfoMapper.queryUserInfoByName(username);
    }
}
