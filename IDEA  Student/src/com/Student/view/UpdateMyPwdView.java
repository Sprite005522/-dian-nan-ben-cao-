package com.Student.view;

import com.Student.dao.AdminDao;
import com.Student.utils.Tools;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.tools.Tool;

public class UpdateMyPwdView {

	JFrame frame;
	private JPasswordField passwordField;



	/**
	 * Create the application.
	 */
	public UpdateMyPwdView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("更改密码");
		frame.setBounds(100, 100, 450, 160);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("密码");
		lblNewLabel.setBounds(60, 37, 58, 15);
		frame.getContentPane().add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(128, 34, 186, 21);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("更改密码");
		btnNewButton.setBounds(128, 74, 186, 23);
		frame.getContentPane().add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pwd=new String(passwordField.getPassword());
				if (pwd.equals("")){
					Tools.showMessage("请输入密码");
				}else {
					int a = AdminDao.updatePwd(LoginView.uuid,pwd);
					if (a==1){
						Tools.showMessage("更改成功");
					}else {
						Tools.showMessage("更改失败");
					}
				}
			}
		});
	}

}
