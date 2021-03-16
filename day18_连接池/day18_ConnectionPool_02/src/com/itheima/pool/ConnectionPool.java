package com.itheima.pool;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 包名:com.itheima.pool
 * 作者:Leevi
 * 日期2019-04-07  09:56
 * 1. 当连接池对象初始化的时候，要存放5个连接对象
 * 2. 当需要连接的时候，如果连接池中有连接，则直接从连接池的头部获取，如果连接池中没有连接，则新创建连接
 * 3. 如果要归还连接的时候，则判断该连接是否本身就是连接池中的连接，如果是则归还，如果不是则销毁
 *
 * 一个连接对象之所以是连接，也就是因为它所属的类实现了Connection接口
 *
 * sun公司对连接池的编写，有一些规则的要求，也就是说凡是要写连接池，那么都得实现一个接口DataSource接口
 */
public class ConnectionPool implements DataSource{
    private static String driverClass;
    private static String username;
    private static String password;
    private static String url;
    //声明一个容器存放连接
    private LinkedList<Connection> connections = new LinkedList<>();
    static {
        //从配置文件中读取数据并使用
        Properties properties = new Properties();
        //将配置文件转换成流
        ClassLoader classLoader = ConnectionPool.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc.properties");
        try {
            properties.load(is);
            driverClass = properties.getProperty("driverClass");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //注册驱动
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //当连接池对象初始化的时候，要执行的代码写在构造函数中
    public ConnectionPool(){
        //初始化五个连接存放进容器
        /*try {
            for(int i=0;i<5;i++){
                //连接对象是mysql驱动中的实现类的对象
                Connection conn = DriverManager.getConnection("jdbc:mysql:///day17", "root", "123");
                connections.add(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        //初始化的时候，往连接池中存入的连接对象是MyConnection的对象
        for(int i=0;i<=4;i++){
            //传入到构造函数中去的对象，应该是具备功能的连接对象
            try {
                Connection connection = DriverManager.getConnection(url, username, password);

                Connection conn = new MyConnection(connection,connections);
                connections.add(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取连接池中连接数
     * @return
     */
    public Integer getCount(){
        return connections.size();
    }

    /**
     * 从连接池获取连接对象
     * @return
     */
    @Override
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        if(connections.size()>0){
            //容器中还有连接
            conn = connections.removeFirst();
        }else {
            //容器没有连接了，则新创建连接
            conn = DriverManager.getConnection(url,username,password);
        }
        return conn;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    /**
     * 归还连接
     * @param conn
     */
    /*public void addBack(Connection conn) throws SQLException {
        //判断当前归还的这个连接是连接池中的连接还是新创建的连接
        //新创建的连接不是MyConnection类型，原本就在连接池中的连接是MyConnection类型
        *//*if(conn instanceof MyConnection){
            //将要归还的连接存放到连接池的尾部
            connections.addLast(conn);
        }else {
            //新创建的连接，则销毁
            conn.close();
        }*//*

        //当conn对象是MyConnection的对象的时候，调用close()而是将连接归还到连接池
        //当conn是新创建的连接对象的时候，调用close()就销毁
        conn.close();
    }*/
}
