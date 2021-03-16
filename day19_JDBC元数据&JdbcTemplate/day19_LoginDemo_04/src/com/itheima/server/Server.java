package com.itheima.server;

import com.itheima.domain.User;
import com.itheima.utils.JDBCUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 包名:com.itheima.server
 * 作者:Leevi
 * 日期2019-04-09  11:15
 */
public class Server {
    public boolean checkLogin(String username,String password){
        String sql = "select * from user where username=? and password=?";

        //使用JDBCTemplate执行SQL语句
        JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());
        //将结果集封装到user对象中
        User user = null;
        try {
            //如果没有查到数据，这个方法会抛异常，为了让程序不终止，将异常catch起来
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (DataAccessException e) {

        }
        //如果user为空，则表示查询不到用户，也就是说登录失败
        if (user == null) {
            //登录失败
            return false;
        }else {
            //登录成功
            return true;
        }
    }
}
