package com.hafele.model;
/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年10月10日 下午3:59:55
* 图书归还模块
*/
public class BackBook {
	private String Id;
	private String bookId;
	private String bookName;
	private String readerId;
	private String readerName;
	private String bookFee;
	private String borrowDate;
	private String returnDate;
	private String typeId;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
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
	public String getReaderId() {
		return readerId;
	}
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public String getBookFee() {
		return bookFee;
	}
	public void setBookFee(String bookFee) {
		this.bookFee = bookFee;
	}
	public String getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
}
