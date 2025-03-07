package com.Student.bean;

public class Student {


    private String number;
    private String xm;
    private String sex;
    private String age;
    private String grade;

    public Student(String number, String xm, String sex, String age, String grade) {
        this.number = number;
        this.xm = xm;
        this.sex = sex;
        this.age = age;
        this.grade = grade;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "number='" + number + '\'' +
                ", xm='" + xm + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }




}
