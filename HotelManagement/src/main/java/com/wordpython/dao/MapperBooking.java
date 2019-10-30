package com.wordpython.dao;

import com.wordpython.entity.Booking;

import java.util.List;

/**
 * @Author wordpython
 * @Date 2019/10/17
 **/

public interface MapperBooking {
    //添加
    int insertBooking(Booking booking);
    //更新
    int updateBooking(Booking booking);
    //删除
    int deleteBooking(Booking booking);
    //查询
    List<Booking> selectBooking(Booking booking);
    //查询部分用户
    List<Booking> selectPartBooking(Booking booking);
}
