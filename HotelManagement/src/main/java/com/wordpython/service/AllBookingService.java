package com.wordpython.service;

import com.wordpython.entity.Booking;

import java.util.List;

public interface AllBookingService {
    //添加
    int insertBooking(Booking booking);
    //更新
    int updateBooking(Booking booking);
    //删除
    int deleteBooking(Booking booking);
    //查询
    Booking selectBooking(Booking booking);
    //查询部分用户
    List<Booking> selectPartBooking(Booking booking);
    //查询用户总数
    int selectUserCount();
}
