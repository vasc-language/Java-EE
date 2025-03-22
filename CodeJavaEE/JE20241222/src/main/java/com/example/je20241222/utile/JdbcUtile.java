package com.example.je20241222.utile;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
// import java.io.FileNotFoundException;
// import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2024-12-22
 * Time: 23:03
 */
public class JdbcUtile {
    // 1. 获取数据库连接池对象DataSource
    public static DataSource getDataSource() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("D://Java//java-ee-beginner//CodeJavaEE//JE20241222//src//druid.properties"));
        // 数据库连接池 连接多个对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        return dataSource;
    }
    // 2. 获取连接对象Connection
    public static Connection getConnection() throws Exception {
        return getDataSource().getConnection();
    }
    // 3. 获取执行对象Statement
    public static Statement getStatement() throws Exception {
        return getConnection().createStatement();
    }
}
