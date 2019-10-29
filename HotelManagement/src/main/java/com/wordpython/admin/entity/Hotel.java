package com.wordpython.admin.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author wordpython
 * @Date 2019/10/27
 **/
@Data
public class Hotel {
    private String hotel_id;
    private String admin_id;
    private String hotel_name;
    private String phone;
    private String telephone;
    private String email;
    private String address;
    private String website;
    private String detail;
    private Timestamp create_time;
    private Timestamp update_time;
}
