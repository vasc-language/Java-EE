package org.example.je20250307springbook.service;

import org.example.je20250307springbook.dao.BookDao;
import org.example.je20250307springbook.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: @Repository 相当于 Spring 管理仓库 @Autowired 相当于配送机器人
 * User: 姚东名
 * Date: 2025-03-06
 * Time: 20:24
 */
@Service
public class UserService {
    @Autowired // 自动从仓库取 BookRepository
    private BookDao bookDao;
    public List<UserInfo> getList() {
        // 对结果进行二次处理：将状态码转换为 字符串
        List<UserInfo> userInfoList = bookDao.mockData();
        for (UserInfo userInfo : userInfoList) {
            if (userInfo.getStatus() == 1) {
                userInfo.setStatusCN("可借阅");
            } else {
                userInfo.setStatusCN("不可借阅");
            }
        }
        return userInfoList;
    }
}
