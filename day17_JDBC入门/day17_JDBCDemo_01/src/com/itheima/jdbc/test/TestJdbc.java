package com.itheima.jdbc.test;

import com.itheima.jdbc.utils.JdbcUtil;
import com.itheima.jdbc.utils.JdbcUtil_1;
import com.itheima.jdbc.utils.JdbcUtil_2;
import org.junit.Test;

import java.sql.*;

/**
 * 包名:com.itheima.jdbc.test
 * 作者:Leevi
 * 日期2019-04-06  10:15
 * 使用注解进行Junit单元测试
 * 单元测试方法:1.必须是public  2.必须是void    3.必须没有参数
 */
public class TestJdbc {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        //目标:往mysql服务器中的day17数据库中的user表中添加一条数据
        //1.注册驱动
        //Class.forName(类的全限定名)为了获取类的字节码对象
        Class.forName("com.mysql.jdbc.Driver");

        //2.创建连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "root");

        //3.创建statement对象
        Statement stm = conn.createStatement();

        //4.执行SQL语句
        String sql = "insert into user values (null,'jay','666666','中国台湾','周杰棍')";
        int i = stm.executeUpdate(sql);//这个方法返回的值，就是受到影响的行数
        System.out.println(i);

        //5.关闭资源
        stm.close();
        conn.close();
    }

    @Test
    public void deleteUser() throws ClassNotFoundException, SQLException {
       //目的:删除id为2的数据
        //1.注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2.获得连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "123");
        //3.创建statement对象
        Statement stm = conn.createStatement();
        //4.执行SQL语句
        String sql = "delete from user where id=2";
        int i = stm.executeUpdate(sql);
        System.out.println(i);

        //5.关闭资源
        stm.close();
        conn.close();
    }

    @Test
    public void testUpdateUser() throws ClassNotFoundException, SQLException {
        //将id为1的用户的用户名改成‘卢锡安’
        //2.获得连接
        Connection conn = JdbcUtil_1.getConnection();

        //3.创建statement
        Statement stm = conn.createStatement();
        //4.执行SQL语句
        String sql = "update user set username='卢锡安' where id=2";
        int i = stm.executeUpdate(sql);
        //5.关闭资源
        JdbcUtil_1.close(conn,stm);
    }

    @Test
    public void testSelect() throws SQLException {
        //查询id为1的用户信息
        //1.获得连接
        Connection conn = JdbcUtil.getConnection();
        //2.创建Statement
        Statement stm = conn.createStatement();
        //3.执行SQL语句
        String sql = "select * from user where id=1";
        ResultSet rst = stm.executeQuery(sql);
        //4.处理结果集
        rst.next();//将游标向下移动一行，获取该行数据
        int id = rst.getInt("id");
        String uname = rst.getString("username");
        String pwd = rst.getString("password");
        String address = rst.getString("address");
        String nickname = rst.getString("nickname");

        System.out.println(id+","+uname+","+pwd+","+address+","+nickname);

        //5.关闭资源
        JdbcUtil.close(conn,stm,rst);
    }
    @Test
    public void testTransaction() {
        //执行转账，使用事务控制流程
        //1.获取连接对象
        Connection conn = null;
        Statement stm1 = null;
        Statement stm2 = null;
        try {
            conn = JdbcUtil.getConnection();
            //2.开启事务:conn.setAutoCommit(false)
            conn.setAutoCommit(false);//设置不自动提交事务

            //3.执行业务逻辑
            //3.1 张三扣款
            String sql = "update account set balance=balance-500 where id=1";
            stm1 = conn.createStatement();
            stm1.executeUpdate(sql);

            //出现了异常
            int num = 10/0;

            //3.2 李四收款
            String sql2 = "UPDATE account SET balance=balance+500 WHERE id=2";
            stm2 = conn.createStatement();
            stm2.executeUpdate(sql2);


            //执行完成业务逻辑没有出现异常，则提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //执行业务逻辑的时候出现了异常，则回滚事务
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            //关闭资源
            try {
                JdbcUtil.close(conn, stm1);
                stm2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
