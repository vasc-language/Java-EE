package Demo;

import java.sql.*;
import java.text.MessageFormat;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-10-19
 * Time: 10:29
 */
public class Demo01_DriverManager  {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        //PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            // 1. 加载数据库厂商提供的驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javahome?characterEncoding=utf8" +
                    "&allowPublicKeyRetrieval=true&useSSL=false", "root", "212409");
            // 3. 创建Statement对象
            statement = connection.createStatement();
            // 4. 定义SQL并执行SQL语句
            System.out.println("请输入姓名：");
            Scanner scanner = new Scanner(System.in);
            // 接受用户的输入
            String name = scanner.next();
            String sql = "SELECT student_id, sn, `name`, mail, class_id FROM student WHERE `name` = '" + name + "'";
            // 5. 执行SQL，获取查询结果
            resultSet = statement.executeQuery(sql);
                        //statement.executeLargeUpdate(sql);
            // 6. 对结果进行遍历，获取数据
            //如果下一条有记录，就返回true，否则就返回false -> 迭代器遍历
            while (resultSet.next()) {
                //获取学生id
                long stuId = resultSet.getLong(1);
                String stuSn = resultSet.getString(2);
                String stuName = resultSet.getString(3);
                String stuMail = resultSet.getString(4);
                long classId = resultSet.getLong(5);
                System.out.println(MessageFormat.format("学生编号={0}, 学号={1}, 学生姓名={2}, 邮箱={3}, 班级编号={4}", stuId, stuSn,
                        stuName, stuMail, classId));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            //依次释放资源，关闭连接
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
