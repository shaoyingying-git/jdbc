package com.itheima.test;

import com.itheima.domain.User;
import com.itheima.utils.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 包名:com.itheima.test
 * 作者:Leevi
 * 日期2019-04-07  08:51
 *
 * 1.性能方面的问题:每次执行SQL语句都会创建和销毁连接，这样会带来很大的系统开销以及如果并发访问过大的情况会导致内存溢出
 *   使用连接池解决
 *
 * 2.代码方面不够简化
 */
public class TestJdbc {
    @Test
    public void testFindUserById() throws SQLException {
        //执行查询的SQL语句，使用preparedStatement进行预编译
        String sql = "select * from user where id=?";//变化的
        //1.注册驱动
        //2.获得连接对象
        Connection conn = JdbcUtil.getConnection();
        //3.预编译SQL语句
        PreparedStatement pstm = conn.prepareStatement(sql);
        //4.设置参数
        pstm.setObject(1,4);//变化的
        //5.执行SQL语句
        ResultSet rst = pstm.executeQuery();
        //6.从rst对象中取出结果
        rst.next();
        //分别取出这条数据的各个字段的值
        //结果集的封装也是变化的
        int id = rst.getInt("id");
        String username = rst.getString("username");
        String password = rst.getString("password");
        String address = rst.getString("address");
        String nickname = rst.getString("nickname");

        //将查询到的数据封装到一个JavaBean对象中(很重要)
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setAddress(address);
        user.setNickname(nickname);

        System.out.println(user);
        //7.关闭资源
        JdbcUtil.close(conn,pstm,rst);
    }

    @Test
    public void testAddUser() throws SQLException {
        //往user表中添加一条数据
        //1.获取连接对象
        Connection conn = JdbcUtil.getConnection();
        //2.预编译SQL语句
        String sql = "insert into user values (null,?,?,?,?)";
        PreparedStatement pstm = conn.prepareStatement(sql);
        //设置参数
        pstm.setObject(1,"caixukun");
        pstm.setObject(2,"999999");
        pstm.setObject(3,"NBA");
        pstm.setObject(4,"鸡你太美");
        //3.执行SQL语句
        pstm.executeUpdate();
        //4.关闭资源
        JdbcUtil.close(conn,pstm);
    }
}
