package com.hafele.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.hafele.dao.BookStyleDao;
import com.hafele.model.BookStyle;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年10月1日 下午6:39:31
* 图书类型修改与删除主窗体
*/
@SuppressWarnings("serial")
public class BookTypeModifyFrame extends JInternalFrame {
	private JTable table;
	private DefaultTableModel model;
	private String[] columnNames = {"图书类别编号", "图书类别名称", "可借天数","罚款"};
	private JTextField bookStyleNumberTxt;
	private JTextField bookStyleTxt;
	private JTextField borrowDaysTxt;
	private JTextField amerceTxt;
	
	private Object[][] getFileStates(List<?> list){
		Object[][] results = new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			BookStyle bookStyle = (BookStyle)list.get(i);
			results[i][0] = bookStyle.getBookStyleNumber();
			results[i][1] = bookStyle.getBookStyle();
			results[i][2] = bookStyle.getBorrowDays();
			results[i][3] = bookStyle.getAmerce();
		}
		return results;
	}

	/**
	 * Create the frame.
	 */
	public BookTypeModifyFrame() {
		setTitle("图书类型修改");
		setFrameIcon(new ImageIcon(BookTypeModifyFrame.class.getResource("/icon/type_16px.png")));
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 504, 470);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon(BookTypeModifyFrame.class.getResource("/icon/booktypemodify.jpg")));
		panel.add(logoLabel);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton modifyButton = new JButton("修改");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookStyleNumber = bookStyleNumberTxt.getText().trim();
				String bookStyle = bookStyleTxt.getText().trim();
				String borrowDays = borrowDaysTxt.getText().trim();
				String amerce = amerceTxt.getText().trim();
				int i = BookStyleDao.updateBookStyle(bookStyleNumber,bookStyle,borrowDays,amerce);
				if(i == 1) {
					JOptionPane.showMessageDialog(null, "修改成功。");
					Object[][] results = getFileStates(BookStyleDao.selectBookStyle());
					model.setDataVector(results,columnNames);
					table.setModel(model);
				}
			}
		});
		modifyButton.setIcon(new ImageIcon(BookTypeModifyFrame.class.getResource("/icon/change_16px.png")));
		panel_1.add(modifyButton);
		
		JButton cancelButton = new JButton("取消");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		cancelButton.setIcon(new ImageIcon(BookTypeModifyFrame.class.getResource("/icon/cancel_16px.png")));
		panel_1.add(cancelButton);
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(400, 130));
		
		JPanel panel_3 = new JPanel();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel bookStyleNumberLabel = new JLabel("图书类别编号：");
		
		JLabel bookStyleLabel = new JLabel("图书类别名称：");
		
		JLabel borrowDaysLabel = new JLabel("可借阅天数：");
		
		JLabel amerceLabel = new JLabel("罚款(天/元)：");
		
		bookStyleNumberTxt = new JTextField();
		bookStyleNumberTxt.setColumns(10);
		bookStyleNumberTxt.setEditable(false);
		
		bookStyleTxt = new JTextField();
		bookStyleTxt.setColumns(10);
		
		borrowDaysTxt = new JTextField();
		borrowDaysTxt.setColumns(10);
		
		amerceTxt = new JTextField();
		amerceTxt.setColumns(10);
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(61)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(amerceLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(amerceTxt, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(bookStyleLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(bookStyleTxt, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(bookStyleNumberLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(bookStyleNumberTxt, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(borrowDaysLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(borrowDaysTxt, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(75, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookStyleNumberTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookStyleNumberLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookStyleTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookStyleLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(borrowDaysTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(borrowDaysLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(amerceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(amerceLabel))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		model = new DefaultTableModel();
		Object[][] results = getFileStates(BookStyleDao.selectBookStyle());
		model.setDataVector(results,columnNames);
		
		table = new JTable();
		table.setModel(model);
		table.addMouseListener(new TableListener());
		scrollPane.setViewportView(table);
		panel_2.setLayout(gl_panel_2);
		
		//设置窗体可见
		this.setVisible(true);
	}
	//表格内容点击事件
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int selRow = table.getSelectedRow();
			bookStyleNumberTxt.setText(table.getValueAt(selRow, 0).toString().trim());
			bookStyleTxt.setText(table.getValueAt(selRow, 1).toString().trim());
			borrowDaysTxt.setText(table.getValueAt(selRow, 2).toString().trim());
			amerceTxt.setText(table.getValueAt(selRow, 3).toString().trim());
		}
	}
}
