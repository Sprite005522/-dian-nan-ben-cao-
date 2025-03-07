package com.Student.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// 实现表格显示内容
public class Table {

    private JTable table; // 定义一个表格
    private JScrollPane scrollPane; // 定义一个滚动条
    private DefaultTableModel model; // 设置默认模式，出现滚动条

    public Table(Object columns[]) { // 存入行的数据
        table = new JTable(); // 初始化一个表格
        model = new DefaultTableModel() { // 创建一个匿名类
            // 这个表格是否允许编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.setColumnIdentifiers(columns); // 设置一下表头
        table.setModel(model);
        table.getTableHeader().setReorderingAllowed(false); // 禁止表头排序
        table.getTableHeader().setReorderingAllowed(false); // 禁止拖拉表格

        scrollPane = new JScrollPane(table); // 创建一个滚动条并赋值给成员变量
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // 设置滚动条为竖着的
    }

    // 返回滚动条
    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    // 返回模型
    public DefaultTableModel getModel() {
        return model;
    }
}