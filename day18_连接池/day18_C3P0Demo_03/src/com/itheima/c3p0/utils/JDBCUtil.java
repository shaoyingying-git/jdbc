package com.itheima.c3p0.utils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 包名:com.itheima.utils
 * 作者:Leevi
 * 日期2019-04-07  11:05
 *
 * C3P0的使用步骤:
 * 1.引入jar
 * 2.编写配置文件
 * 3.创建连接池对象
 * 4.使用连接池对象的getConnection()方法获取连接
 * 5.调用connection对象的close()方法就归还连接
 */
public class JDBCUtil {
    private static DataSource dataSource;
    static {
        //1.创建C3P0连接池的对象
        dataSource = new ComboPooledDataSource();
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
