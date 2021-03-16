package com.itheima;

import com.itheima.dbutils.DBUtils;
import com.itheima.utils.JDBCUtil;

import java.sql.SQLException;

/**
 * 包名:com.itheima
 * 作者:Leevi
 * 日期2019-04-09  09:27
 */
public class Client {
    public static void main(String[] args) throws SQLException {
        //使用DBUtils框架执行添加数据的SQL语句
        //1.创建对象
        DBUtils dbUtils = new DBUtils(JDBCUtil.getDataSource());
        //2.使用DBUtils对象执行update方法
        String sql = "insert into user values (null,?,?,?,?)";
        int i = dbUtils.update(sql, "zs", "12345678", "深圳", "张三");
        System.out.println(i);
    }
}
