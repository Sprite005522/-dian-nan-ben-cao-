package com.Student.dao;

import com.Student.bean.Admin;
import com.Student.utils.DBMySQL;
import com.Student.utils.DBUntil;

import java.sql.ResultSet;
import java.util.List;

public class AdminDao {
    /**
     * 是否登录
     *
     * @param username
     * @param password
     * @return
     */
    public static Admin islogin(String username, String password) {
        String sql = "select * from s_admin where account=? and pwd=?";
        ResultSet res = DBMySQL.query(sql, username, password);//获取到一个结果集合
        try {
            if (res.next()) {
                Admin admin = new Admin(res.getString("account"),
                        res.getString("pwd"),
                        res.getString("xm"),
                        res.getString("sta"),
                        res.getString("pow"));//把数据读取出来
                return admin;
            }

        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 实现注册账户的功能
     *
     * @param account
     * @param password
     * @param xm
     * @param sta
     * @param pow
     * @return
     */
    public static int register(String account, String password, String xm, String sta, String pow) {
        String set = "insert into s_admin values(?,?,?,?,?)";

        int result = DBMySQL.update(set, account, password, xm, sta, pow);
        return result;
    }


    /**
     * 实现更新账号是否在线的状态
     *
     * @param account
     * @param sta
     * @return
     */
    public static int update(String account, String sta) {
        String sql = "update s_admin set sta=? where account=?";
        return DBMySQL.update(sql, sta, account);
    }


    /**
     * 通过uuid进行修改
     *
     * @param uuid
     * @param sta
     * @return
     */
    public static int updateByUuid(String uuid, String sta) {
        String sql = "update s_admin set sta=? where sta=?";
        return DBMySQL.update(sql, sta, uuid);
    }


    /**
     * 查询所有在线账号
     *
     * @return
     */
    public static List<Admin> showAccount() {
        String sql = "select * from s_admin where sta!='0'";
        List<Admin> list = DBMySQL.queryAll(sql, Admin.class);//获取到一个结果集合
        return list;
    }


    /**
     * 查询所有账号
     *
     * @return
     */
    public static List<Admin> showAllAccount() {
        String sql = "select * from s_admin";
        List<Admin> list = DBMySQL.queryAll(sql, Admin.class);//获取到一个结果集合
        return list;
    }

    /**
     * 查询指定账号
     *
     * @return
     */
    public static Admin showAccount(String uuid) {
        String sql = "select * from s_admin where sta=?";
        Admin list = DBMySQL.queryOne(sql, Admin.class, uuid);//获取到一个结果集合
        return list;
    }


    /**
     * 更改账号信息
     *
     * @param account
     * @param pwd
     * @param xm
     * @return
     */
    public static int updateUserMes(String account, String pwd, String xm) {
        String sql = "update s_admin set xm=?,pwd=? where account=?";
        return DBMySQL.update(sql, xm, pwd, account);
    }

    /**
     * 更改当前账号的密码
     *
     * @param uuid
     * @param pwd
     * @return
     */
    public static int updatePwd(String uuid, String pwd) {
        String sql = "update s_admin set pwd=? where sta=?";
        return DBMySQL.update(sql, pwd, uuid);


    }
}
