package com.zp.gossiptripe.main.personal.regist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.zp.gossiptripe.common.BaseSessionBean;

public class UserBaseInfoBean extends BaseSessionBean implements Parcelable {
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

	@Override
	public String toString() {
		return "UserBaseInfoBean{" +
		"name='" + name + '\'' +
		", birthday='" + birthday + '\'' +
		", address='" + address + '\'' +
		", email='" + email + '\'' +
		", detialaddress='" + detialaddress + '\'' +
		", mechanism='" + mechanism + '\'' +
		'}';
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
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeString(this.birthday);
		dest.writeString(this.address);
		dest.writeString(this.email);
		dest.writeString(this.detialaddress);
		dest.writeString(this.mechanism);
	}

	public UserBaseInfoBean() {
	}

	protected UserBaseInfoBean(Parcel in) {
		this.name = in.readString();
		this.birthday = in.readString();
		this.address = in.readString();
		this.email = in.readString();
		this.detialaddress = in.readString();
		this.mechanism = in.readString();
	}

	public static final Parcelable.Creator<UserBaseInfoBean> CREATOR = new Parcelable.Creator<UserBaseInfoBean>() {
		@Override
		public UserBaseInfoBean createFromParcel(Parcel source) {
			return new UserBaseInfoBean(source);
		}

		@Override
		public UserBaseInfoBean[] newArray(int size) {
			return new UserBaseInfoBean[size];
		}
	};
}
