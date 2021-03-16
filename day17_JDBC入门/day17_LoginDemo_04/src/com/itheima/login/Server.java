package com.itheima.login;

import com.itheima.login.utils.JdbcUtil;

import java.sql.*;

/**
 * 包名:com.itheima.login
 * 作者:Leevi
 * 日期2019-04-06  12:04
 * 模拟一个服务器
 *
 * 使用preparedStatement防止SQL注入
 */
public class Server {
    public boolean checkLogin(String username, String password) throws SQLException {
        //连接mysql服务器校验用户名和密码是否正确
        Connection conn = JdbcUtil.getConnection();
        //准备一个待编译的SQL语句
        String sql = "select * from user where username=? and password=?";
        //预编译SQL语句
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setObject(1,username);
        pstm.setObject(2,password);

        //执行SQL语句
        ResultSet rst = pstm.executeQuery();

        boolean flag = false;
        //判断是否登录成功
        if (rst.next()) {
            flag = true;
        }

        return flag;
    }
}
