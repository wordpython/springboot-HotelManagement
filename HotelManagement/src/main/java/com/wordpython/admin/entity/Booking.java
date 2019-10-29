package com.wordpython.admin.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author wordpython
 * @Date 2019/10/16
 **/
@Data
public class Booking {
    private Long booking_id;//预订id
    private String user_id;//客户编号
    private String room_id;//房间编号
    private Timestamp booking_time;//预定的日期
    private Double cost;//订单费用
    private int status;//订单状态:0-已下单，1-已付款，2-已消费，-1-已取消，-2-被删除
    private Timestamp create_time;//创建订单时间
    private Timestamp update_time;//更新时间
}
