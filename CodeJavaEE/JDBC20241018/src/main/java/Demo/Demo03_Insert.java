package Demo;

import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-10-19
 * Time: 12:35
 */
public class Demo03_Insert {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        //插入操作需不需要定义结果集对象？ -> 不需要，因为insert返回的是受影响的条数
        try {
            // 1. 获取数据库连接
            connection = DBUtil.getConnection();
            // 2. 定义SQL
            String sql = "INSERT INTO student (sn, `name`, mail, class_id) VALUES (?, ?, ?, ?)";
            // 3. 定义SQL与处理对象
            statement = connection.prepareStatement(sql);
            // 4. 接受用户的输入
            System.out.println("请输入学号：");
            Scanner scanner = new Scanner(System.in);
            String sn = scanner.next();

            System.out.println("请输入学生姓名：");
            String name = scanner.next();

            System.out.println("请输入邮箱：");
            String mail = scanner.next();

            System.out.println("请输入班级编号：");
            Long classId = Long.valueOf(scanner.next());

            // 5. 用真实数据替换占位符
            statement.setString(1, sn);
            statement.setString(2, name);
            statement.setString(3, mail);
            statement.setLong(4, classId);
            // 6. 执行SQL获取结果，返回的是受影响的条数
            int row = statement.executeUpdate();
            if (row == 1) {
                System.out.println("插入成功");
            } else {
                System.out.println("插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放资源，关闭连接
            DBUtil.close(null, statement, connection);
        }
    }
}