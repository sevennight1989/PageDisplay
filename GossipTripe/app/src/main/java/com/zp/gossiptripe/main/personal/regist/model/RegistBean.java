package com.zp.gossiptripe.main.personal.regist.model;

public class RegistBean{
	// 用户名
	private String username;
	// 密码
	private String password;
	// 姓名
	private String name;
	// 出生日期
	private String birthday = "";
	// 地址
	private String address = "";
	// 邮箱地址
	private String email;
	// 详细地址
	private String detialaddress = "";
	// 机构
	private String mechanism = "";
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDetialaddress() {
		return detialaddress;
	}
	public void setDetialaddress(String detialaddress) {
		this.detialaddress = detialaddress;
	}
	public String getMechanism() {
		return mechanism;
	}
	public void setMechanism(String mechanism) {
		this.mechanism = mechanism;
	}
	@Override
	public String toString() {
		return "RegistBean [address=" + address + ", birthday=" + birthday
				+ ", detialaddress=" + detialaddress + ", email=" + email
				+ ", mechanism=" + mechanism + ", name=" + name + ", password="
				+ password + ", username=" + username + "]";
	}
	
	

}
