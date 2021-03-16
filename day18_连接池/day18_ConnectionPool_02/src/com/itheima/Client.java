package com.itheima;

import com.itheima.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 包名:com.itheima
 * 作者:Leevi
 * 日期2019-04-07  10:05
 */
public class Client {
    public static void main(String[] args) throws SQLException {
        //使用连接池获取连接
        //1.创建连接池对象
        ConnectionPool pool = new ConnectionPool();
        //2.从连接池获取一个连接对象
        Connection conn1 = pool.getConnection();
        System.out.println("拿出一个之后连接池中还剩下:" + pool.getCount());

        Connection conn2 = pool.getConnection();
        Connection conn3 = pool.getConnection();
        Connection conn4 = pool.getConnection();
        Connection conn5 = pool.getConnection();
        System.out.println("拿出五个之后连接池中还剩下:" + pool.getCount());
        Connection conn6 = pool.getConnection();
        System.out.println("拿出六个之后连接池中还剩下:" + pool.getCount());

        //归还连接
        conn1.close();
        conn2.close();
        conn3.close();
        conn4.close();
        System.out.println("归还了四个连接之后，连接池中剩下:" + pool.getCount());
        conn5.close();
        conn6.close();
        System.out.println("归还了六个连接之后，连接池中剩下:" + pool.getCount());

        Statement stm = conn1.createStatement();
        System.out.println(stm);
    }
}
