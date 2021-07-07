package com.bbm.entity;

public class Reader {
	private String readerId;
	private String name;
	private String sex;
	private String phone;

	private String regDate;
	public String getReaderId() {
		return readerId;
	}
	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "Reader [readerId=" + readerId + ", name=" + name + ", sex=" + sex
				+ ", phone=" + phone + ", regDate=" + regDate + "]";
	}
	
}
