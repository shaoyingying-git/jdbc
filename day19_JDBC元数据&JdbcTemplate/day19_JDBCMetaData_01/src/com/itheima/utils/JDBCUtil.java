package com.itheima.utils;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 包名:com.itheima.utils
 * 作者:Leevi
 * 日期2019-04-07  11:05
 *
 * DRUID连接池的使用步骤:
 */
public class JDBCUtil {
    private static DataSource dataSource;
    static {
        //1.使用连接池工厂创建Druid连接池的对象
        Properties properties = new Properties();
        //将配置文件转换成流
        ClassLoader classLoader = JDBCUtil.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("druidconfig.properties");
        try {
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
