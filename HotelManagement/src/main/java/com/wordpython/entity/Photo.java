package com.wordpython.entity;

import lombok.Data;

import java.util.List;


/**
 * @Author wordpython
 * @Date 2019/10/21
 **/
@Data
public class Photo {
    private String photo_id;
    private String room_number;//房间编号
    private String hotel_id;//酒店id
    private String img_url;//图片文件夹名字
    private String photo_name;//图片名字
    private List<String> photo_names;//房间图片名字集

    public Photo(String photo_id,String hotel_id, String img_url, String photo_name) {
        this.photo_id = photo_id;
        this.hotel_id=hotel_id;
        this.img_url = img_url;
        this.photo_name = photo_name;
    }
}
