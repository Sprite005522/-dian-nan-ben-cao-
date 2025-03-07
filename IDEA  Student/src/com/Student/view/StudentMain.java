package com.Student.view;

import com.Student.utils.DBUntil;

//当前是程序的入口
public class StudentMain {
    public static void main(String[] args){
        //打开主程序，连接数据库

        //连接数据库
        DBUntil dbUntil=new DBUntil("root","005522","db_Student");
        LoginView loginView=new LoginView();//打开登陆窗口
    }
}
