package com.example.demo3.utiles;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtile {

    //获取数据库连接数对象
    public static DataSource getDataSource() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:/Java/java-ee-beginner/CodeJavaEE/demo3/src/druid.properties"));
        //数据库连接池  多个连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        return dataSource;
    }

    //获取连接对象Connection
    public static Connection getConnection() throws Exception {
       return getDataSource().getConnection();

    }

    //获取执行对象Statement
    public static Statement getStatement() throws Exception {
        return getConnection().createStatement();
    }

}
