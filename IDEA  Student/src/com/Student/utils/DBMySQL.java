package com.Student.utils;


import com.Student.bean.Student;

import javax.xml.transform.Result;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//封装，查询，和更改的方法
public class DBMySQL {
private static Connection cno=DBUntil.con;


    //实现查询 封装好一个查询的工具
    public static ResultSet query(String sql,String...data) {
        try {
            PreparedStatement pstmt=cno.prepareStatement(sql);//初步加载sql

            for (int i = 0; i<data.length; i++)
            {
                //数组下标是0 1开始的
                pstmt.setString(i+1,data[i]);
            }
            ResultSet resultSet=pstmt.executeQuery();//将结果集合返回去
            return resultSet;

        }catch (SQLException e){
            return null;
        }

    }


    //-1代表报错  0代表更改失败  大于1代表成功
    public static int update(String sql,String...data) {
        try {
            PreparedStatement pstmt=cno.prepareStatement(sql);//初步加载sql
            for (int i = 0; i<data.length; i++)
            {
                //数组下标是0 1开始的
                pstmt.setString(i+1,data[i]);
            }
            return pstmt.executeUpdate();//将结果集合返回去


        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }

    }




    //查询单个
    //T 代表任意类型 泛指
    public static <T> T queryOne(String sql, Class<T> tclass,String ...data) {
        T instance=null;
        try {
            PreparedStatement pstmt=cno.prepareStatement(sql);//初步加载sql


            for (int i = 0; i<data.length; i++)
            {
                //数组下标是0 1开始的
                pstmt.setString(i+1,data[i]);
            }
            ResultSet resultSet=pstmt.executeQuery();//将结果集合返回去
            //单个查询

            //创建一个类的实例
            instance = tclass.getDeclaredConstructor().newInstance();//创建类T的新实例
            Student student=new Student();
            Field fields[] = tclass.getDeclaredFields();//获取一个类当中所有声明字段
            if (resultSet.next()){
                for (Field field : fields) {
                    field.setAccessible(true);//允许访问私有字段
                   String res = resultSet.getString(field.getName());//得到一个值
                    field.set(instance,res);
//                    student.setSex(res);
                }
            }else {
                return null;
            }

            return instance;

        }catch (SQLException e){
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;

    }




    //查询所有
    public static <T> List<T> queryAll(String sql, Class<T> tclass, String ...data) {

        List<T> list=new ArrayList<>();
        try {
            PreparedStatement pstmt=cno.prepareStatement(sql);//初步加载sql


            for (int i = 0; i<data.length; i++)
            {
                //数组下标是0 1开始的
                pstmt.setString(i+1,data[i]);
            }
            System.out.println(pstmt);
            ResultSet resultSet=pstmt.executeQuery();//将结果集合返回去
            //单个查询
            //创建一个类的实例
            while (resultSet.next()){
                T instance = tclass.getDeclaredConstructor().newInstance();//创建类T的新实例
                Field fields[] = tclass.getDeclaredFields();//获取一个类当中所有声明字段
                for (Field field : fields) {
                    field.setAccessible(true);//允许访问私有字段
                    String res = resultSet.getString(field.getName());//得到一个值
                    field.set(instance,res);
//                    student.setSex(res);
                }
                list.add(instance);
            }


            return list;

        }catch (SQLException e){
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return list;

    }

}
