package com.hafele.model;
/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年10月9日 下午3:50:43
* 图书借阅模型
*/
public class Borrow {
	private String bookId;
	private String readerId;
	private String borrowDate;
	private String backDate;
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getReaderId() {
		return readerId;
	}
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}
	public String getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	public String getBackDate() {
		return backDate;
	}
	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}
}
