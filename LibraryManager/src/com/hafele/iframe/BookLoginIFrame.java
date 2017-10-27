package com.hafele.iframe;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.Enumeration;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import com.hafele.LibraryMain;
import com.hafele.dao.AdminDao;
import com.hafele.model.Admin;
import com.hafele.util.DbHelper;
import com.hafele.util.MD5Util;
import com.hafele.util.StringHelper;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月19日 下午4:50:08
* 类说明
*/
@SuppressWarnings("serial")
public class BookLoginIFrame extends JFrame {
	private JTextField userNameTxt;
	private JPasswordField passwordTxt;
	private JButton loginButton;
	private JButton resetButton;
	private static Admin currentAdmin;
	
	@SuppressWarnings("rawtypes")
	public BookLoginIFrame() {
		//改变系统默认字体
		Font font = new Font("Dialog", Font.PLAIN, 12);
		Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource) {
				UIManager.put(key, font);
			}
		}
		setResizable(false);
		setTitle("图书馆管理系统登录");
		setBounds(100, 100, 285, 220);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BookLoginIFrame.class.getResource("/icon/book_200px.png")));
		
		JLabel Label_toppicture = new JLabel("");
		Label_toppicture.setIcon(new ImageIcon(BookLoginIFrame.class.getResource("/icon/login.jpg")));
		
		JPanel panel_info = new JPanel();
		
		JPanel panel_button = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(Label_toppicture, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
				.addComponent(panel_info, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
				.addComponent(panel_button, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(Label_toppicture, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_info, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_button, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		//登录按钮逻辑
		loginButton = new JButton("登录");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = userNameTxt.getText();
				String password = new String(passwordTxt.getPassword());
				String passwordMD5 = MD5Util.convertMD5(password);
				if(StringHelper.isEmpty(userName)) {
					JOptionPane.showMessageDialog(null, "用户名不能为空！");
					return;
				}
				if(StringHelper.isEmpty(password)) {
					JOptionPane.showMessageDialog(null, "密码不能为空！");
				}
				Admin admin = new Admin(userName,passwordMD5);
				Connection conn=null;
				try {
					conn = DbHelper.getConnection();
					currentAdmin = AdminDao.login(conn, admin);
					if(currentAdmin!=null){
						dispose();
						new LibraryMain().setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "用户名或者密码错误！");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		loginButton.setIcon(new ImageIcon(BookLoginIFrame.class.getResource("/icon/login_button_16px.png")));
		
		//重置按钮逻辑
		resetButton = new JButton("重置");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userNameTxt.setText("");
				passwordTxt.setText("");
			}
		});
		resetButton.setIcon(new ImageIcon(BookLoginIFrame.class.getResource("/icon/reset_button_16px.png")));
		GroupLayout gl_panel_button = new GroupLayout(panel_button);
		gl_panel_button.setHorizontalGroup(
			gl_panel_button.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_button.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addComponent(loginButton)
					.addGap(31)
					.addComponent(resetButton)
					.addGap(37))
		);
		gl_panel_button.setVerticalGroup(
			gl_panel_button.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_button.createSequentialGroup()
					.addGroup(gl_panel_button.createParallelGroup(Alignment.BASELINE)
						.addComponent(loginButton)
						.addComponent(resetButton))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		panel_button.setLayout(gl_panel_button);
		
		JLabel UserLabel = new JLabel("用 户 名：");
		UserLabel.setIcon(new ImageIcon(BookLoginIFrame.class.getResource("/icon/user_16px.png")));
		
		JLabel passwordLabel = new JLabel("密    码：");
		passwordLabel.setIcon(new ImageIcon(BookLoginIFrame.class.getResource("/icon/Password_16px.png")));
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setColumns(10);
		passwordTxt.setEchoChar('*');//设置密码框的回显字符
		passwordTxt.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == 10)
					loginButton.doClick();
			}
		});
		GroupLayout gl_panel_info = new GroupLayout(panel_info);
		gl_panel_info.setHorizontalGroup(
			gl_panel_info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_info.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_info.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_info.createSequentialGroup()
							.addComponent(UserLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_info.createSequentialGroup()
							.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(passwordTxt)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_panel_info.setVerticalGroup(
			gl_panel_info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_info.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_info.createParallelGroup(Alignment.BASELINE)
						.addComponent(UserLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_info.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_info.setLayout(gl_panel_info);
		getContentPane().setLayout(groupLayout);
		
		//设置窗体可见
		this.setVisible(true);
		//设置窗体居中
		this.setLocationRelativeTo(null);
	}
	public static Admin getUser() {
		return currentAdmin;
	}
	public static void setUser(Admin currentAdmin) {
		BookLoginIFrame.currentAdmin = currentAdmin;
	}

}
