package com.wordpython.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author wordpython
 * @Date 2019/10/16
 **/
@Data
public class Room {
    private String type_id;//主键id，自增
    private String room_number;//房间编号
    private String hotel_id;//酒店id
    private String hotel_name;//酒店名
    private String type;//房间类型
    private String area;//房间面积
    private int floor;//所属楼层
    private int bed;//床位数
    private String wifi;
    private String workbench;//工作台
    private String livingroom;//客厅
    private String kitchen;//厨房
    private String win;//窗口
    private String detailed;//特别提示
    private String img;//图片文件夹名,图片名字(用于主页展示)
    private String img_url;//图片路径
    private int img_number;//图片数量
    private Timestamp create_time;//创建时间
    private Timestamp update_time;//更新时间
    //分页
    private int start;
    private int rows;


    private String room_id;//房间id,自增
    private String status;//房间状态:1-可预订，0-已被预订，-1：已入住，-2：不可用
    private double price;//房间价格
    private double discount;//房间折扣
    private String remark;//备注

    public Room(){}
    public Room(String room_number, String hotel_id, String status) {
        this.room_number = room_number;
        this.hotel_id = hotel_id;
        this.status = status;
    }
}
