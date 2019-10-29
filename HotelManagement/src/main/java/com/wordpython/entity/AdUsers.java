package com.wordpython.entity;

import lombok.Data;

/**
 * @Author wordpython
 * @Date 2019/10/13
 **/
@Data
public class AdUsers {
    String account,name,sexName,roleName,deptName,email,phone,createTime,status;

    public AdUsers(String account, String name, String sexName, String roleName, String deptName, String email, String phone, String createTime, String status) {
        this.account = account;
        this.name = name;
        this.sexName = sexName;
        this.roleName = roleName;
        this.deptName = deptName;
        this.email = email;
        this.phone = phone;
        this.createTime = createTime;
        this.status = status;
    }
}
