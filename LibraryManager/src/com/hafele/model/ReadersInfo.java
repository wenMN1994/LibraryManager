package com.hafele.model;

import java.sql.Date;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月23日 下午12:31:31
* 读者信息模型
*/
public class ReadersInfo {
	private String readerId;
	private String readerName;
	private String readerSex;
	private String readerAge;
	private String readerOccupation;
	private int readerIdType;
	private String readerIdNumber;
	private String readerTelNumber;
	private Double guaranteeDeposit;
	private Date regdate;
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
	public String getReaderSex() {
		return readerSex;
	}
	public void setReaderSex(String readerSex) {
		this.readerSex = readerSex;
	}
	public String getReaderAge() {
		return readerAge;
	}
	public void setReaderAge(String readerAge) {
		this.readerAge = readerAge;
	}
	public String getReaderOccupation() {
		return readerOccupation;
	}
	public void setReaderOccupation(String readerOccupation) {
		this.readerOccupation = readerOccupation;
	}
	public int getReaderIdType() {
		return readerIdType;
	}
	public void setReaderIdType(int readerIdType) {
		this.readerIdType = readerIdType;
	}
	public String getReaderIdNumber() {
		return readerIdNumber;
	}
	public void setReaderIdNumber(String readerIdNumber) {
		this.readerIdNumber = readerIdNumber;
	}
	public String getReaderTelNumber() {
		return readerTelNumber;
	}
	public void setReaderTelNumber(String readerTelNumber) {
		this.readerTelNumber = readerTelNumber;
	}
	public Double getGuaranteeDeposit() {
		return guaranteeDeposit;
	}
	public void setGuaranteeDeposit(Double guaranteeDeposit) {
		this.guaranteeDeposit = guaranteeDeposit;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}
