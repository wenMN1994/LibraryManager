package com.hafele.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.hafele.model.BookStyle;
import com.hafele.util.DbHelper;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月30日 上午12:39:05
* 类说明
*/
public class BookStyleDao {

	//插入图书类型信息
	public static int insertBookStyle(String bookStyleNumber, String bookStyle, String borrowDays, String amerce) {
		int i = 0;
		String sql = "insert into book_style(bookstyleno,bookstyle,borrowdays,amerce) values ('"+bookStyleNumber+"','"+bookStyle+"','"+borrowDays+"','"+amerce+"')";
		System.out.println(sql);
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			i = conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);;
		return i;
	}

	//获取图书类型所有数据
	public static List<BookStyle> selectBookStyle() {
		List<BookStyle> list = new ArrayList<BookStyle>();
		String sql = "select * from book_style";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while(re.next()) {
				BookStyle bookStyle = new BookStyle();
				bookStyle.setBookStyleNumber(re.getString("bookstyleno"));
				bookStyle.setBookStyle(re.getString("bookstyle"));
				bookStyle.setBorrowDays(re.getString("borrowdays"));
				bookStyle.setAmerce(re.getString("amerce"));
				list.add(bookStyle);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}
	
	//根据图书类型获取图书类型所有数据
	public static List<BookStyle> selectBookStyle(String bookType) {
		List<BookStyle> list=new ArrayList<BookStyle>();
		String sql = "select * from book_style where bookstyle='"+bookType+"'";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while (re.next()) {
				BookStyle type=new BookStyle();
				type.setBorrowDays(re.getString("borrowdays"));;
				type.setAmerce(re.getString("amerce"));
				list.add(type);
			}
		} catch (Exception e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}

	//修改图书类型信息
	public static int updateBookStyle(String bookStyleNumber, String bookStyle, String borrowDays, String amerce) {
		int i = 0;
		String sql = "update book_style set amerce='"+amerce+"',bookstyle='"+bookStyle+"',borrowdays='"+borrowDays+"' where bookstyleno='"+bookStyleNumber+"'";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			i = conn.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return i;
	}
}
