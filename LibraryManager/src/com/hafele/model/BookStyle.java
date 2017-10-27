package com.hafele.model;
/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月30日 上午12:38:48
* 图书类型封装类
*/
public class BookStyle {
	private String bookStyleNumber;//图书类型编号
	private String bookStyle;//图书类型名称
	private String borrowDays;//可借阅天数
	private String amerce;//罚款
	public String getBookStyleNumber() {
		return bookStyleNumber;
	}
	public void setBookStyleNumber(String bookStyleNumber) {
		this.bookStyleNumber = bookStyleNumber;
	}
	public String getBookStyle() {
		return bookStyle;
	}
	public void setBookStyle(String bookStyle) {
		this.bookStyle = bookStyle;
	}
	public String getBorrowDays() {
		return borrowDays;
	}
	public void setBorrowDays(String borrowDays) {
		this.borrowDays = borrowDays;
	}
	public String getAmerce() {
		return amerce;
	}
	public void setAmerce(String amerce) {
		this.amerce = amerce;
	}
}
