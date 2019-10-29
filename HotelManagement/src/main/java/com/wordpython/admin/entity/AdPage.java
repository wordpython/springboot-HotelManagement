package com.wordpython.admin.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author wordpython
 * @Date 2019/10/13
 **/
@Data
public class AdPage {
    List data;
    String code,count,msg;

    public AdPage(List data, String code, String count, String msg) {
        this.data = data;
        this.code = code;
        this.count = count;
        this.msg = msg;
    }
}
