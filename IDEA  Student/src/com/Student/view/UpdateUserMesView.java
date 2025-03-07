package com.Student.view;

import com.Student.dao.AdminDao;
import com.Student.utils.Tools;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateUserMesView {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;



	/**
	 * Create the application.
	 */
	public UpdateUserMesView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("更改用户信息");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("账号");
		lblNewLabel.setBounds(84, 64, 58, 15);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(152, 61, 158, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setBounds(84, 115, 58, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(152, 112, 158, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("密码");
		lblNewLabel_2.setBounds(84, 160, 58, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(152, 157, 158, 21);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("修改用户信息");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnNewButton.setBounds(152, 198, 158, 40);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String account=textField.getText();
				String xm=textField_1.getText();
				String pwd=new String(passwordField.getPassword());
				if (account.equals("")){
					Tools.showMessage("请输入账号");
				}else if (xm.equals("")){
					Tools.showMessage("请输入姓名");
				}else if (pwd.equals("")){
					Tools.showMessage("请输入密码");
				}else {
					int res=AdminDao.updateUserMes(account,xm,pwd);
					if (res==1){
						Tools.showMessage("更改成功");
					}else {
						Tools.showMessage("更改失败");
					}



				}
			}
		});
	}
}
