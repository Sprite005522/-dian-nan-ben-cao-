package com.Student.bean;

public class Admin {


    private String account;

    private String pwd;

    private String xm;

    private String sta;

    private String pow;

    @Override
    public String toString() {
        return "Admin{" +
                "account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", xm='" + xm + '\'' +
                ", sta='" + sta + '\'' +
                ", pow='" + pow + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta;
    }

    public String getPow() {
        return pow;
    }

    public void setPow(String pow) {
        this.pow = pow;
    }

    public Admin(String account, String pwd, String xm, String sta, String pow) {
        this.account = account;
        this.pwd = pwd;
        this.xm = xm;
        this.sta = sta;
        this.pow = pow;
    }

    public Admin() {
    }
}