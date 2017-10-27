package com.hafele.iframe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.hafele.dao.BookInfoDao;
import com.hafele.dao.BookStyleDao;
import com.hafele.model.BookInfo;
import com.hafele.model.BookStyle;
import com.hafele.util.Item;
import com.hafele.util.MapUtil;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年10月5日 下午2:42:19
* 类说明
*/
@SuppressWarnings("serial")
public class BookInfoModiAndDelIFrame extends JInternalFrame {
	private JTable table;
	private JTextField bookIDTxt;
	private JTextField bookNameTxt;
	private JTextField publisherTxt;
	private JTextField publisherDateTxt;
	private JTextField bookAuthorTxt;
	private JTextField orderDateTxt;
	private JTextField priceTxt;
	private JComboBox<Item> bookType;
	private DefaultComboBoxModel<Item> bookTypeModel;
	private String[] columnNames = { "图书编号", "图书类别", "图书名称", "作者", "出版社", "购买日期",
			"出版日期", "单价","是否借出" };
	private String ISBNs, typeids, bookNames,writers,publishers,orderDates,publisherDates,prices,isBorrowed;
	
	//取数据库中图书相关信息放入表格中
	private Object[][] getFileStates(List<?> list){
		
		Object[][]results=new Object[list.size()][columnNames.length];
		for(int i = 0; i < list.size(); i++) {
			BookInfo bookInfo = (BookInfo) list.get(i);
			String bookTypeName = String.valueOf(MapUtil.getMap().get(bookInfo.getBookStyleNumber()));
			String isborrowed;
			if(bookInfo.getIsBorrowed().equals("1")) {
				isborrowed = "是";
			}else {
				isborrowed = "否";
			}
			results[i][0] = bookInfo.getBookId();
			results[i][1] = bookTypeName;
			results[i][2] = bookInfo.getBookName();
			results[i][3] = bookInfo.getBookAuthor();
			results[i][4] = bookInfo.getBookPub();
			results[i][5] = bookInfo.getBookInDate();
			results[i][6] = bookInfo.getBookPubDate();
			results[i][7] = bookInfo.getBookPrice();
			results[i][8] = isborrowed;
		}
		return results;
	}

	/**
	 * Create the frame.
	 */
	public BookInfoModiAndDelIFrame() {
		setTitle("图书信息修改与删除");
		setFrameIcon(new ImageIcon(BookInfoModiAndDelIFrame.class.getResource("/icon/check_16px.png")));
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 603, 510);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(BookInfoModiAndDelIFrame.class.getResource("/icon/bookmodify.jpg")));
		panel.add(label);
		
		JPanel panel_button = new JPanel();
		getContentPane().add(panel_button, BorderLayout.SOUTH);
		
		JButton modifyButton = new JButton("修改");
		modifyButton.addActionListener(new addBookActionListener());
		modifyButton.setIcon(new ImageIcon(BookInfoModiAndDelIFrame.class.getResource("/icon/change_16px.png")));
		panel_button.add(modifyButton);
		
		JButton deleteButton = new JButton("删除");
		deleteButton.addActionListener(new DeleteBookActionListener());
		deleteButton.setIcon(new ImageIcon(BookInfoModiAndDelIFrame.class.getResource("/icon/delete_16px.png")));
		panel_button.add(deleteButton);
		
		JButton cancelButton = new JButton("取消");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		cancelButton.setIcon(new ImageIcon(BookInfoModiAndDelIFrame.class.getResource("/icon/cancel_16px.png")));
		panel_button.add(cancelButton);
		
		JPanel panel_Info = new JPanel();
		getContentPane().add(panel_Info, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel_1 = new JPanel();
		GroupLayout gl_panel_Info = new GroupLayout(panel_Info);
		gl_panel_Info.setHorizontalGroup(
			gl_panel_Info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Info.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_Info.setVerticalGroup(
			gl_panel_Info.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Info.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
		);
		
		JLabel bookIDLabel = new JLabel("图书编号：");
		
		JLabel bookNameLabel = new JLabel("图书名称：");
		
		JLabel publisherLabel = new JLabel("出版社：");
		
		JLabel publisherDateLabel = new JLabel("出版日期：");
		
		JLabel bookTypeLabel = new JLabel("图书类别：");
		
		JLabel authorLabel = new JLabel("图书作者：");
		
		JLabel orderDateLabel = new JLabel("购买日期：");
		
		JLabel priceLabel = new JLabel("图书单价：");
		
		bookIDTxt = new JTextField();
		bookIDTxt.setColumns(10);
		bookIDTxt.setEditable(false);
		
		bookNameTxt = new JTextField();
		bookNameTxt.setColumns(10);
		
		publisherTxt = new JTextField();
		publisherTxt.setColumns(10);
		
		publisherDateTxt = new JTextField();
		publisherDateTxt.setColumns(10);
		
		bookAuthorTxt = new JTextField();
		bookAuthorTxt.setColumns(10);
		
		orderDateTxt = new JTextField();
		orderDateTxt.setColumns(10);
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		
		bookType = new JComboBox<Item>();
		bookTypeModel = (DefaultComboBoxModel<Item>) bookType.getModel();
		
		//从数据库中获取图书类别
		List<BookStyle> list = BookStyleDao.selectBookStyle();
		for(int i = 0; i < list.size(); i++) {
			BookStyle bookStyle = (BookStyle) list.get(i);
			Item item = new Item();
			item.setId((String)bookStyle.getBookStyleNumber());
			item.setName((String)bookStyle.getBookStyle());
			bookTypeModel.addElement(item);
		}
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(bookNameLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(publisherLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(publisherDateLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookIDLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(bookIDTxt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addComponent(publisherTxt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
						.addComponent(publisherDateTxt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(priceLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(orderDateLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(orderDateTxt, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGap(9)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(bookTypeLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
								.addComponent(authorLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(bookType, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(bookAuthorTxt, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookIDTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookIDLabel)
						.addComponent(bookTypeLabel)
						.addComponent(bookType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(bookNameLabel)
						.addComponent(bookNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookAuthorTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(authorLabel))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(publisherLabel)
						.addComponent(publisherTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(orderDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(orderDateLabel))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(publisherDateLabel)
						.addComponent(publisherDateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(priceTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(priceLabel))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		Object[][] results = getFileStates(BookInfoDao.selectBookInfo());
		table = new JTable(results,columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//鼠标单击表格中的内容产生事件,将表格中的内容放入文本框中
		table.addMouseListener(new TableListener());
		
		scrollPane.setViewportView(table);
		panel_Info.setLayout(gl_panel_Info);

		//设置窗体可见
		this.setVisible(true);
	}
	
	//表格点击事件监听
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int selRow = table.getSelectedRow();
			ISBNs = table.getValueAt(selRow, 0).toString().trim();
			typeids = table.getValueAt(selRow, 1).toString().trim();
			bookNames = table.getValueAt(selRow, 2).toString().trim();
			writers = table.getValueAt(selRow, 3).toString().trim();
			publishers = table.getValueAt(selRow, 4).toString().trim();
			orderDates = table.getValueAt(selRow, 5).toString().trim();
			publisherDates = table.getValueAt(selRow, 6).toString().trim();
			prices = table.getValueAt(selRow, 7).toString().trim();
			isBorrowed = table.getValueAt(selRow, 8).toString().trim();
			
			bookIDTxt.setText(ISBNs);
			
			for(int i = 0;i < bookTypeModel.getSize(); i++){
				Item item = bookTypeModel.getElementAt(i);
				if(item.getName().equals(typeids)){
					bookTypeModel.setSelectedItem(item);
				}
			}
			
			bookNameTxt.setText(bookNames);
			bookAuthorTxt.setText(writers);
			publisherTxt.setText(publishers);
			orderDateTxt.setText(orderDates);
			publisherDateTxt.setText(publisherDates);
			priceTxt.setText(prices);
		}
	}
	
	//修改按钮监听事件
	class addBookActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String ISBN = bookIDTxt.getText().trim();
			
			//分类
			Object selectedItem = bookTypeModel.getSelectedItem();
			if (selectedItem == null) {
				return;
			}
			Item item = (Item)selectedItem;
			String bookTypes=item.getId();
			String bookName = bookNameTxt.getText().trim();
			String bookAuthor = bookAuthorTxt.getText().trim();
			String publisher = publisherTxt.getText().trim();
			String orderDate = orderDateTxt.getText().trim();
			String publisherDate = publisherDateTxt.getText().trim();
			String price = priceTxt.getText().trim();
			if(bookName.length() == 0) {
				JOptionPane.showMessageDialog(null, "图书名称不能为空。");
				return;
			}
			if(bookAuthor.length() == 0) {
				JOptionPane.showMessageDialog(null, "图书作者不能为空。");
				return;
			}
			if(publisher.length() == 0) {
				JOptionPane.showMessageDialog(null, "出版社不能为空。");
				return;
			}
			if(orderDate.length() == 0) {
				JOptionPane.showMessageDialog(null, "出版社不能为空。");
				return;
			}
			if(publisherDate.length() == 0) {
				JOptionPane.showMessageDialog(null, "出版日期不能为空。");
				return;
			}
			if(price.length() == 0) {
				JOptionPane.showMessageDialog(null, "图书单价不能为空。");
				return;
			}
			
			int i = BookInfoDao.updateBookInfo(ISBN,bookTypes,bookName,bookAuthor,publisher,Date.valueOf(orderDate),Date.valueOf(publisherDate),Double.valueOf(price)); 
			if(i == 1){
				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results=getFileStates(BookInfoDao.selectBookInfo());
				DefaultTableModel model=new DefaultTableModel();
				table.setModel(model);
				model.setDataVector(results, columnNames);
				resertTextFile();
			}
		}
	}
	
	//删除按钮监听事件
	class DeleteBookActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(isBorrowed.equals("否")) {
				int i = BookInfoDao.deleteBookInfo(ISBNs);
				if(i == 1) {
					JOptionPane.showMessageDialog(null, "删除成功。");
					Object[][] results=getFileStates(BookInfoDao.selectBookInfo());
					DefaultTableModel model=new DefaultTableModel();
					table.setModel(model);
					model.setDataVector(results, columnNames);
					resertTextFile();
				}
			} else {
				JOptionPane.showMessageDialog(null, "该图书已借出暂未归还，不可删除。");
				return;
			}
		}
	}
	
	//清空文本框
	private void resertTextFile() {
		bookIDTxt.setText(null);
		bookNameTxt.setText(null);
		bookAuthorTxt.setText(null);
		publisherTxt.setText(null);
		publisherDateTxt.setText(null);
		orderDateTxt.setText(null);
		priceTxt.setText(null);
	}
}
