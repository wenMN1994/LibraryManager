package com.hafele.model;

import java.sql.Date;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月30日 上午12:35:07
* 书本封装类
*/
public class BookInfo {
	private String bookId;
	private String bookName;
	private String bookStyleNumber;
	private String bookAuthor;
	private String bookPub;
	private Date bookPubDate;
	private Date bookInDate;
	private Double bookPrice;
	private String isBorrowed;
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookStyleNumber() {
		return bookStyleNumber;
	}
	public void setBookStyleNumber(String bookStyleNumber) {
		this.bookStyleNumber = bookStyleNumber;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookPub() {
		return bookPub;
	}
	public void setBookPub(String bookPub) {
		this.bookPub = bookPub;
	}
	public Date getBookPubDate() {
		return bookPubDate;
	}
	public void setBookPubDate(Date bookPubDate) {
		this.bookPubDate = bookPubDate;
	}
	public Date getBookInDate() {
		return bookInDate;
	}
	public void setBookInDate(Date bookInDate) {
		this.bookInDate = bookInDate;
	}
	public Double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getIsBorrowed() {
		return isBorrowed;
	}
	public void setIsBorrowed(String isBorrowed) {
		this.isBorrowed = isBorrowed;
	}
}
