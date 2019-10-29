package com.wordpython.admin.entity;

import lombok.Data;

/**
 * @Author wordpython
 * @Date 2019/10/26
 **/
@Data
public class Admin {
    private String admin_id;
    private String username;
    private String password;
    private String rePassword;
}
