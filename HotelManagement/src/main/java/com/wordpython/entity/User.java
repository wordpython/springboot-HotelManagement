package com.wordpython.entity;

import lombok.Data;

@Data
public class User {
	private String account;//账号
	private String username;
	private String password;
	private String rePassword;//确认密码
	private String phone;
	private Integer start;
	private Integer rows;
	private String mail;
	private String code;//邮箱验证码
	private String perms;//授权字符串
	@Override
	public String toString() {
		return "------User [account=" + account + ", username=" + username
				+ ", password=" + password + ", phone=" + phone+ "]";
	}
	
}
