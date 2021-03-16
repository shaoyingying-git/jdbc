package com.itheima.jdbc;

import java.sql.*;

/**
 * 包名:com.itheima.jdbc
 * 作者:Leevi
 * 日期2019-04-06  09:09
 */
public class Cleint {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //使用Java代码操作day17数据库中的user表，并且查询出所有数据
        //使用Java代码操作mysql服务器
        //1.加载驱动，也就是告诉应用程序，我们使用的是什么驱动
        //DriverManager驱动管理者，它有一个方法registerDriver(驱动对象)注册驱动
        //静态代码块什么时候执行:类加载的时候执行
        //我们这种减少编译时报错的做法:实现低耦合
        Class.forName("com.mysql.jdbc.Driver");//1.降低耦合  2.防止两次注册驱动

        //2.创建连接:让Java代码和mysql服务器进行连接
        //DriverManager.getConnection()
        /*String url = "jdbc:mysql://localhost:3306/day17";*///要连接到的数据库的路径
        String url = "jdbc:mysql:///day17";
        String user = "root";//mysql服务器的用户名
        String password = "root";//mysql服务器的密码
        //Connection是一个接口(接口就是JDBC中)，而我们这句代码获取到的实现类(实现类在驱动里面)的对象
        //此地使用了:父类的引用指向子类对象,就是多态
        //使用多态的目的也是为了解耦。面向对象编程的六大基本原则之一:依赖倒置原则-----依赖抽象不依赖具体
        Connection conn = DriverManager.getConnection(url, user, password);//连接到mysql服务器

        //3.创建statement对象(这个对象能够将SQL语句发送到mysql服务器执行)
        String sql = "select * from user";
        Statement stm = conn.createStatement();

        //4.执行SQL语句
        //如果是增删改的SQL语句，则调用executeUpdate()方法，如果是查询的SQL语句则调用executeQuery
        ResultSet rst = stm.executeQuery(sql);//执行查询的SQL语句，查询到的结果都在rst对象中

        //5.如果是查询语句，则会拿到查询的结果，处理结果
        while (rst.next()) {
            int id = rst.getInt("id");
            String username = rst.getString("username");
            String pwd = rst.getString("password");
            String address = rst.getString("address");
            String nickname = rst.getString("nickname");

            //打印数据
            System.out.println(id + "，" + username + "," + pwd + "," + address + "," + nickname);
        }

        //6.关闭资源,后创建的资源先关闭
        rst.close();
        stm.close();
        conn.close();
    }
}
