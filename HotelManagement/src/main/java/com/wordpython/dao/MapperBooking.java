package com.wordpython.dao;

import com.wordpython.entity.Booking;

import java.util.List;

/**
 * @Author wordpython
 * @Date 2019/10/17
 **/

public interface MapperBooking {
    //添加
    int insertBooking(com.wordpython.entity.Booking booking);
    //更新
    int updateBooking(com.wordpython.entity.Booking booking);
    //删除
    int deleteBooking(com.wordpython.entity.Booking booking);
    //查询
    com.wordpython.entity.Booking selectBooking(com.wordpython.entity.Booking booking);
    //查询部分用户
    List<com.wordpython.entity.Booking> selectPartBooking(com.wordpython.entity.Booking booking);
    //查询用户总数
    int selectUserCount();
}
