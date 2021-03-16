package com.itheima.jdbc.utils;

import java.sql.*;

/**
 * 包名:com.itheima.jdbc.utils
 * 作者:Leevi
 * 日期2019-04-06  10:42
 * 优化JdbcUtil
 * 将常量设置成成员变量
 */
public class JdbcUtil_2 {
    private static String driverClass = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql:///day17";
    private static String username = "root";
    private static String password = "root";
    static {
        try {
            //1.注册驱动
            Class.forName(driverClass);
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
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    /**
     * 关闭资源的方法
     * @param conn
     * @param stm
     * @throws SQLException
     */
    public static void close(Connection conn, Statement stm) throws SQLException {
        close(conn,stm,null);
    }

    /**
     * 关闭资源的方法
     * @param conn
     * @param stm
     * @param rst
     * @throws SQLException
     */
    public static void close(Connection conn, Statement stm, ResultSet rst) throws SQLException {
        if (rst != null){
            rst.close();
        }
        if(stm != null){
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}
