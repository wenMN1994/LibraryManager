package com.hafele.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.hafele.dao.ReaderInfoDao;
import com.hafele.model.ReadersInfo;
import com.hafele.util.MyDocument;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月23日 下午7:21:50
* 读者信息修改与删除窗体实现
*/
@SuppressWarnings("serial")
public class ReaderModiAndDelIFrame extends JInternalFrame {
	
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox<?> validDocumentation;
	private JTable table;
	private String[] columnNames = {"读者姓名", "读者性别", "读者年龄", "职业", "证件", "证件号码", "电话", "押金", "注册日期" ,"读者编号"};
	private String[] array = new String[]{"身份证","军人证","学生证","工作证"};
	private JTextField nameTxt;
	private JTextField ageTxt;
	private JTextField occupationTxt;
	private JTextField idNumberTxt;
	private JTextField telNumberTxt;
	private JTextField depositTxt;
	private JFormattedTextField regdateTxt;
	private JTextField readerNumTxt;
	private JRadioButton JRadioButton1;
	private JRadioButton JRadioButton2;
	
	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	public ReaderModiAndDelIFrame() {
		super();
		setTitle("\u8BFB\u8005\u4FE1\u606F\u4FEE\u6539\u4E0E\u5220\u9664");
		setIconifiable(true);
		setFrameIcon(new ImageIcon(ReaderModiAndDelIFrame.class.getResource("/icon/change_16px.png")));
		setClosable(true);
		setBounds(100, 100, 600, 459);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ReaderModiAndDelIFrame.class.getResource("/icon/readerModiAndDel.jpg")));
		getContentPane().add(label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(0,100));
		
		DefaultTableModel model = new DefaultTableModel();
		Object[][] results=getFileStates(ReaderInfoDao.selectReader());
		model.setDataVector(results,columnNames);
		
		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new TableListener());
		
		JPanel panel_Info = new JPanel();
		
		JPanel panel_button = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
				.addComponent(panel_Info, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_button, GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_Info, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_button, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
					.addGap(143))
		);
		
		JButton button_modify = new JButton("\u4FEE\u6539");
		button_modify.setIcon(new ImageIcon(ReaderModiAndDelIFrame.class.getResource("/icon/change_16px.png")));
		button_modify.addActionListener(new modifyButtonListener(model));
		
		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.setIcon(new ImageIcon(ReaderModiAndDelIFrame.class.getResource("/icon/delete_16px.png")));
		button_delete.addActionListener(new DelButtonListener(model));
		
		GroupLayout gl_panel_button = new GroupLayout(panel_button);
		gl_panel_button.setHorizontalGroup(
			gl_panel_button.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_button.createSequentialGroup()
					.addGap(133)
					.addComponent(button_modify)
					.addGap(142)
					.addComponent(button_delete, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(155, Short.MAX_VALUE))
		);
		gl_panel_button.setVerticalGroup(
			gl_panel_button.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_button.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_button.createParallelGroup(Alignment.LEADING)
						.addComponent(button_delete, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_modify, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_button.setLayout(gl_panel_button);
		
		JLabel nameLabel = new JLabel("\u59D3        \u540D\uFF1A");
		
		JLabel sexLabel = new JLabel("\u6027        \u522B\uFF1A");
		
		JLabel label_1 = new JLabel("\u5E74        \u9F84\uFF1A");
		
		JLabel label_2 = new JLabel("\u804C        \u4E1A\uFF1A");
		
		JLabel label_3 = new JLabel("\u6709\u6548\u8BC1\u4EF6\uFF1A");
		
		JLabel label_4 = new JLabel("\u8BC1\u4EF6\u53F7\u7801\uFF1A");
		
		JLabel label_5 = new JLabel("\u7535        \u8BDD\uFF1A");
		
		JLabel label_6 = new JLabel("\u62BC        \u91D1\uFF1A");
		
		JLabel label_7 = new JLabel("\u6CE8\u518C\u65E5\u671F\uFF1A");
		
		JLabel label_8 = new JLabel("\u8BFB\u8005\u7F16\u53F7\uFF1A");
		
		nameTxt = new JTextField();
		nameTxt.setColumns(10);
		
		ageTxt = new JTextField();
		ageTxt.setDocument(new MyDocument(2));//控制该文本域最大输入两位数
		ageTxt.addKeyListener(new NumberListener());
		
		occupationTxt = new JTextField();
		occupationTxt.setDocument(new MyDocument(30));
		
		idNumberTxt = new JTextField();
		idNumberTxt.setColumns(10);
		
		telNumberTxt = new JTextField();
		telNumberTxt.setDocument(new MyDocument(11));//控制该文本域最大输入两位数
		telNumberTxt.addKeyListener(new NumberListener());
		
		depositTxt = new JTextField();
		depositTxt.setColumns(10);
		depositTxt.addKeyListener(new KeepmoneyListener());
		
		SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
		regdateTxt = new JFormattedTextField(myfmt.getDateInstance());
		
		readerNumTxt = new JTextField();
		readerNumTxt.setColumns(10);
		
		validDocumentation = new JComboBox();
		validDocumentation.setModel(new DefaultComboBoxModel(array));
		for(int i=1;i<array.length;i++){
			validDocumentation.setSelectedIndex(i);
			validDocumentation.setSelectedItem(array);
		}
		
		JPanel panel_radioButton = new JPanel();
		
		JRadioButton1 = new JRadioButton("\u7537");
		buttonGroup.add(JRadioButton1);
		panel_radioButton.add(JRadioButton1);
		
		JRadioButton2 = new JRadioButton("\u5973");
		buttonGroup.add(JRadioButton2);
		panel_radioButton.add(JRadioButton2);
		GroupLayout gl_panel_Info = new GroupLayout(panel_Info);
		gl_panel_Info.setHorizontalGroup(
			gl_panel_Info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Info.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addComponent(nameLabel)
							.addGap(10)
							.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(sexLabel)
							.addGap(18)
							.addComponent(panel_radioButton, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addComponent(label_1)
							.addGap(10)
							.addComponent(ageTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(label_2)
							.addGap(18)
							.addComponent(occupationTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(validDocumentation, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(idNumberTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addComponent(label_5)
							.addGap(10)
							.addComponent(telNumberTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(label_6)
							.addGap(18)
							.addComponent(depositTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(regdateTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(readerNumTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))))
		);
		gl_panel_Info.setVerticalGroup(
			gl_panel_Info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Info.createSequentialGroup()
					.addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addGap(3)
							.addComponent(nameLabel))
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addGap(3)
							.addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addGap(3)
							.addComponent(sexLabel))
						.addComponent(panel_radioButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addGap(3)
							.addComponent(label_1))
						.addComponent(ageTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addGap(3)
							.addComponent(label_2))
						.addComponent(occupationTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addGap(3)
							.addComponent(label_3))
						.addComponent(validDocumentation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addGap(3)
							.addComponent(label_4))
						.addComponent(idNumberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addGap(3)
							.addComponent(label_5))
						.addComponent(telNumberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addGap(3)
							.addComponent(label_6))
						.addComponent(depositTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING)
						.addComponent(label_7)
						.addComponent(regdateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_Info.createSequentialGroup()
							.addGap(3)
							.addComponent(label_8))
						.addComponent(readerNumTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		panel_Info.setLayout(gl_panel_Info);
		panel.setLayout(gl_panel);
		
		//设置窗体可见
		this.setVisible(true);
	}

	//向表格中加载数据
	private Object[][] getFileStates(List<?> list) {
		Object[][] results = new Object[list.size()][columnNames.length];
		for(int i = 0; i < list.size(); i++) {
			ReadersInfo readerInfo=(ReadersInfo)list.get(i);
			results[i][0]=readerInfo.getReaderName();
			String sex;
			if(readerInfo.getReaderSex().equals("1")){
				sex="男";
			}
			else
				sex="女";
			results[i][1]=sex;
			results[i][2]=readerInfo.getReaderAge();
			results[i][3]=readerInfo.getReaderOccupation();
			results[i][4]=array[readerInfo.getReaderIdType()];
			results[i][5]=readerInfo.getReaderIdNumber();
			results[i][6]=readerInfo.getReaderTelNumber();
			results[i][7]=readerInfo.getGuaranteeDeposit();
			results[i][8]=readerInfo.getRegdate();
			results[i][9]=readerInfo.getReaderId();
		}
		return results;
	}
	
	//表格数据点击事件
	private final class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int selRow = table.getSelectedRow();
			nameTxt.setText(table.getValueAt(selRow, 0).toString().trim());
			if(table.getValueAt(selRow, 1).toString().trim().equals("男")) {
				JRadioButton1.setSelected(true);
			}else {
				JRadioButton2.setSelected(true);
			}
			ageTxt.setText(table.getValueAt(selRow, 2).toString().trim());
			occupationTxt.setText(table.getValueAt(selRow, 3).toString().trim());
			validDocumentation.setSelectedItem(table.getValueAt(selRow, 4).toString().trim());
			idNumberTxt.setText(table.getValueAt(selRow, 5).toString().trim());
			telNumberTxt.setText(table.getValueAt(selRow, 6).toString().trim());
			depositTxt.setText(table.getValueAt(selRow, 7).toString().trim());
			regdateTxt.setText(table.getValueAt(selRow, 8).toString().trim());
			readerNumTxt.setText(table.getValueAt(selRow, 9).toString().trim());
		}
	}
	
	//修改按钮事件监听
	private final class modifyButtonListener implements ActionListener{
		private final DefaultTableModel model;
		
		private modifyButtonListener(DefaultTableModel model) {
			this.model = model;
		}

		@Override
		public void actionPerformed(final ActionEvent ae) {
			if(nameTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "读者姓名不能为空！");
				return;
			}
			if(ageTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "读者年龄不能为空！");
				return;
			}
			if(occupationTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "读者职业不能为空！");
				return;
			}
			if(occupationTxt.getText().length() > 20) {
				JOptionPane.showMessageDialog(null, "读者职业信息不能超过20个字符！");
				return;
			}
			if(idNumberTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "读者证件号码不能为空！");
				return;
			}
			if(idNumberTxt.getText().length() < 10) {
				JOptionPane.showMessageDialog(null, "读者证件号码不能小于10位数！");
				return;
			}
			if(telNumberTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "读者电话号码不能为空！");
				return;
			}
			if(telNumberTxt.getText().length() > 11 || telNumberTxt.getText().length() < 0) {
				JOptionPane.showMessageDialog(null, "电话号码位数小于11位！");
				return;
			}
			if(depositTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "押金不能为空！");
				return;
			}
			if(regdateTxt.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "时间格式请使用\"2017-09-22\"格式");
				return;
			}
			if(readerNumTxt.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "读者编号不能为空！");
				return;
			}
			if(readerNumTxt.getText().length() != 8) {
				JOptionPane.showMessageDialog(null, "读者编号数为8位！");
				return;
			}
			//sex等于1为男生，sex等于2为女生
			String sex = "1";
			if(!JRadioButton1.isSelected()) {
				sex = "2";
			}
			
//			System.out.println(id + nameTxt.getText()+sex.trim()+ageTxt.getText().trim()+occupationTxt.getText().trim()+validDocumentation.getSelectedIndex()+idNumberTxt.getText().trim()+telNumberTxt.getText().trim()+depositTxt.getText().trim()+regdateTxt.getText().trim()+readerNumTxt.getText().trim());
			int i = ReaderInfoDao.updateReader(nameTxt.getText().trim(),sex.trim(),ageTxt.getText().trim(),occupationTxt.getText().trim(),validDocumentation.getSelectedIndex(),idNumberTxt.getText().trim(),telNumberTxt.getText().trim(),BigDecimal.valueOf(Double.valueOf(depositTxt.getText().trim())),Date.valueOf(regdateTxt.getText().trim()),readerNumTxt.getText().trim());
			
			if(i == 1) {
				JOptionPane.showMessageDialog(null, "添加成功！");
				Object[][] results=getFileStates(ReaderInfoDao.selectReader());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
		}
	}
	//删除按钮监听事件
	private final class DelButtonListener implements ActionListener{
		private final DefaultTableModel model;

		private DelButtonListener(DefaultTableModel model) {
			this.model = model;
		}
		@Override
		public void actionPerformed(final ActionEvent ae) {
			int i = ReaderInfoDao.deleteReader(readerNumTxt.getText().trim());
			if(i == 1) {
				JOptionPane.showMessageDialog(null, "删除成功！");
				Object[][] results=getFileStates(ReaderInfoDao.selectReader());
				model.setDataVector(results,columnNames);
				table.setModel(model);
			}
		}
		
	}
	
	//日期格式监听
	public class DateListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(regdateTxt.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "时间格式请使用\"2017-09-22\"格式");
			}
		}
	}
	
	//数字监听事件
	private final class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	
	//电话号码位数监听
	@SuppressWarnings("unused")
	private class TelListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789-"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	
	//押金格式监听
	private class KeepmoneyListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;//只允许输入数字与退格键
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
			if(depositTxt.getText().length()>2||depositTxt.getText().length()<0){
				e.consume();
			}
		}
	}
}
