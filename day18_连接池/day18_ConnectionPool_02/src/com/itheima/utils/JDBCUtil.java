package com.itheima.utils;

import com.itheima.pool.ConnectionPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 包名:com.itheima.utils
 * 作者:Leevi
 * 日期2019-04-07  11:05
 */
public class JDBCUtil {
    private static DataSource dataSource;
    static {
        dataSource = new ConnectionPool();
    }

    public static Connection getConnection() throws SQLException {
        //获取连接,从连接池获取
        return dataSource.getConnection();
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
