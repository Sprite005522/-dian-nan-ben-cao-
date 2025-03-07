package com.Student.utils;

import com.Student.bean.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.List;

public class Tools {
    /**
     * 实现了窗口居中
     * @param jFrame
     * @param width
     * @param height
     */
    public static void setPos(JFrame jFrame, int width, int height){
        Toolkit tk=Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        int x=(int)screenSize.getWidth()/2-width/2;
        int y=(int)screenSize.getHeight()/2-height/2;
        jFrame.setBounds(x,y,width,height);

    }

    //弹窗封装的方法
    public  static  void  showMessage(String message){
        JOptionPane.showMessageDialog(null,message,"提示消息",JOptionPane.WARNING_MESSAGE);
        //JOptionPane.showConfirmDialog(null,"密码不能为空","注册消息",JOptionPane.WARNING_MESSAGE);
    }

    public static void addTableData(DefaultTableModel model, JTable table, Student stu) {
        if (stu != null) {
            Object[] row = {stu.getNumber(), stu.getXm(), stu.getSex(), stu.getAge(), stu.getGrade()};
            model.addRow(row);
            table.repaint(); // 刷新表格
        }
    }

    /**
     * 表格的控制权,List数据
     * @param tableModel
     * @param list
     * @param <T>
     * @return
     */
    public static <T> void addTableData(DefaultTableModel tableModel, List<T> list) throws IllegalAccessException {
        //定义这个类List
        tableModel.setRowCount(0);
        for(T t:list){//遍历List
            int len = t.getClass().getDeclaredFields().length;//获取这个类当中所有的私有变量
            String data[]=new String[len];//存储数据组
            Field[] fieldes = t.getClass().getDeclaredFields();//得到所有字段的值
            for (int i=0;i<len;i++) {
                fieldes[i].setAccessible(true);
                Object js = fieldes[i].get(t);
                if (js!=null){
                    data[i]=js.toString();
                }
            }

            tableModel.addRow(data);
        }

    }

    /**
     *
     * @param tableModel
     * @param t
     * @param <T>
     */
    public static <T> void addTableData(DefaultTableModel tableModel, T t) throws IllegalAccessException {
        tableModel.setRowCount(0);
        int len = t.getClass().getDeclaredFields().length;//获取这个类当中所有的私有变量
        String data[] = new String[len];//存储数据组
        Field[] fieldes = t.getClass().getDeclaredFields();//得到所有字段的值
        for (int i = 0; i < len; i++) {
            fieldes[i].setAccessible(true);

            Object js = fieldes[i].get(t);
            if (js!=null){
                data[i]=js.toString();
            }

        }
        tableModel.addRow(data);

    }
}