package com.hafele.iframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.hafele.dao.AdminDao;
import com.hafele.model.Admin;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月28日 下午11:29:14
* 类说明
*/
@SuppressWarnings("serial")
public class UserModiAndDelIFrame extends JInternalFrame {
	private JTable table;
	private String[] str = { "用户编号", "用户姓名", "性别", "年龄", "身份证", "注册日期", "电话", "密码" } ;
	private JPasswordField passwordTxt;
	private JTextField userNumberTxt;
	private JTextField regdateTxt;
	private JTextField idCardTxt;
	private JTextField ageTxt;
	private JTextField userNameTxt;
	private JTextField telNumberTxt;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton JRadioButton1;
	private JRadioButton JRadioButton2;
	private Admin userName = BookLoginIFrame.getUser();
	
	private Object[][] getFileStates(List<?> list){
		Object[][] admins = new Object[list.size()][str.length];
		for(int i=0;i<list.size();i++){
			Admin admin=(Admin)list.get(i);
			admins[i][0]=admin.getSys_number();
			admins[i][1]=admin.getSys_name();
			String sex;
			if(admin.getSys_sex().equals("1")){
				sex="男";
			} else {
				sex="女";
			}
			admins[i][2]=sex;
			admins[i][3]=admin.getSys_age();
			admins[i][4]=admin.getSys_idCard();
			admins[i][5]=admin.getSys_regdate();
			admins[i][6]=admin.getSys_telNumber();
			admins[i][7]=admin.getSys_password();
		}
		return admins;     		
	}

	/**
	 * Create the frame.
	 */
	public UserModiAndDelIFrame() {
		setFrameIcon(new ImageIcon(UserModiAndDelIFrame.class.getResource("/icon/User_Settings_16px.png")));
		setClosable(true);
		setIconifiable(true);
		setTitle("\u7BA1\u7406\u5458\u4FE1\u606F\u4FEE\u6539\u4E0E\u5220\u9664");
		setBounds(100, 100, 500, 400);
		
		JPanel table_panel = new JPanel();
		
		JPanel info_panel = new JPanel();
		
		JPanel button_panel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(table_panel, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
				.addComponent(info_panel, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(button_panel, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(table_panel, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(info_panel, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(11))
		);
		
		JButton modifyButton = new JButton("\u4FEE\u6539");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userNumber = userNumberTxt.getText();
				String userName = userNameTxt.getText();
				String age = ageTxt.getText();
				String idCard = idCardTxt.getText();
				String regdate = regdateTxt.getText();
				String telNumber = telNumberTxt.getText();
				if(age.length() == 0) {
					JOptionPane.showMessageDialog(null, "年龄不能为空。");
					return;
				}
				if(regdate.length() == 0) {
					JOptionPane.showMessageDialog(null, "注册日期不能为空。");
					return;
				}
				if(idCard.length() == 0) {
					JOptionPane.showMessageDialog(null, "身份证号码不能为空。");
					return;
				}
				if(idCard.length() != 18) {
					JOptionPane.showMessageDialog(null, "身份证号码位数必须为18位数。");
					return;
				}
				if(telNumber.length() == 0) {
					JOptionPane.showMessageDialog(null, "电话号码不能为空。");
					return;
				}
				if(telNumber.length() != 11) {
					JOptionPane.showMessageDialog(null, "电话号码位数必须为11位数。");
					return;
				}
				String sex = "1";
				if(JRadioButton2.isSelected()) {
					sex = "2";
				}
				
				int i = AdminDao.updateAdmin(userNumber,userName,sex,age,idCard,Date.valueOf(regdate),telNumber);
				if(i == 1) {
					JOptionPane.showMessageDialog(null, "修改成功");
					Object[][] results=getFileStates(AdminDao.selectuser());
					DefaultTableModel model=new DefaultTableModel();					
					table.setModel(model);
					model.setDataVector(results, str);
				}
			}
		});
		modifyButton.setIcon(new ImageIcon(UserModiAndDelIFrame.class.getResource("/icon/change_16px.png")));
		
		JButton deleteButton = new JButton("\u5220\u9664");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userName.getSys_name().equals(userNameTxt.getText().trim())) {
					JOptionPane.showMessageDialog(null, "当前管理员正在登录，无法删除！");
					return;
				}
				String adminNumber = userNumberTxt.getText().trim();
				System.out.println("adminNumber"+adminNumber);
				int i = AdminDao.deleteAdmin(adminNumber);
				if(i == 1) {
					JOptionPane.showMessageDialog(null, "删除成功");
					Object[][] results=getFileStates(AdminDao.selectuser());
					DefaultTableModel model=new DefaultTableModel();					
					table.setModel(model);
					model.setDataVector(results, str);
				}
			}
		});
		deleteButton.setIcon(new ImageIcon(UserModiAndDelIFrame.class.getResource("/icon/delete_16px.png")));
		
		JButton cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		cancelButton.setIcon(new ImageIcon(UserModiAndDelIFrame.class.getResource("/icon/cancel_16px.png")));
		GroupLayout gl_button_panel = new GroupLayout(button_panel);
		gl_button_panel.setHorizontalGroup(
			gl_button_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_button_panel.createSequentialGroup()
					.addGap(87)
					.addComponent(modifyButton)
					.addGap(43)
					.addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(82, Short.MAX_VALUE))
		);
		gl_button_panel.setVerticalGroup(
			gl_button_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_button_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_button_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(modifyButton)
						.addComponent(deleteButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		button_panel.setLayout(gl_button_panel);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u7F16\u53F7\uFF1A");
		
		JLabel label = new JLabel("\u7528\u6237\u59D3\u540D\uFF1A");
		
		JLabel label_1 = new JLabel("\u6027    \u522B\uFF1A");
		
		JLabel label_2 = new JLabel("\u5E74    \u9F84\uFF1A");
		
		JLabel label_3 = new JLabel("\u8EAB \u4EFD \u8BC1\uFF1A");
		
		JLabel label_4 = new JLabel("\u6CE8\u518C\u65E5\u671F\uFF1A");
		
		JLabel label_5 = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		
		JLabel label_6 = new JLabel("\u5BC6    \u7801\uFF1A");
		
		passwordTxt = new JPasswordField();
		passwordTxt.setEditable(false);
		
		userNumberTxt = new JTextField();
		userNumberTxt.setColumns(10);
		userNumberTxt.setEditable(false);
		
		regdateTxt = new JTextField();
		regdateTxt.setColumns(10);
		
		idCardTxt = new JTextField();
		idCardTxt.setColumns(10);
		
		ageTxt = new JTextField();
		ageTxt.setColumns(10);
		
		userNameTxt = new JTextField();
		userNameTxt.setEditable(false);
		userNameTxt.setColumns(10);
		
		telNumberTxt = new JTextField();
		telNumberTxt.setColumns(10);
		
		JPanel panel = new JPanel();
		GroupLayout gl_info_panel = new GroupLayout(info_panel);
		gl_info_panel.setHorizontalGroup(
			gl_info_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_info_panel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_info_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_info_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(userNumberTxt)
						.addComponent(idCardTxt, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
						.addComponent(telNumberTxt, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
					.addGap(24)
					.addGroup(gl_info_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_info_panel.createSequentialGroup()
							.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_info_panel.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(ageTxt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_info_panel.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_info_panel.createSequentialGroup()
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(regdateTxt)))
					.addGap(34))
		);
		gl_info_panel.setVerticalGroup(
			gl_info_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_info_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_info_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(label)
						.addComponent(userNumberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(userNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_info_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_info_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(label_1)
							.addComponent(label_2)
							.addComponent(ageTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_info_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(label_4)
						.addComponent(regdateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(idCardTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_info_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(label_6)
						.addComponent(passwordTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(telNumberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		
		JRadioButton1 = new JRadioButton("\u7537");
		JRadioButton1.setSelected(true);
		buttonGroup.add(JRadioButton1);
		panel.add(JRadioButton1);
		
		JRadioButton2 = new JRadioButton("\u5973");
		buttonGroup.add(JRadioButton2);
		panel.add(JRadioButton2);
		info_panel.setLayout(gl_info_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_table_panel = new GroupLayout(table_panel);
		gl_table_panel.setHorizontalGroup(
			gl_table_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_table_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_table_panel.setVerticalGroup(
			gl_table_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_table_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
					.addContainerGap())
		);
		table_panel.setLayout(gl_table_panel);
		
		
		Object[][] results=getFileStates(AdminDao.selectuser());
		table = new JTable(results,str);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new TableListener());
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);

		getContentPane().setLayout(groupLayout);
		//设置窗体可见
		this.setVisible(true);
	}
	
	//表格点击事件
	private final class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int selRow = table.getSelectedRow();
			userNumberTxt.setText(table.getValueAt(selRow, 0).toString().trim());
			userNameTxt.setText(table.getValueAt(selRow, 1).toString().trim());
			if(table.getValueAt(selRow, 2).toString().trim().equals("男")) {
				JRadioButton1.setSelected(true);
			}else {
				JRadioButton2.setSelected(true);
			}
			ageTxt.setText(table.getValueAt(selRow, 3).toString().trim());
			idCardTxt.setText(table.getValueAt(selRow, 4).toString().trim());
			regdateTxt.setText(table.getValueAt(selRow, 5).toString().trim());
			telNumberTxt.setText(table.getValueAt(selRow, 6).toString().trim());
			passwordTxt.setText(table.getValueAt(selRow, 7).toString().trim());
		}
	}
}
