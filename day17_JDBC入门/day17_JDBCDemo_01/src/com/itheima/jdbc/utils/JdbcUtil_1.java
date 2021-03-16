package com.itheima.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 包名:com.itheima.jdbc.utils
 * 作者:Leevi
 * 日期2019-04-06  10:42
 * 1. 完成注册驱动，注册驱动这个工作不用每次调用方法都注册，只需要在程序执行的时候注册一次就行了
 * 2. 封装获得连接对象的方法
 * 3. 封装关闭资源的方法
 *
 * 怎么样封装一个方法
 * 1.确定到底是public、private的
 * 2.确定返回值类型:就看调用者需要什么，它需要什么我们就返回什么
 * 3.确定参数类型:方法内需要什么数据，就将这些数据作为参数传递过来
 */
public class JdbcUtil_1 {
    static {
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接的方法
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "root");
        return conn;
    }

    /**
     * 关闭资源的方法
     * @param conn
     * @param stm
     * @throws SQLException
     */
    public static void close(Connection conn, Statement stm) throws SQLException {
        stm.close();
        conn.close();
    }
}
