package com.Student.dao;

import com.Student.bean.Student;
import com.Student.utils.DBMySQL;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.List;

public class StudentDao {

    /**
     * 添加学生
     * @param student
     * @return
     */
    public int addStudent(Student student){
        String sql="insert into s_student (number,xm,sex,age,grade)values(?,?,?,?,?)";
        int res=DBMySQL.update(sql,student.getNumber(),
                student.getXm(),
                student.getSex(),
                student.getAge(),
                student.getGrade());
        return  res;
    }


    public int delStudent(String id){
        String sql="DELETE FROM s_student where number=?";
        int res=DBMySQL.update(sql,id);
        return res;
    }


    /**
     * 实现更改学生
     * @param student
     * @return
     */
    public int updateStudent(Student student,String num){
        String sql="update s_student set number=?,xm=?,sex=?,age=?,grade=? where number=?";
        int res=DBMySQL.update(sql,student.getNumber(),
                student.getXm(),
                student.getSex(),
                student.getAge(),
                student.getGrade(),num);
        return  res;
    }


    /**
     * 通过学号查询
     * @param id
     * @return
     */
    public Student getStudentByNumber(String id){
        String sql="select * from s_student where number=?";
        Student stu = DBMySQL.queryOne(sql, Student.class, id);//返回的是一个存放学生的java bean
        return stu;
    }

    /**
     * 查询全部学生
     * @return
     */
    public List<Student> getStudentAll(){
        String sql="select * from s_student ";
        List<Student> stu;//返回的是一个存放学生的java bean
        stu = DBMySQL.queryAll(sql, Student.class);
        return stu;
    }


    /**
     * 实现条件查找
     * @return
     */
    public List<Student> getStudentWhere(Student student) throws IllegalAccessException {
        String sql = "select * from s_student ";
        Field[] fieldes = student.getClass().getDeclaredFields();//获取到所有的字段名称
        boolean firstCondition = true; // 用于标记是否是第一个条件
        for (int i = 0; i < fieldes.length; i++) {
            Field fielde = fieldes[i];
            fielde.setAccessible(true);//允许访问私有字段
            String name = fielde.getName();//字段名称
            Object value = fielde.get(student);
            if (value != null && !value.toString().isEmpty()) {
                if (firstCondition) {
                    sql = sql + " where " + name + " Like'%" + value + "%' ";
                    firstCondition = false;
                } else {
                    sql = sql + " and " + name + " Like'%" + value + "%' ";
                }
            }
        }

        List<Student> stu = DBMySQL.queryAll(sql, Student.class);//返回的是一个存放学生的java bean
        return stu;
    }

}