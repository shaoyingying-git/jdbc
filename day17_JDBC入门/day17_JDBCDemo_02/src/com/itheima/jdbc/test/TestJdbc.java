package com.itheima.jdbc.test;

import com.itheima.jdbc.utils.JdbcUtil;
import org.junit.Test;

import java.sql.*;

/**
 * 包名:com.itheima.jdbc.test
 * 作者:Leevi
 * 日期2019-04-06  10:15
 * preparedStatement的使用:
 * 优势:1.能够预编译SQL语句，提升执行效率   2.能够防止SQL注入
 */
public class TestJdbc {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        //目标:往mysql服务器中的day17数据库中的user表中添加一条数据
        //1.获取连接对象
        Connection conn = JdbcUtil.getConnection();
        //准备一个待编译的SQL语句,需要传入参数的地方使用"?"代替
        String sql = "insert into user values (?,?,?,?,?)";

        //2.预编译带SQL 语句
        PreparedStatement pstm = conn.prepareStatement(sql);

        //预编译完之后，设置参数
        pstm.setObject(1,null);
        pstm.setObject(2,"jax");
        pstm.setObject(3,"12345678");
        pstm.setObject(4,"召唤师峡谷");
        pstm.setObject(5,"一灯大师");


        //3.执行SQL语句
        pstm.executeUpdate();

        //4.关闭资源
        JdbcUtil.close(conn,pstm);
    }
}
