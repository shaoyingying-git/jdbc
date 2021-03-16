package com.itheima.login;

import com.itheima.login.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 包名:com.itheima.login
 * 作者:Leevi
 * 日期2019-04-06  12:04
 * 模拟一个服务器
 */
public class Server {
    public boolean checkLogin(String username, String password) throws SQLException {
        //连接mysql服务器校验用户名和密码是否正确
        Connection conn = JdbcUtil.getConnection();
        Statement stm = conn.createStatement();
        //我们在SQL语句拼接的时候，改变了该SQL语句的结构，这种就叫做SQL注入。
        //怎么来避免SQL注入呢?就要让SQL语句不能够拼接字符串，使用preparedStatement代替Statement
        String sql = "select * from user where username='"+username+"' and password='"+password+"'";
        System.out.println(sql);
        ResultSet rst = stm.executeQuery(sql);

        boolean flag = false;
        if (rst.next()) {
            //登录成功
            flag = true;
        }
        //关闭资源
        JdbcUtil.close(conn, stm,rst);

        return flag;
    }
}
