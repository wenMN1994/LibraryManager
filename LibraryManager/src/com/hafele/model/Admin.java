package com.hafele.model;

import java.sql.Date;

/**
* @author Dragon Wen E-mail:18475536452@163.com
* @version 创建时间：2017年9月20日 下午1:54:49
* 管理员
*/
public class Admin {
	private String sys_number;
	private String sys_name;
	private String sys_password;
	private String sys_sex;
	private String sys_age;
	private Date sys_regdate;
	private String sys_telNumber;
	private String sys_idCard;
	public Admin() {
		super();
	}
	
	public Admin(String sys_name, String sys_password) {
		super();
		this.sys_name = sys_name;
		this.sys_password = sys_password;
	}

	public String getSys_number() {
		return sys_number;
	}
	public void setSys_number(String sys_number) {
		this.sys_number = sys_number;
	}
	public String getSys_name() {
		return sys_name;
	}
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}
	public String getSys_password() {
		return sys_password;
	}
	public void setSys_password(String sys_password) {
		this.sys_password = sys_password;
	}
	public String getSys_sex() {
		return sys_sex;
	}

	public void setSys_sex(String sys_sex) {
		this.sys_sex = sys_sex;
	}

	public String getSys_age() {
		return sys_age;
	}

	public void setSys_age(String sys_age) {
		this.sys_age = sys_age;
	}

	public Date getSys_regdate() {
		return sys_regdate;
	}

	public void setSys_regdate(Date sys_regdate) {
		this.sys_regdate = sys_regdate;
	}

	public String getSys_telNumber() {
		return sys_telNumber;
	}

	public void setSys_telNumber(String sys_telNumber) {
		this.sys_telNumber = sys_telNumber;
	}

	public String getSys_idCard() {
		return sys_idCard;
	}

	public void setSys_idCard(String sys_idCard) {
		this.sys_idCard = sys_idCard;
	}
}
