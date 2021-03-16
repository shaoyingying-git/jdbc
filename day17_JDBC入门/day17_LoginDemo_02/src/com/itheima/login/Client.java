package com.itheima.login;

import com.itheima.login.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 包名:com.itheima.login
 * 作者:Leevi
 * 日期2019-04-06  11:50
 * 模拟登录的客户端
 */
public class Client {
    public static void main(String[] args) throws SQLException {
        //1.提示用户输入用户名
        System.out.println("请输入用户名:");
        //2.获取用户在控制台输入的用户名
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        //3.提示用户输入密码
        System.out.println("请输入密码:");
        //4.获取用户输入的密码
        String password = scanner.nextLine();

        //连接mysql服务器校验用户名和密码是否正确
        Connection conn = JdbcUtil.getConnection();
        Statement stm = conn.createStatement();
        String sql = "select * from user where username='"+username+"' and password='"+password+"'";
        ResultSet rst = stm.executeQuery(sql);
        if (rst.next()) {
            //登录成功
            System.out.println("登录成功");
        }else {
            //登录失败
            System.out.println("登录失败");
        }

        //关闭资源
        JdbcUtil.close(conn, stm,rst);
    }
}
