package com.hafele.iframe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.hafele.dao.AdminDao;
import com.hafele.util.MD5Util;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月28日 下午1:23:24
* 类说明
*/
@SuppressWarnings("serial")
public class UserAddIFrame extends JInternalFrame {
	private JPasswordField passwordTxt;
	private JTextField idCardTxt;
	private JTextField telNumTxt;
	private JFormattedTextField regdateTxt;
	private JTextField ageTxt;
	private JTextField userNameTxt;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JTextField userNumberTxt;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("static-access")
	public UserAddIFrame() {
		setTitle("\u6DFB\u52A0\u7BA1\u7406\u5458");
		setIconifiable(true);
		setFrameIcon(new ImageIcon(UserAddIFrame.class.getResource("/icon/User_add_16px.png")));
		setClosable(true);
		setBounds(100, 100, 380, 415);
		
		JPanel panel_info = new JPanel();
		getContentPane().add(panel_info, BorderLayout.CENTER);
		
		JLabel userNameLabel = new JLabel("\u7528\u6237\u59D3\u540D\uFF1A");
		
		JLabel sexLabel = new JLabel("\u6027    \u522B\uFF1A");
		
		JLabel ageLabel = new JLabel("\u5E74    \u9F84\uFF1A");
		
		JLabel regdateTabel = new JLabel("\u6CE8\u518C\u65E5\u671F\uFF1A");
		
		JLabel telNumLabel = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		
		JLabel idCardLabel = new JLabel("\u8EAB \u4EFD \u8BC1\uFF1A");
		
		JLabel passwordLabel = new JLabel("\u5BC6    \u7801\uFF1A");
		
		passwordTxt = new JPasswordField();
		
		idCardTxt = new JTextField();
		idCardTxt.setColumns(10);
		
		telNumTxt = new JTextField();
		telNumTxt.setColumns(10);
		
		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		regdateTxt = new JFormattedTextField(myfmt.getDateInstance());
		regdateTxt.setColumns(10);
		regdateTxt.setValue(new java.util.Date());
		
		ageTxt = new JTextField();
		ageTxt.setColumns(10);
		
		userNameTxt = new JTextField();
		userNameTxt.setColumns(10);
		
		JPanel panel = new JPanel();
		
		JLabel userNumberLabel = new JLabel("\u7528\u6237\u7F16\u53F7\uFF1A");
		
		System.out.println((int)((Math.random()*9+1)*1000));
		userNumberTxt = new JTextField("EPC"+(int)((Math.random()*9+1)*1000));
		userNumberTxt.setColumns(10);
		userNumberTxt.setEditable(false);
		
		GroupLayout groupLayout = new GroupLayout(panel_info);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(userNumberLabel)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addComponent(idCardLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addComponent(telNumLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addComponent(regdateTabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
									.addGap(46)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(regdateTxt, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
										.addComponent(telNumTxt, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
										.addComponent(idCardTxt, 153, 153, 153)
										.addComponent(passwordTxt, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(userNameLabel)
										.addComponent(sexLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
										.addComponent(ageLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
									.addGap(46)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(ageTxt, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
										.addComponent(panel, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
										.addComponent(userNumberTxt, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
										.addComponent(userNameTxt, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))))
							.addGap(53))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(userNumberLabel)
						.addComponent(userNumberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(userNameLabel)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(26)
							.addComponent(sexLabel)))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ageLabel)
						.addComponent(ageTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(regdateTabel)
						.addComponent(regdateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(telNumLabel)
						.addComponent(telNumTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(idCardLabel)
						.addComponent(idCardTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		radioButton1 = new JRadioButton("\u7537");
		panel.add(radioButton1);
		radioButton1.setSelected(true);
		buttonGroup.add(radioButton1);
		
		radioButton2 = new JRadioButton("\u5973");
		panel.add(radioButton2);
		buttonGroup.add(radioButton2);
		
		panel_info.setLayout(groupLayout);
		
		JPanel panel_button = new JPanel();
		getContentPane().add(panel_button, BorderLayout.SOUTH);
		
		JButton saveButton = new JButton("\u4FDD\u5B58");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordTxt.getPassword());
				String passwordMD5 = MD5Util.convertMD5(password);
				if(userNameTxt.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "用户名不能为空。");
					return;
				}
				if(userNameTxt.getText().length() > 12) {
					JOptionPane.showMessageDialog(null, "用户名位数不能超过12位数。");
					return;
				}
				if(ageTxt.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "年龄不能为空。");
					return;
				}
				if(regdateTxt.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "注册日期不能为空。");
					return;
				}
				if(telNumTxt.getText().length() != 11) {
					JOptionPane.showMessageDialog(null, "电话号码必须为11位数。");
					return;
				}
				if(idCardTxt.getText().length() != 18) {
					JOptionPane.showMessageDialog(null, "身份证号码必须为18位数。");
					return;
				}
				if(passwordMD5.length() == 0) {
					JOptionPane.showMessageDialog(null, "密码不能为空。");
					return;
				}
				
				String sex="1";
				if(!radioButton1.isSelected()){
					sex="2";
				}
				int i = AdminDao.insertAdmin(userNumberTxt.getText(),userNameTxt.getText(),passwordMD5,sex,ageTxt.getText(),Date.valueOf(regdateTxt.getText()),telNumTxt.getText(),idCardTxt.getText());
				if(i == 1) {
					JOptionPane.showMessageDialog(null, "添加成功！");
					doDefaultCloseAction();
				}
			}
		});
		saveButton.setIcon(new ImageIcon(UserAddIFrame.class.getResource("/icon/save_button_16px.png")));
		panel_button.add(saveButton);
		
		JButton btnNewButton = new JButton("\u53D6\u6D88");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		btnNewButton.setIcon(new ImageIcon(UserAddIFrame.class.getResource("/icon/cancel_16px.png")));
		panel_button.add(btnNewButton);

		//设置窗体可见
		this.setVisible(true);
	}
}
