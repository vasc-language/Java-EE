package Demo;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-10-19
 * Time: 11:44
 */
public class Demo02_DataSource {
    public static void main(String[] args) {
        // 定义MySQL数据源对象
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        // 设置数据库连接串
        mysqlDataSource.setURL("jdbc:mysql://127.0.0.1:3306/javahome?characterEncoding=utf8" +
                "&allowPublicKeyRetrieval=true&useSSL=false");
        // 用户名
        mysqlDataSource.setUser("root");
        // 密码
        mysqlDataSource.setPassword("212409");

        // 定义JDBC数据源对象
        DataSource dataSource = mysqlDataSource;

        // 定义连接对象
        Connection connection = null;
        // 定义预处理SQL执行对象
        PreparedStatement statement = null;
        // 定义结果集对象
        ResultSet resultSet = null;

        try {
            // 1. 通过数据源获取数据库连接
            connection = dataSource.getConnection();
            // 2. 获取预处理SQL执行对象
            // 定义要执行的SQL
            String sql = "SELECT student_id, sn, `name`, mail, class_id FROM student WHERE `name` = ?";
            statement = connection.prepareStatement(sql);
            // 接受用户的输入
            System.out.println("请输入学生姓名：");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.next();
            // 3. 用真实值替换占位符（?）
            statement.setString(1, name);
            // 4. 执行SQL，获取结果集
            resultSet = statement.executeQuery();
            // 5. 遍历结果集
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
        } catch (SQLException e) {
            e.printStackTrace();
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




