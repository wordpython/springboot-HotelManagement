package com.wordpython.entity;

import lombok.Data;

/**
 * @Author wordpython
 * @Date 2019/10/29
 **/
@Data
public class Discuss {
    private String discuss_id;
    private String room_number;
    private String hotel_id;
    private String content;
    private String username;
    private String photo;
    private String create_time;
}
