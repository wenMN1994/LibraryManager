package com.hafele.iframe;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.hafele.dao.BookStyleDao;
import com.hafele.model.BookStyle;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月30日 上午12:37:53
* 图书类别添加
*/
@SuppressWarnings("serial")
public class BookTypeAddIFrame extends JInternalFrame {
	private JTextField bookStyleNumberTxt;
	private JTextField bookStyleTxt;
	private JTextField borrowDaysTxt;
	private JTextField amerceTxt;
	private int getBookStyleNumber(List<?> list){
		//tag等于1时，表示该图书类型编号存在
		int tag = 0;
		Object[] bookStyles = new Object[list.size()];
		for(int i=0;i<list.size();i++){
			BookStyle bookStyle = (BookStyle) list.get(i);
			bookStyles[i] = bookStyle.getBookStyleNumber();
			if(bookStyles[i].equals(bookStyleNumberTxt.getText())) {
				tag = 1;
			}
		}
		return tag;
	}

	/**
	 * Create the frame.
	 */
	public BookTypeAddIFrame() {
		setTitle("图书类别添加");
		setFrameIcon(new ImageIcon(BookTypeAddIFrame.class.getResource("/icon/type_16px.png")));
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 500, 380);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BookTypeAddIFrame.class.getResource("/icon/bookTypeAdd.jpg")));
		panel.add(lblNewLabel);
		
		JPanel panel_Button = new JPanel();
		getContentPane().add(panel_Button, BorderLayout.SOUTH);
		
		JButton saveButton = new JButton("添加");
		saveButton.addActionListener(new SaveButtonActionListener());
		saveButton.setIcon(new ImageIcon(BookTypeAddIFrame.class.getResource("/icon/save_button_16px.png")));
		panel_Button.add(saveButton);
		
		JButton closeButton = new JButton("关闭");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		closeButton.setIcon(new ImageIcon(BookTypeAddIFrame.class.getResource("/icon/logoff_16px.png")));
		panel_Button.add(closeButton);
		
		JPanel panel_Info = new JPanel();
		getContentPane().add(panel_Info, BorderLayout.CENTER);
		
		JLabel bookStyleNumberLabel = new JLabel("图书类别编号：");
		
		JLabel bookStyleLabel = new JLabel("图书类别名称：");
		
		JLabel borrowDaysLabel = new JLabel("可借阅天数：");
		
		JLabel amerceLabel = new JLabel("罚款(天/元)：");
		
		bookStyleNumberTxt = new JTextField();
		bookStyleNumberTxt.setColumns(10);
		
		bookStyleTxt = new JTextField();
		bookStyleTxt.setColumns(10);
		
		borrowDaysTxt = new JTextField();
		borrowDaysTxt.setColumns(10);
		
		amerceTxt = new JTextField();
		amerceTxt.setColumns(10);
		GroupLayout gl_panel_Info = new GroupLayout(panel_Info);
		gl_panel_Info.setHorizontalGroup(
			gl_panel_Info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Info.createSequentialGroup()
					.addGap(77)
					.addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addComponent(amerceLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(amerceTxt, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addComponent(borrowDaysLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(borrowDaysTxt, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addComponent(bookStyleLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(bookStyleTxt))
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addComponent(bookStyleNumberLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(bookStyleNumberTxt, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(68, Short.MAX_VALUE))
		);
		gl_panel_Info.setVerticalGroup(
			gl_panel_Info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Info.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookStyleNumberLabel)
						.addComponent(bookStyleNumberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookStyleLabel)
						.addComponent(bookStyleTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
						.addComponent(borrowDaysLabel)
						.addComponent(borrowDaysTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
						.addComponent(amerceLabel)
						.addComponent(amerceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		panel_Info.setLayout(gl_panel_Info);
		
		//设置窗体可见
		this.setVisible(true);
	}
	
	//保存按钮监听事件
	class SaveButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String bookStyleNumber =  bookStyleNumberTxt.getText();
			String bookStyle = bookStyleTxt.getText();
			String borrowDays = borrowDaysTxt.getText();
			String amerce = amerceTxt.getText();
			if(getBookStyleNumber(BookStyleDao.selectBookStyle()) == 1) {
				JOptionPane.showMessageDialog(null, "该图书类型编号已存在，请重新输入。");
				return;
			}
			
			if(bookStyleNumber.length() == 0) {
				 JOptionPane.showMessageDialog(null, "图书类型编号不能为空。");
				 return;
			 }
			 if(bookStyleNumber.length() != 6) {
				 JOptionPane.showMessageDialog(null, "图书类型编号必须为6位数。");
				 return;
			 }
			 if(bookStyle.length() == 0) {
				 JOptionPane.showMessageDialog(null, "图书类型名称不能为空。");
				 return;
			 }
			 if(borrowDays.length() == 0) {
				 JOptionPane.showMessageDialog(null, "可借阅天数不能为空。");
				 return;
			 }
			 if(amerce.length() == 0) {
				 JOptionPane.showMessageDialog(null, "罚款金额不能为空。");
				 return;
			 }
			 int i = BookStyleDao.insertBookStyle(bookStyleNumber,bookStyle,borrowDays,amerce);
			 if(i == 1) {
				 JOptionPane.showMessageDialog(null, "添加成功。");
				 bookStyleNumberTxt.setText(null);
				 bookStyleTxt.setText(null);
				 borrowDaysTxt.setText(null);
				 amerceTxt.setText(null);
			 }
		}
	}
}
