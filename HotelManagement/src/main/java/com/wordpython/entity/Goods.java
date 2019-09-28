package com.wordpython.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Goods implements Serializable{
	/**
	 * 序列化，因为redis保存对象的时候要求对象是序列化的；
	 */
	private static final long serialVersionUID = 1L;
	
	private String uuid;/* 主键UUID */
	private String mark;/* 物品分类 */
	private String academy;/* 学院 */
	private String major;/* 专业名 */
	private String photo;/* images/uuid.jpg */
	private String sno;/* 学号 */
	private String sname;/* 姓名 */
	private String contact;/* 拾主电话， */
	private String idcard;/* 身份证号 */
	private String bname;/* 银行卡名，简称;书等物品名 */
	private String account;/* 银行卡号，最长19位 */
	private String brand;/* 手机，U盘等的品牌 */
	private String size;/* U盘大小，如4G */
	private String other;/* 其他信息，100字符以内 */
	private String mobile;/* 丢失的手机的号码，可为空 */
	private Timestamp create_time;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	@Override
	public String toString() {
		return "Goods [uuid=" + uuid + ", mark=" + mark + ", academy=" + academy + ", major=" + major + ", photo="
				+ photo + ", sno=" + sno + ", sname=" + sname + ", contact=" + contact + ", idcard=" + idcard
				+ ", bname=" + bname + ", account=" + account + ", brand=" + brand + ", size=" + size + ", other="
				+ other + ", mobile=" + mobile + "]";
	}
	
}
