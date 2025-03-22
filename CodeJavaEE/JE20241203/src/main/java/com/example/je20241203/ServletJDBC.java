package com.example.je20241203; /**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-12-03
 * Time: 10:31
 */

import com.example.je20241203.mode.User;
import com.example.je20241203.utiles.JDBCUtile;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletJDBC", value = "/ServletJDBC")
public class ServletJDBC extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取数据库连接池
            QueryRunner runner = new QueryRunner(JDBCUtile.getDataSource());
            // 条件查询
            String sql = "SELECT * FROM user WHERE uname = '李四';";
            // 执行SQL语句
            User user = (User) runner.query(sql, new BeanHandler<User>(User.class));
            System.out.println(user);

            // 全列查询
            String sql2 = "SELECT * FROM user;";
            List<User> users = runner.query(sql2, new BeanListHandler<User>(User.class));
            System.out.println(users);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
