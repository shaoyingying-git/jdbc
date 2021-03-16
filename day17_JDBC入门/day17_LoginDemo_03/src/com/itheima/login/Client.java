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

        //调用服务器的代码，校验登录
        Server server = new Server();
        boolean flag = server.checkLogin(username,password);

        //判断是否登录成功
        if (flag) {
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }
}
