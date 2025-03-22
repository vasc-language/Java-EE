package com.example.je20241123;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: JDBC数据库连接
 * User: 姚东名
 * Date: 2024-11-23
 * Time: 15:12
 */
public class JdbcDemo {
    // 一：更新操作
    public void JDBCUpdate() throws ClassNotFoundException, SQLException {
        // 1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接对象 Connection
        String url = "jdbc:mysql://127.0.0.1:3306/javahome?characterEncoding=utf8";
        String uname = "root";
        String password = "212409";
        Connection connection = DriverManager.getConnection(url, uname, password);
        // 3. 获取执行对象 Statement
        Statement statement = connection.createStatement();
        // 4. 执行SQL语句
        String sql = "UPDATE student SET sn = '09984' WHERE student_id = 1;";
        int count = statement.executeUpdate(sql);
        if (count != 0) {
            System.out.println("修改成功！");
        } else {
            System.out.println("修改失败！");
        }
        // 5. 关闭链接，释放资源
        statement.close();
        connection.close();
    }

    // 二：增、删、查操作
    public List<Student> JDBCSelect() throws ClassNotFoundException, SQLException {
        // 1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接对象 Connection
        String url = "jdbc:mysql://127.0.0.1:3306/javahome?characterEncoding=utf8";
        String uname = "root";
        String password = "212409";
        Connection connection = DriverManager.getConnection(url, uname, password);
        // 3. 获取执行对象 Statement
        Statement statement = connection.createStatement();
        // 4. 执行SQL语句
        String sql = "SELECT * FROM `student`;";
        // 结果集对象
        ResultSet resultSet = statement.executeQuery(sql);
        // 如果下一条有记录，就返回true，否则就返回false -> 迭代器遍历
        List<Student> studentList = new ArrayList<>();
        while (resultSet.next()) {
            Student student = new Student();
            // 把结果集中的数据放到 student 对象去
            String id = resultSet.getString("id");
            String sn = resultSet.getString("sn");
            String name = resultSet.getString("uname");
            String mail = resultSet.getString("mail");
            int classId = resultSet.getInt("classId");

            student.setId(id);
            student.setSn(sn);
            student.setUname(name);
            student.setMail(mail);
            student.setClassId(classId);
            // student 对象赋值完 放到 studentList 集合中
            studentList.add(student);
        }
        /*List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            // 把结果集中的数据放到 User对象去
            String id = resultSet.getString("id");
            String uname = resultSet.getString("uname");
            String password1 = resultSet.getString("password");
            int age = resultSet.getInt("age");
            int gender = resultSet.getInt("gender");

            user.setId(id);
            user.setUname(uname);
            user.setPassword(password1);
            user.setAge(age);
            user.setGender(gender);
            // user对象赋值完 放到 UserList 集合中
            userList.add(user);
        }*/
        // 5. 关闭链接，释放资源
        statement.close();
        connection.close();

        return studentList;
    }

    // 三：
    public Student JDBCLogin(String uname, String sn) throws ClassNotFoundException, SQLException {
        // 1. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2. 获取连接对象
        String url = "jdbc:mysql://127.0.0.1:3306/javahome?characterEncoding=utf8";
        String username = "root";
        String password = "212409";
        Connection connection = DriverManager.getConnection(url, username, password);
        // 3. 获取执行对象 Statement
        Statement statement = connection.createStatement();
        // 4. 执行SQL语句
        String sql = "SELECT * FROM student WHERE `name` = '" + uname + "' AND sn = ' + " + sn + "';";
        // 结果集对象
        ResultSet resultSet = statement.executeQuery(sql);
        Student student = new Student();
        // 迭代器遍历
        while (resultSet.next()) {
            // 把结果集中的数据放到 student 对象去
            String id = resultSet.getString("id");
            String sn1 = resultSet.getString("sn");
            String uname1 = resultSet.getString("uname");
            String mail = resultSet.getString("mail");
            int classId = resultSet.getInt("classId");

            student.setId(id);
            student.setSn(sn1);
            student.setUname(uname1);
            student.setMail(mail);
            student.setClassId(classId);
        }
        // 5. 关闭链接，释放资源
        statement.close();
        connection.close();

        return student;
    }
}
