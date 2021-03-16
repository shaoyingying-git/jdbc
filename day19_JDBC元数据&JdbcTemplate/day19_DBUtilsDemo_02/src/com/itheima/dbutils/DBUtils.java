package com.itheima.dbutils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 包名:com.itheima.dbutils
 * 作者:Leevi
 * 日期2019-04-09  09:18
 */
public class DBUtils {
    private DataSource dataSource;

    public DBUtils(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int update(String sql,Object... params) throws SQLException {
        //1.获取连接对象
        //从连接池中获取连接
        Connection conn = dataSource.getConnection();
        //2.预编译SQL语句
        PreparedStatement pstm = conn.prepareStatement(sql);
        //3.设置参数
        //获取参数个数
        ParameterMetaData metaData = pstm.getParameterMetaData();
        int parameterCount = metaData.getParameterCount();
        //我们要考虑传入的数组正确与否，params的长度，必须和parameterCount是一致的
        if (params.length == parameterCount) {
            for(int i=1;i<=parameterCount;i++){
                pstm.setObject(i,params[i-1]);
            }
        }else {
            throw new RuntimeException("参数长度不对");
        }

        //4.执行SQL语句
        int i = pstm.executeUpdate();

        //5.关闭资源
        pstm.close();
        conn.close();
        return i;
    }
}
