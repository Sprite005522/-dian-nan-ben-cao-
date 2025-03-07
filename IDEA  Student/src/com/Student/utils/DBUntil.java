package com.Student.utils;

import java.sql.Connection;
import java.sql.DriverManager;

//功能创建数据库的链接
public class DBUntil {

    public DBUntil() {

    }



    private String account;
    private String password;
    private String db;
//    账号，数据库密码，数据库名称
    public DBUntil(String account,String password,String db){
        this.account=account;
        this.password=password;
        this.db=db;
//        实现加载数据库的驱动，以及创建数据库的链接
        init();
    }

    public static Connection con=null;

    private void init(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("加载驱动成功");
        }catch (Exception e){
            System.out.println("加载驱动失败");
        }

//        连接数据库
        try{
            String uri="jdbc:mysql://localhost:3306/"+db+"?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
            con= DriverManager.getConnection(uri,account,password);
            System.out.println("连接数据库成功");
        }catch (Exception e){
            System.out.println("连接数据库失败");
        }
    }

}
