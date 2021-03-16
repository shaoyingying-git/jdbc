package com.itheima.c3p0;

import com.itheima.c3p0.domain.User;
import com.itheima.c3p0.utils.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 包名:com.itheima.c3p0
 * 作者:Leevi
 * 日期2019-04-07  11:22
 */
public class Client {
    @Test
    public void testFindUserById() throws SQLException {
        //1.获取连接对象
        Connection conn = JDBCUtil.getConnection();
        //2.预编译SQL语句
        String sql = "select * from user where id=?";
        PreparedStatement pstm = conn.prepareStatement(sql);
        //3.设置参数
        pstm.setObject(1,4);
        //4.执行SQL语句
        ResultSet rst = pstm.executeQuery();
        //5.处理结果集
        rst.next();
        int id = rst.getInt("id");
        String username = rst.getString("username");
        String password = rst.getString("password");
        String address = rst.getString("address");
        String nickname = rst.getString("nickname");

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setAddress(address);
        user.setNickname(nickname);


        System.out.println(user);

        //6.关闭资源
        JDBCUtil.close(conn,pstm,rst);
    }
    @Test
    public void test02() throws SQLException {
        Connection conn1 = JDBCUtil.getConnection();
        Connection conn2 = JDBCUtil.getConnection();
        Connection conn3 = JDBCUtil.getConnection();
        Connection conn4 = JDBCUtil.getConnection();
        Connection conn5 = JDBCUtil.getConnection();
        Connection conn6 = JDBCUtil.getConnection();
        Connection conn7 = JDBCUtil.getConnection();
        Connection conn8 = JDBCUtil.getConnection();
        Connection conn9 = JDBCUtil.getConnection();
        Connection conn10 = JDBCUtil.getConnection();

        conn1.close();

        Connection conn11 = JDBCUtil.getConnection();

    }
}
