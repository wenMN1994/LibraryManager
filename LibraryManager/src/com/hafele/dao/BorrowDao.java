package com.hafele.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.hafele.model.Borrow;
import com.hafele.util.DbHelper;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年10月9日 下午3:54:06
* 图书借阅数据库操作类
*/
public class BorrowDao {

	//插入图书借阅数据
	public static int insertBorrow(String bookId, String readerId, Timestamp borrowDate, Timestamp backDate) {
		int i = 0;
		String sql = "insert into borrow_record(bookid,readerid,borrowDate,backDate)values('"+bookId+"','"+readerId+"','"+borrowDate+"','"+backDate+"')";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			i = conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
		}
		DbHelper.close(conn);
		return i;
	}

	//获取所有借阅数据
	public static List<Borrow> selectBorrow() {
		List<Borrow> list = new ArrayList<Borrow>();
		String sql =  "select * from borrow_record";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while(re.next()) {
				Borrow borrow = new Borrow();
				borrow.setBookId(re.getString("bookid"));
				borrow.setReaderId(re.getString("readerid"));
				borrow.setBorrowDate(re.getDate("borrowdate").toString());
				borrow.setBackDate(re.getDate("backDate").toString());
				list.add(borrow);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}
	
}
