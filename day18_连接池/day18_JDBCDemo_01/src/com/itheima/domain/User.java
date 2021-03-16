package com.itheima.domain;

import java.io.Serializable;

/**
 * 包名:com.itheima.domain
 * 作者:Leevi
 * 日期2019-04-07  08:57
 * 实体类:
 * 1.成员变量都是私有的
 * 2.针对每一个私有的成员变量提供公有的get和set方法
 * 3.必须有一个无参的构造函数
 * 4.基本数据类型的成员变量我们最好是声明成包装类型
 * 5.最好是要去实现Serializable接口
 * 6.如果该类的对象需要封装从数据库查询到的数据的话，咱们类的成员变量名一定要和结果集的字段名一一对应
 * 7.如果需要打印这个对象，则重写toString方法
 * 一个类要怎么去确定它到底该有哪些成员变量
 */
public class User implements Serializable{
    private Integer id;
    private String username;
    private String password;
    private String address;
    private String nickname;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
