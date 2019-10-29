package com.wordpython.admin.service.impl;

import com.wordpython.admin.dao.BookingMapper;
import com.wordpython.admin.entity.Booking;
import com.wordpython.admin.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author wordpython
 * @Date 2019/10/18
 **/
@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingMapper bookingMapper;

    @Override
    public int insertBooking(Booking booking) {
        return 0;
    }

    @Override
    public int updateBooking(Booking booking) {
        return 0;
    }

    @Override
    public int deleteBooking(Booking booking) {
        return 0;
    }

    @Override
    public Booking selectBooking(Booking booking) {
        return null;
    }

    @Override
    public List<Booking> selectPartBooking(Booking booking) {
        return null;
    }

    @Override
    public int selectUserCount() {
        return 0;
    }
}
