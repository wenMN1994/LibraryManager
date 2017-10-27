package com.hafele.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.hafele.dao.BackBookDao;
import com.hafele.dao.BookInfoDao;
import com.hafele.dao.BookStyleDao;
import com.hafele.model.Admin;
import com.hafele.model.BackBook;
import com.hafele.model.BookStyle;
import com.hafele.util.MapUtil;
import com.hafele.util.MyDocument;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年10月10日 上午9:30:52
* 图书归还窗体实现类
*/
@SuppressWarnings("serial")
public class BookBackIFrame extends JInternalFrame {

	private JTable table;
	private JTextField adminTxt;
	private JTextField currentlyDateTxt;
	private JTextField amerceMoneyTxt;
	private JTextField exceedDaysTxt;
	private JTextField realDaysTxt;
	private JTextField allotedDaysTxt;
	private JTextField borrowDateTxt;
	private JTextField readerIdTxt;
	private String[] columnNames = { "图书名称", "图书条形码","图书类别","读者姓名","读者条形码","借书时间","归还时间" };
	private DefaultTableModel model = new DefaultTableModel();
	private SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private Admin userName = BookLoginIFrame.getUser(); 
	private String bookId=null;
	private String readerId=null;
	private String bookName=null;
	private String readerName=null;
	private String bookFee=null;
	private String borrowDate=null;
	private String returnDate=null;
	private void Resert() {
		borrowDateTxt.setText(null);
		allotedDaysTxt.setText(null);
		realDaysTxt.setText(null);
		allotedDaysTxt.setText(null);
		realDaysTxt.setText(null);
		amerceMoneyTxt.setText(null);
	}
	/**
	 * Create the frame.
	 */
	public BookBackIFrame() {
		setTitle("图书归还管理");
		setFrameIcon(new ImageIcon(BookBackIFrame.class.getResource("/icon/return_book_16px.png")));
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 600, 480);

		final JPanel basicInfoPanel = new JPanel();
		basicInfoPanel.setBorder(new TitledBorder(null, "基本信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		basicInfoPanel.setPreferredSize(new Dimension(0, 200));
		getContentPane().add(basicInfoPanel, BorderLayout.NORTH);

		final JPanel readerIdPanel = new JPanel();
		final GridLayout gl_readerIdPanel = new GridLayout(0, 2);
		gl_readerIdPanel.setVgap(5);
		readerIdPanel.setLayout(gl_readerIdPanel);
		readerIdPanel.setPreferredSize(new Dimension(400, 20));
		basicInfoPanel.add(readerIdPanel);

		final JLabel readerIdLabel = new JLabel();
		readerIdLabel.setText("读者编号：");
		readerIdPanel.add(readerIdLabel);

		readerIdTxt = new JTextField();
		readerIdTxt.setDocument(new MyDocument(13));
		readerIdTxt.addKeyListener(new readerISBNListenerlostFocus(model));
		readerIdPanel.add(readerIdTxt);

		final JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new FlowLayout());
		tablePanel.setPreferredSize(new Dimension(450, 130));
		basicInfoPanel.add(tablePanel);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(450, 120));
		tablePanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
		table.addMouseListener(new TableListener());
		

		final JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);

		final JPanel fineMoneyPanel = new JPanel();
		final GridLayout gl_fineMoneyPanel = new GridLayout(0, 2);
		gl_fineMoneyPanel.setVgap(20);
		fineMoneyPanel.setLayout(gl_fineMoneyPanel);
		fineMoneyPanel.setBorder(new TitledBorder(null, "罚款信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		fineMoneyPanel.setPreferredSize(new Dimension(250, 230));
		panel_1.add(fineMoneyPanel);

		final JLabel borrowDateLabel = new JLabel();
		borrowDateLabel.setText("借书日期：");
		fineMoneyPanel.add(borrowDateLabel);

		borrowDateTxt = new JTextField();
		borrowDateTxt.setEditable(false);

		fineMoneyPanel.add(borrowDateTxt);

		final JLabel borrowdaysLabel = new JLabel();
		borrowdaysLabel.setText("规定天数：");
		fineMoneyPanel.add(borrowdaysLabel);

		allotedDaysTxt = new JTextField();
		allotedDaysTxt.setEditable(false);
		fineMoneyPanel.add(allotedDaysTxt);

		final JLabel realDaysLabel = new JLabel();
		realDaysLabel.setText("实际天数：");
		fineMoneyPanel.add(realDaysLabel);

		realDaysTxt = new JTextField();
		realDaysTxt.setEditable(false);
		fineMoneyPanel.add(realDaysTxt);

		final JLabel exceedDaysLabel = new JLabel();
		exceedDaysLabel.setText("超出天数：");
		fineMoneyPanel.add(exceedDaysLabel);

		exceedDaysTxt = new JTextField();
		exceedDaysTxt.setEditable(false);
		fineMoneyPanel.add(exceedDaysTxt);

		final JLabel fineMoneyLabel = new JLabel();
		fineMoneyLabel.setText("罚款金额：");
		fineMoneyPanel.add(fineMoneyLabel);

		amerceMoneyTxt = new JTextField();
		amerceMoneyTxt.setEditable(false);
		fineMoneyPanel.add(amerceMoneyTxt);

		final JPanel systemPanel = new JPanel();
		systemPanel.setBorder(new TitledBorder(null, "系统信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		systemPanel.setPreferredSize(new Dimension(280, 230));
		panel_1.add(systemPanel);

		final JPanel panel_7 = new JPanel();
		final GridLayout gridLayout_3 = new GridLayout(0, 2);
		gridLayout_3.setVgap(35);
		panel_7.setLayout(gridLayout_3);
		panel_7.setPreferredSize(new Dimension(260, 90));
		systemPanel.add(panel_7);

		final JLabel currentlyDateLabel = new JLabel();
		currentlyDateLabel.setText("当前时间：");
		panel_7.add(currentlyDateLabel);

		currentlyDateTxt = new JTextField();
		currentlyDateTxt.setEditable(false);
		currentlyDateTxt.setPreferredSize(new Dimension(0, 0));
		currentlyDateTxt.addActionListener(new TimeActionListener());
		currentlyDateTxt.setFocusable(false);
		panel_7.add(currentlyDateTxt);

		final JLabel adminLabel = new JLabel();
		adminLabel.setText("操作员：");
		panel_7.add(adminLabel);

		adminTxt  =new JTextField(userName.getSys_name());
		adminTxt.setEditable(false);
		panel_7.add(adminTxt);

		final JButton buttonback = new JButton();
		buttonback.setIcon(new ImageIcon(BookBackIFrame.class.getResource("/icon/return_book_16px.png")));
		buttonback.setText("归还");
		buttonback.addActionListener(new BookBackActionListener(model));
		systemPanel.add(buttonback);

		final JButton buttonExit= new JButton();
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		buttonExit.setIcon(new ImageIcon(BookBackIFrame.class.getResource("/icon/logoff_16px.png")));
		buttonExit.setText("退出");
		systemPanel.add(buttonExit);
		setVisible(true);
	}
	
	//图书归还按钮监听
	class BookBackActionListener implements ActionListener{
		private final DefaultTableModel model;

		BookBackActionListener(DefaultTableModel model) {
			this.model = model;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(readerId == null){
				JOptionPane.showMessageDialog(null, "请输入读者编号！");
				return;
			}
			System.out.println(readerId == null);

			if(table.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(null, "请选择所要归还的图书！");
				return;	
			}
			
			int i = BackBookDao.updateBookBack(readerId,readerName,bookId,bookName,bookFee,borrowDate,returnDate);
			int j = BookInfoDao.updateBookInfo(bookId, 0);
			if(i == 1 && j == 1) {
				int selectedRow = table.getSelectedRow();
				model.removeRow(selectedRow);
				Resert();
				JOptionPane.showMessageDialog(null, "还书操作完成！");
			}
		}
	}
	
	//点击table表数据监听事件
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			java.util.Date currentDate=new java.util.Date();
			String bookAmerce = "";
			String allotedDays = "";
			int totalDays;
			int overdueDays;
			int selRow = table.getSelectedRow();
			List<BookStyle> list = BookStyleDao.selectBookStyle(table.getValueAt(selRow, 2).toString().trim());
			for(int i = 0; i < list.size(); i++) {
				BookStyle bookStyle = (BookStyle) list.get(i);
				bookAmerce = bookStyle.getAmerce();
				allotedDays = bookStyle.getBorrowDays();
			}
			borrowDateTxt.setText(table.getValueAt(selRow, 5).toString().trim());
			allotedDaysTxt.setText(allotedDays + "");
			totalDays = differentDaysByMillisecond(currentDate,java.sql.Date.valueOf(table.getValueAt(selRow, 5).toString().trim()));
			overdueDays = totalDays - Integer.parseInt(allotedDays);
			realDaysTxt.setText(totalDays + "");
			if(overdueDays > 0) {
				exceedDaysTxt.setText(overdueDays + "");
				Double totalAmerce = Double.valueOf(bookAmerce) * overdueDays;
				amerceMoneyTxt.setText(totalAmerce + "");
			} else {
				exceedDaysTxt.setText("没有超过规定天数");
				amerceMoneyTxt.setText("0");
			}
			bookId = table.getValueAt(selRow, 1).toString().trim();
			bookName = table.getValueAt(selRow, 0).toString().trim();
			readerName = table.getValueAt(selRow, 3).toString().trim();
			bookFee = amerceMoneyTxt.getText();
			borrowDate = table.getValueAt(selRow, 5).toString();
			returnDate = currentlyDateTxt.getText().toString();
		}
	}

	//当前时间监听事件
	class TimeActionListener implements ActionListener{
		public TimeActionListener(){
			Timer t=new Timer(1000,this);
			t.start();
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			currentlyDateTxt.setText(myfmt.format(new java.util.Date()).toString());
		}
	}
	
	//读者编号文本框监听事件
	class readerISBNListenerlostFocus extends KeyAdapter {
		private final DefaultTableModel model;

		readerISBNListenerlostFocus(DefaultTableModel model) {
			this.model = model;
		}
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车。
				if(table.getRowCount() != 0) {
					model.setRowCount(0);
					Resert();
				}
				add();
			}
		}

		private void add() {
			readerId = readerIdTxt.getText().trim();
			List<BackBook> list = BackBookDao.selectBookBack(readerId);
			for(int i = 0; i < list.size(); i++) {
				BackBook backBook = (BackBook) list.get(i);
				String str[] = new String[7];
				str[0] = backBook.getBookName();
				str[1] = backBook.getBookId();
				str[2] = String.valueOf(MapUtil.getMap().get(backBook.getTypeId()));
				str[3] = backBook.getReaderName();
				str[4] = backBook.getReaderId();
				str[5] = backBook.getBorrowDate();
				str[6] = backBook.getReturnDate();
				model.addRow(str);
			}
		}
	}
	
	/**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date currentDate,Date borrowDate)
    {
        int days = (int) ((currentDate.getTime() - borrowDate.getTime()) / (1000*3600*24));
        return days;
    }
}
