package com.itheima.test;

import com.itheima.domain.User;
import com.itheima.utils.JDBCUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 包名:com.itheima.test
 * 作者:Leevi
 * 日期2019-04-09  10:04
 */
public class TestJdbcTemplate {
    private JdbcTemplate template;
    @Before
    public void init(){
        //写在有Before注解的方法中的代码，会在执行测试方法之前调用
        System.out.println("执行测试方法之前");
        //1.创建一个JdbcTemplate对象
        template = new JdbcTemplate(JDBCUtil.getDataSource());
    }
    @After
    public void destroy(){
        //写在After注解方法里面的代码，会在执行完测试方法之后调用
        System.out.println("测试方法执行完毕。。。");
    }
    @Test
    public void testInsert(){
        //测试添加数据
        String sql = "insert into user values (null,?,?,?,?)";
        //使用JdbcTemplate执行添加数据的SQL语句

        //2.调用update()方法，执行添加数据的SQL语句
        int i = template.update(sql, "ls", "88888", "广州", "李四");
        System.out.println(i);
    }
    @Test
    public void testUpdate(){
        //测试修改数据
        String sql = "update user set nickname=? where id=?";

        //2.调用update()方法，执行SQL语句
        template.update(sql,"张三丰",8);
    }
    @Test
    public void testDelete(){
        System.out.println("执行测试方法....");
        //测试执行删除数据的SQL语句
        String sql = "delete from user where id=?";
        template.update(sql,8);
    }
    @Test
    public void testQueryOne(){
        //测试查询单个数据
        //查询圣枪游侠的id
        /*String sql = "select id from user where nickname=?";
        //调用方法查询
        //如果要使用queryForObject()方法查询单个数据，你必须得指定这个数据的类型
        Integer id = template.queryForObject(sql, Integer.class, "圣枪游侠");
        System.out.println(id);*/


        //查询id为4的用户的nickname
        String sql = "select nickname from user where id=?";
        String nickname = template.queryForObject(sql, String.class, 4);
        System.out.println(nickname);
    }

    @Test
    public void testQueryObeRow(){
        //使用JdbcTemplate方法，查询一行数据
        //将这一行数据查询出来，封装到什么里面?---1.可以封装到Map中
        String sql = "select * from user where id=?";
        //查询一行数据，并且将这一行结果封装到map对象里面
        Map<String, Object> map = template.queryForMap(sql, 4);
        System.out.println(map);
    }
    @Test
    public void testQueryOneRowForBean(){
        //查询一行数据封装到javaBean对象
        String sql = "select * from user where id=?";
        //调用queryForObject()方法
        User user = template.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                //这个方法里面就进行结果集的映射
                User user = new User();
                int id = resultSet.getInt("id");
                user.setId(id);

                String username = resultSet.getString("username");
                user.setUsername(username);

                String password = resultSet.getString("password");
                user.setPassword(password);

                String nickname = resultSet.getString("nickname");
                user.setNickname(nickname);

                String address = resultSet.getString("address");
                user.setAddress(address);
                return user;
            }
        }, 4);

        System.out.println(user);
    }

    @Test
    public void testQueryForOneRow2(){
        //很重要
        //使用queryForObject()方法，查询一条数据并且将这一条数据封装到JavaBean对象中
        String sql = "select * from user where id=?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 4);
        System.out.println(user);
    }
    @Test
    public void testQueryOneColumn(){
        //查询一列数据，要查询所有用户的nickname
        String sql = "select nickname from user";
        //查询一列数据封装到一个集合中
        List<String> nicknames = template.queryForList(sql, String.class);
        System.out.println(nicknames);
    }
    @Test
    public void testQueryRows(){
        //查询多行数据
        //返回值是List<Map<String,Object>>
        String sql = "select * from user where id>?";
        List<Map<String, Object>> maps = template.queryForList(sql, 4);

        System.out.println(maps);
    }

    @Test
    public void testQueryRows2(){
        //查询多行数据
        //很重要
        //返回值是List<Bean>
        String sql = "select * from user where id>?";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<>(User.class), 4);

        System.out.println(users);
    }
}
