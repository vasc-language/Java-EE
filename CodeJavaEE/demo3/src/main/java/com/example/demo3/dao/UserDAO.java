package com.example.demo3.dao;

import com.example.demo3.mode.User;
import com.example.demo3.utiles.JdbcUtile;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 用户相关的数据操作
 * User: 姚东名
 * Date: 2024-12-03
 * Time: 14:55
 */
public class UserDAO {
    public List<User> selectUserList() throws Exception {
        // 引用数据库连接池 引用多个对象
        QueryRunner runner = new QueryRunner(JdbcUtile.getDataSource());
        // 执行SQL语句
        String sql = "SELECT * FROM user;";
        // 封装打包成
        List<User> users = runner.query(sql, new BeanListHandler<User>(User.class));
        return users;
    }
}
