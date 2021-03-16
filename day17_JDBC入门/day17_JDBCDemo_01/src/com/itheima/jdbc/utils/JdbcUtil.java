package com.itheima.jdbc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 包名:com.itheima.jdbc.utils
 * 作者:Leevi
 * 日期2019-04-06  10:42
 * 优化JdbcUtil
 * 使用配置文件的方式，解决硬编码问题
 * 什么是硬编码:1.常量的硬编码   --- 使用配置文件解决，配置文件有两种:1.properties文件  2.xml文件
 * 使用配置文件的话一定要注意,配置文件放在什么地方:类路径下
 * Java程序的目录结构:1.java文件夹里面放Java代码  2.lib文件夹里面放jar包 3.resources文件夹里面放配置文件
 *
 * 2.实现类的硬编码  --- 使用面向接口编程解决
 *
 * 当前类的类名.class.getClassLoader()获取类加载器对象
 *
 * 类加载器的作用:将编译得到的字节码文件，加载进内存，形成字节码对象
 */
public class JdbcUtil {
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;
    static {
        //从配置文件中读取到数据,再将这些数据赋值给成员变量
        //在JDK中有专门用于读取properties文件的类:Properties
        Properties properties = new Properties();
        //使用类加载器的getResourceAsStream()方法将jdbcinfo.properties配置文件转换成字节输入流
        ClassLoader classLoader = JdbcUtil.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbcinfo.properties");


        //使用properties对象加载配置文件
        try {
            properties.load(is);

            //使用properties对象根据key获取value
            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            //1.注册驱动
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
