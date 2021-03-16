package com.itheima.client;

import com.itheima.server.Server;

import java.util.Scanner;

/**
 * 包名:com.itheima.client
 * 作者:Leevi
 * 日期2019-04-09  11:13
 */
public class Client {
    public static void main(String[] args) {
        //1.提示用户输入用户名
        System.out.println("请输入用户名:");
        //2.获取用户输入的用户名
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        //3.提示用户输入密码
        System.out.println("请输入密码:");
        //4.获取用户输入的密码
        String password = scanner.nextLine();

        //5.到服务器校验用户名和密码是否正确
        Server server = new Server();
        boolean flag = server.checkLogin(username, password);

        if (flag) {
            System.out.println("登录成功...");
        }else {
            System.out.println("登陆失败...");
        }
    }
}
