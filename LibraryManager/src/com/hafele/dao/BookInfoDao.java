package com.hafele.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.hafele.model.BookInfo;
import com.hafele.util.DbHelper;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月30日 上午12:36:05
* BooksDao类
*/

public class BookInfoDao {

	//插入图书信息
	public static int insertBookInfo(String ISBN, String bookTypes, String bookName, String bookAuthor,
			String publisher, Date orderDate, Date publisherDate, Double price) {
		int i = 0;
		String sql = "insert into system_books(bookid,bookstylenumber,bookname,bookauthor,bookpub,bookpubdate,bookindate,bookprice) values('"+ISBN+"','"+bookTypes+"','"+bookName+"','"+bookAuthor+"','"+publisher+"','"+publisherDate+"','"+orderDate+"','"+price+"')";
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
		DbHelper.close(conn);
		return i;
	}

	//获取图书信息所有数据
	public static List<BookInfo> selectBookInfo() {
		List<BookInfo> list = new ArrayList<BookInfo>();
		String sql = "select * from system_books";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while(re.next()) {
				BookInfo bookInfo = new BookInfo();
				bookInfo.setBookId(re.getString("bookid"));
				bookInfo.setBookName(re.getString("bookname"));
				bookInfo.setBookStyleNumber(re.getString("bookstylenumber"));
				bookInfo.setBookAuthor(re.getString("bookauthor"));
				bookInfo.setBookPub(re.getString("bookpub"));
				bookInfo.setBookPubDate(re.getDate("bookpubdate"));
				bookInfo.setBookInDate(re.getDate("bookindate"));
				bookInfo.setBookPrice(re.getDouble("bookprice"));
				bookInfo.setIsBorrowed(re.getString("isborrowed"));
				list.add(bookInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}

	//根据图书编号获取图书信息所有数据
	public static List<BookInfo> selectBookInfo(String bookId) {
		List<BookInfo> list = new ArrayList<BookInfo>();
		String sql = "select * from system_books where bookid ='"+bookId+"'";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while(re.next()) {
				BookInfo bookInfo = new BookInfo();
				bookInfo.setBookId(re.getString("bookid"));
				bookInfo.setBookName(re.getString("bookname"));
				bookInfo.setBookStyleNumber(re.getString("bookstylenumber"));
				bookInfo.setBookAuthor(re.getString("bookauthor"));
				bookInfo.setBookPub(re.getString("bookpub"));
				bookInfo.setBookPubDate(re.getDate("bookpubdate"));
				bookInfo.setBookInDate(re.getDate("bookindate"));
				bookInfo.setBookPrice(re.getDouble("bookprice"));
				bookInfo.setIsBorrowed(re.getString("isborrowed"));
				list.add(bookInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}
	
	//根据图书编号获取图书信息
	public static List<BookInfo> selectBookName(String bookName) {
		List<BookInfo> list = new ArrayList<BookInfo>();
		String sql = "select * from system_books where bookname ='"+bookName+"'";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while(re.next()) {
				BookInfo bookInfo = new BookInfo();
				bookInfo.setBookId(re.getString("bookid"));
				bookInfo.setBookName(re.getString("bookname"));
				bookInfo.setBookStyleNumber(re.getString("bookstylenumber"));
				bookInfo.setBookAuthor(re.getString("bookauthor"));
				bookInfo.setBookPub(re.getString("bookpub"));
				bookInfo.setBookPubDate(re.getDate("bookpubdate"));
				bookInfo.setBookInDate(re.getDate("bookindate"));
				bookInfo.setBookPrice(re.getDouble("bookprice"));
				bookInfo.setIsBorrowed(re.getString("isborrowed"));
				list.add(bookInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}
	
	//根据图书编号获取图书信息
	public static List<BookInfo> selectBookAuthor(String bookAuthor) {
		List<BookInfo> list = new ArrayList<BookInfo>();
		String sql = "select * from system_books where bookauthor ='"+bookAuthor+"'";
		Connection conn = null;
		try {
			conn = DbHelper.getConnection();
			ResultSet re = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
			while(re.next()) {
				BookInfo bookInfo = new BookInfo();
				bookInfo.setBookId(re.getString("bookid"));
				bookInfo.setBookName(re.getString("bookname"));
				bookInfo.setBookStyleNumber(re.getString("bookstylenumber"));
				bookInfo.setBookAuthor(re.getString("bookauthor"));
				bookInfo.setBookPub(re.getString("bookpub"));
				bookInfo.setBookPubDate(re.getDate("bookpubdate"));
				bookInfo.setBookInDate(re.getDate("bookindate"));
				bookInfo.setBookPrice(re.getDouble("bookprice"));
				bookInfo.setIsBorrowed(re.getString("isborrowed"));
				list.add(bookInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbHelper.close(conn);
			JOptionPane.showMessageDialog(null, "异常：请检查数据是否有误！");
		}
		DbHelper.close(conn);
		return list;
	}
	
	//更新图书信息
	public static int updateBookInfo(String ISBN, String bookTypes, String bookName, String bookAuthor,
			String publisher, Date orderDate, Date publisherDate, Double price) {
		int i = 0;
		String sql = "update system_books set bookid='"+ISBN+"',bookstylenumber='"+bookTypes+"',bookname='"+bookName+"',bookauthor='"+bookAuthor+"',bookpub='"+publisher+"',bookpubdate='"+publisherDate+"',bookindate='"+orderDate+"',bookprice="+price+" where bookid='"+ISBN+"'";
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
		DbHelper.close(conn);
		return i;
	}

	//根据图书编号更新图书是否借出
	public static int updateBookInfo(String bookId, int isBorrowed) {
		int i = 0;
		String sql = "update system_books set isborrowed="+isBorrowed+" where bookid='"+bookId+"'";
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

	public static int deleteBookInfo(String ISBNs) {
		int i = 0;
		String sql = "delete from system_books where bookid='"+ISBNs+"'";
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
