package utils;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * Description: 把一些重复的代码封装起来
 * User: 姚东名
 * Date: 2024-10-19
 * Time: 12:20
 */
public class DBUtil {
    // 数据源
    private static DataSource dataSource = null;
    // 数据库连接串
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/javahome?characterEncoding=utf8" +
            "&allowPublicKeyRetrieval=true&useSSL=false";
    // 用户名
    private static final String USER = "root";
    // 密码
    private static final String PASSWORD = "212409";

    //当类加载到JVM的时候，执行数据源的初始化
    static {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(URL);
        mysqlDataSource.setUser(USER);
        mysqlDataSource.setPassword(PASSWORD);
        dataSource = mysqlDataSource;
    }

    /**
     * 构造方法私有化，防止new这个对象
     */
    private DBUtil() {};

    /**
     * 获取数据库连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 依次释放资源，关闭连接
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
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
