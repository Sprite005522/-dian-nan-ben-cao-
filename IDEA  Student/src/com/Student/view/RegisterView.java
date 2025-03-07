package com.Student.view;

import com.Student.dao.AdminDao;
import com.Student.utils.Tools;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class RegisterView {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField textField_2;
	private JPasswordField textField_3;



	/**
	 * Create the application.
	 */
	public RegisterView() {
		initialize();
	}


	private final int WIDTH=410;
	private final int HEIGHT=345;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("注册界面");
		Tools.setPos(frame,WIDTH,HEIGHT);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);


		JLabel lblNewLabel_1 = new JLabel("学生信息管理系统注册");
		lblNewLabel_1.setIcon(new ImageIcon("image/2.jpg"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 396, 308);
		frame.getContentPane().add(lblNewLabel_1);


		JLabel lblNewLabel = new JLabel("学生信息管理系统注册");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 28));
		lblNewLabel.setBounds(0, 15, 396, 60);
		lblNewLabel.setForeground(Color.CYAN);
		frame.getContentPane().add(lblNewLabel);


		JPanel panel = new JPanel();
		panel.setBounds(0, 71, 396, 227);
		frame.getContentPane().add(panel);
		panel.setOpaque(false);
		panel.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("姓         名：");
		lblNewLabel_2.setBounds(10, 20, 97, 15);
		lblNewLabel_2.setForeground(new  Color(245,245,220));
		panel.add(lblNewLabel_2);

		textField = new JTextField();
		textField.setBounds(117, 17, 259, 21);
		panel.add(textField);
		textField.setColumns(10);
		textField.setForeground(new  Color(18,120,192));
		textField.setPreferredSize(new Dimension(15,28));

		JLabel lblNewLabel_3 = new JLabel("账         号：");
		lblNewLabel_3.setBounds(10, 56, 97, 15);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(new  Color(245,245,220));




		textField_1 = new JTextField();
		textField_1.setBounds(117, 53, 259, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		textField_1.setForeground(new  Color(18,120,192));
		textField_1.setPreferredSize(new Dimension(15,28));




		JLabel lblNewLabel_4 = new JLabel("密         码：");
		lblNewLabel_4.setBounds(10, 86, 97, 15);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setForeground(new  Color(245,245,220));




		textField_2 = new JPasswordField();
		textField_2.setBounds(117, 84, 259, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setForeground(new  Color(18,120,192));
		textField_2.setPreferredSize(new Dimension(15,28));




		JLabel lblNewLabel_5 = new JLabel("确  认  密 码：");
		lblNewLabel_5.setBounds(10, 123, 97, 15);
		panel.add(lblNewLabel_5);
		lblNewLabel_5.setForeground(new  Color(245,245,220));



		textField_3 = new JPasswordField();
		textField_3.setBounds(117, 120, 259, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);
		textField_3.setForeground(new  Color(18,120,192));
		textField_3.setPreferredSize(new Dimension(15,28));



		JButton btnNewButton = new JButton("注     册");
		btnNewButton.setBounds(10, 164, 366, 34);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("宋体",Font.BOLD,18));
		btnNewButton.setForeground(new Color(0,0,0));


		frame.getContentPane().add(lblNewLabel_1);


		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//注册点击时间

				String xm=textField.getText();
				String account=textField_1.getText();

				String pwd=new String(textField_2.getPassword());
				String qpwd=new String(textField_3.getPassword());

				if (xm.equals("")){
					Tools.showMessage("请输入姓名");
				}else if (account.equals("")){
					Tools.showMessage("请输入账号");
				}else if (pwd.equals("")){
					Tools.showMessage("请输入密码");
				}else if (qpwd.equals("")){
					Tools.showMessage("请输入确认密码");
				}else if (!pwd.equals(qpwd)){
					Tools.showMessage("两次密码不一致");
				}else{

					int a=AdminDao.register(account,pwd,xm,"0","0");
					if (a==0){
						Tools.showMessage("账号冲突，注册失败");
					}else if (a==1){
						Tools.showMessage("注册成功");
					}else {
						Tools.showMessage("报错");
					}

				}
		}






	});
}

}
