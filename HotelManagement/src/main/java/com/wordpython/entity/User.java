package com.wordpython.entity;

import lombok.Data;

@Data
public class User {
    private String user_id;//编号(主键)
    private String username;//昵称
    private String password;//密码
    private String rePassword;//确认密码
    private String mail;//邮箱
    private String code;//邮箱验证码
    private String name;//实名
    private String phone;//电话
    private String idcard;//身份证号
    private String status;//登录状态
    private int bookingtimes;//预订次数
    private int level;//等级
    //分页
    private int start;//起始页数据
    private int row;//行数
    //其他登录方式
    private String account;

}
