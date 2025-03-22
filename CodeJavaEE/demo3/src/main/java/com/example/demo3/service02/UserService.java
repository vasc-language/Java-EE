package com.example.demo3.service02;

import com.example.demo3.dao.UserDAO;
import com.example.demo3.mode.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 接收数据库连接
 * User: 姚东名
 * Date: 2024-12-03
 * Time: 15:48
 */
public class UserService {
    UserDAO userDAO = new UserDAO();

    public List<User> selectUserList() {
        List<User> users = null;
        try {
            users = userDAO.selectUserList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
