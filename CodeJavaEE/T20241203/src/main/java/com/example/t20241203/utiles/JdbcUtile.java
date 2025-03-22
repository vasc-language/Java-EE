package com.example.t20241203.utiles;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * Description: 工具类
 * User: 姚东名
 * Date: 2024-12-03
 * Time: 10:01
 */
public class JdbcUtile {
    // 获取数据连接对象对象
    public static DataSource getDataSource() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:/Java/java-ee-beginner/CodeJavaEE/T20241203/src/test/druid.properties"));
        // 数据库连接池 连接多个对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

    }
}
