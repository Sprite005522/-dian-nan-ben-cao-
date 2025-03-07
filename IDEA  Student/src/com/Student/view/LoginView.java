package com.Student.view;

import com.Student.bean.Admin;
import com.Student.dao.AdminDao;
import com.Student.utils.Tools;
//import jdk.nashorn.internal.scripts.JO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.UUID;

public class LoginView {

    private JFrame frame;

    private final int WIDTH=500;

    private final int HEIGHT=280;

    public static String uuid;

    public static String pow = "0";//用户

    /**
     * 构造方法
     */
    public  LoginView(){
        frame=new JFrame();
        initView();

        frame.setTitle("学生信息管理系统");
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Tools.setPos(frame, WIDTH,HEIGHT);
        frame.validate();//让组件生效
    }

    /**
     * 初始化窗口
     */
    private void initView(){

    /**
     * 创建一个盘子
     */
        JPanel jPanel=new JPanel();
        jPanel.setBounds(0,0,WIDTH,HEIGHT);
        jPanel.setLayout(null);
        frame.add(jPanel);

    /**
     * 创建一个标签，使用标签来存储图片，实现背景  图片
     */

        ImageIcon icon=new ImageIcon("image/1.jpg");
        JLabel label=new JLabel(icon);
        label.setBounds(0,0,WIDTH,HEIGHT);


    /**
     * 设置两个标签，两个输入框 一个标签 一个按钮
      */
        JLabel title=new JLabel("学生信息管理系统");
        title.setBounds(0,0,WIDTH,100);
        title.setFont(new Font("宋体",Font.BOLD,28));
        title.setForeground(Color.CYAN);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel.add(title);

    /**
     * 绘制一个 标签 账号 和 密码
      */
        JPanel jPanel2=new JPanel();
        jPanel2.setBounds(0,100,WIDTH,30);
        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel.add(jPanel2);
        jPanel2.setOpaque(false);

        JLabel jLabel=new JLabel("账号");
        jPanel2.add(jLabel);
        jLabel.setFont(new Font("华文bai行楷",Font.BOLD,18));
        jLabel.setForeground(new Color(255,228,181));

        JTextField jTextField=new JTextField(20);
        jPanel2.add(jTextField);
        jTextField.setFont(new Font("宋体",Font.PLAIN,18));
        jTextField.setForeground(new Color(224,57,151));


        /**
         * 绘制一个 标签 账号 和 密码
          */
        JPanel jPanel22=new JPanel();
        jPanel22.setBounds(0,130,WIDTH,30);
        jPanel22.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel.add(jPanel22);
        jPanel22.setOpaque(false);

        JLabel jLabel2=new JLabel("密码");
        jPanel22.add(jLabel2);
        jLabel2.setFont(new Font("华文bai行楷",Font.BOLD,18));
        jLabel2.setForeground(new Color(255,228,181));


        JPasswordField jPasswordField=new JPasswordField(20);
        jPanel22.add(jPasswordField);
        jPasswordField.setFont(new Font("宋体",Font.PLAIN,18));
        jPasswordField.setForeground(new Color(224,57,151));


        /**
         * 绘制一个 标签 账号 和 密码
          */
        JPanel jPanel222=new JPanel();
        jPanel222.setBounds(0,160,WIDTH,40);
        jPanel222.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel.add(jPanel222);
        jPanel222.setOpaque(false);

        JButton ok=new JButton("安全登录");
        jPanel222.add(ok);
        ok.setPreferredSize(new Dimension(254,35));
        ok.setFont(new Font("宋体",Font.BOLD,18));
        ok.setBackground(new Color(8,189,252));
        ok.setForeground(new Color(255,215,0));

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /**
                 * 判断账号和密码框是否有内容
                 */
                String account=jTextField.getText();//账号
                String password=new String(jPasswordField.getPassword());//密码
                if(account.equals("")){
                    Tools.showMessage("请输入账号!");
                }else if(password.equals("")){
                    Tools.showMessage("请输入密码!");
                }else {
                    //查询数据库 当前账号是否存在 是否在线 是否强制登陆
                    Admin admin = AdminDao.islogin(account, password);
                    if (admin == null) {
                        Tools.showMessage("账号或密码错误");
                    } else {
                        LoginView.pow = admin.getPow();//给权限的值
                        //已经登陆成功
                        //判断这个账号是不是已经登录
                         uuid=UUID.randomUUID().toString().replace("-","").toString();
                        if (admin.getSta().equals("0")){ //没有在线
//                            Tools.showMessage("登录成功");
                            new AdminDao().update(account,uuid);//实现更改帐号状态
                            ManageView window = new ManageView();
                            window.frame.setVisible(true);
                            frame.dispose();
                        }else {//在线登录

                               int a = JOptionPane.showConfirmDialog(null,"当前账号已经登录，是否继续登录","登录消息",JOptionPane.YES_NO_OPTION);
                                //0代表确认     1代表  否

                            if(a==0)
                            {
                                //把当前生成的UUID更改账号
                                //需要更改账号是否在线的字段值
                                new AdminDao().update(account,uuid);//实现更改帐号状态
                                ManageView window = new ManageView();
                                window.frame.setVisible(true);
                                frame.dispose();
                            }




                        }

                    }

                }


                }



        });




        JLabel register=new JLabel("注册账户>");
        jPanel.add(register);
        register.setBounds(10,200,100,40);
        register.setFont(new Font("宋体",Font.PLAIN,15));
        register.setForeground(new Color(245,245,220));
        register.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterView window = new RegisterView();
                window.frame.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        //只能有一个账号能登录，额外登录会挤掉下一个账号








        jPanel.add(label);
    }


}
