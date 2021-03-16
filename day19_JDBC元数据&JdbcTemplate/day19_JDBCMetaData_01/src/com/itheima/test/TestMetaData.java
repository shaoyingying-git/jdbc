package com.itheima.test;

import com.itheima.utils.JDBCUtil;
import org.junit.Test;

import java.sql.*;

/**
 * 包名:com.itheima.test
 * 作者:Leevi
 * 日期2019-04-09  08:59
 */
public class TestMetaData {
    @Test
    public void test01() throws SQLException {
        //测试使用数据库元数据DataBaseMetaData
        //conn肯定是连接到某一个数据库服务器
        Connection conn = JDBCUtil.getConnection();
        //使用代码，获取连接的数据库的url以及用户名
        DatabaseMetaData metaData = conn.getMetaData();//获取数据库元数据
        String url = metaData.getURL();
        System.out.println(url);
        conn.close();
    }
    @Test
    public void test02() throws SQLException {
        //测试使用参数元数据
        Connection conn = JDBCUtil.getConnection();
        String sql = "select * from user where username=? and password=?";
        //使用参数元数据获取参数的个数
        PreparedStatement pstm = conn.prepareStatement(sql);
        ParameterMetaData parameterMetaData = pstm.getParameterMetaData();

        int count = parameterMetaData.getParameterCount();
        System.out.println(count);

        for(int i=1;i<=count;i++){
            String typeName = parameterMetaData.getParameterTypeName(i);
            System.out.println(typeName);
        }
    }

    @Test
    public void test03() throws SQLException {
        //使用结果集元数据，只能针对查询语句
        Connection conn = JDBCUtil.getConnection();
        String sql = "select * from account";
        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rst = pstm.executeQuery();

        /*ResultSetMetaData metaData = pstm.getMetaData();*/

        ResultSetMetaData metaData = rst.getMetaData();

        //获取结果集的列数
        int columnCount = metaData.getColumnCount();
        System.out.println(columnCount);

        //获取结果集的每列的列名
        for(int i=1;i<=columnCount;i++){
            String columnName = metaData.getColumnName(i);
            System.out.println(columnName);

            //获取每列的类型
            String columnTypeName = metaData.getColumnTypeName(i);
            System.out.println(columnTypeName);
        }
    }
}
