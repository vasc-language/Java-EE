package com.example.je20241203.utiles;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-12-03
 * Time: 10:29
 */
public class JDBCUtile {
    // 获取数据库连接对象
    public static DataSource getDataSource() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:/Java/java-ee-beginner/CodeJavaEE/JE20241203/src/main/druid.properties"));
        // 连接数据库连接池 连接多个数据
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        return dataSource;
    }

    // 获取数据库连接对象 Connection
    public static Connection getConnection() throws Exception {
        return getDataSource().getConnection();
    }

    // 获取执行对象
    public static Statement getStatement() throws Exception {
        return getConnection().createStatement();
    }
}
