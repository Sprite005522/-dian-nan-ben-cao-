package com.Student.view;
import java.awt.*;
import java.util.TimerTask;
import java.util.Timer;
import com.Student.bean.Admin;
import com.Student.bean.Student;
import com.Student.dao.AdminDao;
import com.Student.dao.StudentDao;
import com.Student.utils.DBUntil;
import com.Student.utils.Table;
import com.Student.utils.Tools;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.tools.Tool;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;


public class ManageView {

	 JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	private JTextArea jTextArea;//文本域

	private DefaultTableModel model;



	/**
	 * Create the application.
	 */
	public ManageView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("学生信息管理系统");
		frame.setBounds(100, 100, 800, 725);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("管理");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("查看在线账号");
		mntmNewMenuItem_5.setIcon(new ImageIcon("image/I1.gif"));
		mnNewMenu.add(mntmNewMenuItem_5);
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//查看在线账号

				if (LoginView.pow.equals("1")){
					List<Admin> admins = AdminDao.showAccount();
					jTextArea.setText("账号\t姓名\t状态\t\n");
					for (Admin admin : admins) {
						String str=admin.getAccount()+"\t";
						str=str+admin.getXm()+"\t";
						if (admin.getSta().equals("0")){
							str=str+"不在线\t";
						}else {
							str=str+"在线\t";
						}
						jTextArea.append(str+"\n");
					}

				}else {
					Tools.showMessage("您没有管理员权限");
				}



			}
		});



		JMenuItem mntmNewMenuItem_4 = new JMenuItem("查看所有账号");
		mntmNewMenuItem_4.setIcon(new ImageIcon("image/I2.gif"));
		mnNewMenu.add(mntmNewMenuItem_4);

		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//查看在线账号

				if (LoginView.pow.equals("1")){
					List<Admin> admins = AdminDao.showAllAccount();
					jTextArea.setText("账号\t姓名\t状态\t\n");
					for (Admin admin : admins) {
						String str=admin.getAccount()+"\t";
						str=str+admin.getXm()+"\t";
						if (admin.getSta().equals("0")){
							str=str+"不在线\t";
						}else {
							str=str+"在线\t";
						}
						jTextArea.append(str+"\n");
					}

				}else {
					Tools.showMessage("您没有管理员权限");
				}



			}
		});

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("更改用户信息");
		mntmNewMenuItem_3.setIcon(new ImageIcon("image/I3.gif"));
		mnNewMenu.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (LoginView.pow.equals("1")){
					UpdateUserMesView window = new UpdateUserMesView();
					window.frame.setVisible(true);
				}else {
					Tools.showMessage("您没有管理员权限");
				}

			}
		});






		JMenuItem mntmNewMenuItem_2 = new JMenuItem("更改当前账号密码");
		mntmNewMenuItem_2.setIcon(new ImageIcon("image/I4.gif"));
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateMyPwdView window = new UpdateMyPwdView();
				window.frame.setVisible(true);
			}
		});




		JMenu mnNewMenu_1 = new JMenu("账号");
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("注销");
		mntmNewMenuItem_1.setIcon(new ImageIcon("image/I1_2.jpg"));
		mnNewMenu_1.add(mntmNewMenuItem_1);

		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();

				// 新增：查找是否已有 LoginView 实例
				for (Frame f : Frame.getFrames()) {
					if (f.getTitle().equals("学生信息管理系统")) {
						f.setVisible(true);
						new AdminDao().updateByUuid(LoginView.uuid, "0"); // 实现更改帐号状态
						return;
					}
				}

				LoginView loginView=new LoginView();//打开登陆窗口;
				new AdminDao().updateByUuid(LoginView.uuid,"0");//实现更改帐号状态
			}
		});

		JMenuItem mntmNewMenuItem = new JMenuItem("退出");
		mntmNewMenuItem.setIcon(new ImageIcon("image/I1_1.jpg"));
		mnNewMenu_1.add(mntmNewMenuItem);
		frame.getContentPane().setLayout(null);

		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginView loginView=new LoginView();//打开登陆窗口;
				new AdminDao().updateByUuid(LoginView.uuid,"0");//实现更改帐号状态
				System.exit(0);
			}

		});

		/**
		 * 监听窗口状态
		 */
		frame.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// 新增：查找是否已有 LoginView 实例
				for (Frame f : Frame.getFrames()) {
					if (f.getTitle().equals("学生信息管理系统")) {
						f.setVisible(true);
						new AdminDao().updateByUuid(LoginView.uuid, "0"); // 实现更改帐号状态
						System.exit(0);
						return;
					}
				}
				// 若没有找到，则创建新的登录界面
				LoginView loginView = new LoginView();
				new AdminDao().updateByUuid(LoginView.uuid, "0"); // 实现更改帐号状态
				System.exit(0);

//				new AdminDao().updateByUuid(LoginView.uuid,"0");//实现更改帐号状态
//				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}
		});





		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("image/3.jpg"));
		lblNewLabel.setBounds(0, 0, 800, 100);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "学生数据信息显示", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 110, 766, 100);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setBounds(10, 22, 38, 15);
		panel.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(44, 19, 66, 21);
		panel.add(textField);
		textField.setColumns(10);

		ButtonGroup group=new ButtonGroup();


		JRadioButton rdbtnNewRadioButton = new JRadioButton("男");
		rdbtnNewRadioButton.setBounds(116, 18, 66, 23);
		panel.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("女");
		rdbtnNewRadioButton_1.setBounds(184, 18, 66, 23);
		panel.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton_1);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("全部");
		rdbtnNewRadioButton_2.setBounds(252, 18, 66, 23);
		panel.add(rdbtnNewRadioButton_2);
		group.add(rdbtnNewRadioButton_2);


		/**
		 * 设置一个默认值
		  */
		rdbtnNewRadioButton_2.setSelected(true);//将全部设置为默认选择

		JLabel lblNewLabel_2 = new JLabel("年龄");
		lblNewLabel_2.setBounds(324, 22, 58, 15);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("班级");
		lblNewLabel_3.setBounds(441, 22, 58, 15);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("学号");
		lblNewLabel_4.setBounds(564, 22, 58, 15);
		panel.add(lblNewLabel_4);

		textField_1 = new JTextField();
		textField_1.setBounds(365, 19, 66, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(488, 19, 66, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(606, 19, 66, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("搜索【学号】");
		lblNewLabel_5.setBounds(10, 59, 132, 15);
		panel.add(lblNewLabel_5);

		textField_4 = new JTextField();
		textField_4.setBounds(103, 56, 66, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JButton btnNewButton = new JButton("添加学生");
		btnNewButton.setBounds(179, 55, 97, 23);
		panel.add(btnNewButton);


		/**
		 * 实现添加学生
		 */
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String xm=textField.getText();
				String age=textField_1.getText();
				String bj=textField_2.getText();
				String xh=textField_3.getText();

				String sex=null;
				if (rdbtnNewRadioButton.isSelected()){
					sex="男";
				}
				if (rdbtnNewRadioButton_1.isSelected()){
					sex="女";
				}

				if(xm.equals("")){
					Tools.showMessage("请输入姓名");
				}else if (sex==null){
					Tools.showMessage("请输入性别");
				}else if (age.equals("")){
					Tools.showMessage("请输入年龄");
				}else if (bj.equals("")){
					Tools.showMessage("请输入班级");
				}else if (xh.equals("")){
					Tools.showMessage("请输入学号");
				}else{

					//代表所有内容都已经添加了 将数据放到java bean当中
					Student student=new Student(xh,xm,sex,age,bj);
					int res=new StudentDao().addStudent(student);
					if (res==1){
						Tools.showMessage("添加成功");
					}else {
						Tools.showMessage("学号冲突,请修改学号");
					}


				}


			}
		});






		JButton btnNewButton_1 = new JButton("删除学生");
		btnNewButton_1.setBounds(286, 55, 97, 23);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//删除
				String tj=textField_4.getText();
				if (tj.equals("")){
					Tools.showMessage("请输入要删除的学号");
				}else {
					int res= new StudentDao().delStudent(tj);
					if (res==1){
						Tools.showMessage("删除成功");
					}else {
						Tools.showMessage("删除失败,请检查学号");
					}
				}
			}
		});

		JButton btnNewButton_2 = new JButton("更改学生");
		btnNewButton_2.setBounds(393, 55, 97, 23);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String xm=textField.getText();
				String age=textField_1.getText();
				String bj=textField_2.getText();
				String xh=textField_3.getText();




				String whereSh=textField_4.getText();
				String sex=null;
				if (rdbtnNewRadioButton.isSelected()){
					sex="男";
				}
				if (rdbtnNewRadioButton_1.isSelected()){
					sex="女";
				}

				if(xm.equals("")){
					Tools.showMessage("请输入姓名");
				}else if (sex==null){
					Tools.showMessage("请输入性别");
				}else if (age.equals("")){
					Tools.showMessage("请输入年龄");
				}else if (bj.equals("")){
					Tools.showMessage("请输入班级");
				}else if (xh.equals("")){
					Tools.showMessage("请输入学号");
				}else if (whereSh.equals("")){
					Tools.showMessage("请输入要更改的学号");
				}else {

					//代表所有内容都已经添加了 将数据放到java bean当中
					Student student = new Student(xh, xm, sex, age, bj);
					int a=new StudentDao().updateStudent(student,whereSh);
					if (a==1) {
						Tools.showMessage("更改成功");
					}else{
						Tools.showMessage("更改失败");
					}
				}
			}
		});

		JButton btnNewButton_3 = new JButton("查找学生");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setBounds(498, 55, 97, 23);
		//查找学生的监听
		panel.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {

				//有一个条件模糊查找，根据学号查找，查找全部内容
				String tjXh = textField_4.getText();//条件学号
				if (tjXh.equals("")){
					//想查询全部
					String xm=textField.getText();
					String age=textField_1.getText();
					String bj=textField_2.getText();
					String xh=textField_3.getText();

					String sex=null;
					if (rdbtnNewRadioButton.isSelected()){
						sex="男";
					}
					if (rdbtnNewRadioButton_1.isSelected()){
						sex="女";
					}
					if (rdbtnNewRadioButton_2.isSelected()){
						sex="";
					}


					if (xm.equals("")&&age.equals("")&&bj.equals("")&&xh.equals("")&&sex.equals("")){
						//查询全部
						//Tools.showMessage("查询全部");
						//有东西是不是代表想单独查询这个学号
						//Tools.showMessage("单独查询");//单独查询
						List<Student> stu = new StudentDao().getStudentAll();//得到一个学生实体 条件学号
						try {
							Tools.addTableData(model,stu);
						} catch (IllegalAccessException illegalAccessException) {
							illegalAccessException.printStackTrace();
						}
					}else {
						//条件查询
						Student student=new Student(xh,xm,sex,age,bj);
						try {
							List<Student> stu = new StudentDao().getStudentWhere(student);
							Tools.addTableData(model,stu);
						} catch (IllegalAccessException illegalAccessException) {
							illegalAccessException.printStackTrace();
						}

//						Tools.showMessage("条件查询");
					}


				}else {
					//有东西是不是代表想单独查询这个学号
					//Tools.showMessage("单独查询");//单独查询
					Student stu = new StudentDao().getStudentByNumber(tjXh);//得到一个学生实体 条件学号
					//将这个数据回显到内容
					if (stu!=null){
						textField.setText(stu.getXm());
						textField_1.setText(stu.getAge());
						textField_2.setText(stu.getGrade());
						textField_3.setText(stu.getNumber());

						if (stu.getSex()!=null&&stu.getSex().equals("男")){
							rdbtnNewRadioButton.setSelected(true);
						}
						if (stu.getSex()!=null&&stu.getSex().equals("女")){
							rdbtnNewRadioButton_1.setSelected(false);
						}





					}



					try {
						Tools.addTableData(model,stu);
					} catch (IllegalAccessException illegalAccessException) {
						illegalAccessException.printStackTrace();
					}
				}
			}
		});

		JButton btnNewButton_4 = new JButton("重置");
		btnNewButton_4.setBounds(606, 55, 97, 23);
		panel.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//实现重置功能

				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				rdbtnNewRadioButton_2.setSelected(true);


			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "学生信息显示", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 220, 766, 205);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);











		Object col[] = {"学号", "姓名", "性别", "年龄", "班级"};
		model = new DefaultTableModel(col, 0); // 初始化 DefaultTableModel
		JTable table = new JTable(model); // 使用这个模型来创建表格
		JScrollPane scroll = new JScrollPane(table);
		//scroll.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
		//"账号信息显示", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scroll.setBounds(10, 22, 746, 173);
		panel_1.add(scroll);









		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "账号信息显示", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1.setBounds(10, 435, 766, 219);
		frame.getContentPane().add(panel_1_1);


		jTextArea =new JTextArea();
		JScrollPane scrol_1=new JScrollPane(jTextArea);
		scrol_1.setBounds(10, 22, 746, 187);
		//jTextArea.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "账号信息显示", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1_1.add(scrol_1);


		//当运行管理界面时，会循环监听数据变化，如果变化则关闭当前程序
		// 定义定时器变量，方便在任务内部访问并取消定时器
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				Admin admin = AdminDao.showAccount(LoginView.uuid);
				if (admin == null) { // 表示账号异地登录
					// 取消当前定时器任务，防止重复执行
					this.cancel();
					// 取消定时器，释放资源
					timer.cancel();

					// 新增：查找是否已有 LoginView 实例
					for (Frame f : Frame.getFrames()) {
						if (f.getTitle().equals("学生信息管理系统")) {
							f.setVisible(true);
							frame.dispose();
							Tools.showMessage("您的帐号已经异地登录，被迫下线，请重新登录");
							return;
						}
					}
					// 若没有找到，则创建新的登录界面
					LoginView loginView = new LoginView();
					frame.dispose();
					Tools.showMessage("您的帐号已经异地登录，被迫下线，请重新登录");


//					LoginView loginView = new LoginView(); // 打开登陆窗口
//					frame.dispose();
//					Tools.showMessage("您的帐号已经异地登录，被迫下线，请重新登录");
				}
			}
		};

// 设定任务调度，延迟1秒开始，之后每隔一秒执行一次
		timer.scheduleAtFixedRate(timerTask, 1000L, 1000L);
	}
}