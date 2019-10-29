package com.wordpython.service.impl;

import com.wordpython.dao.MapperBooking;
import com.wordpython.service.AllBookingService;
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
public class AllBookingServiceImpl implements AllBookingService {
    @Autowired
    private MapperBooking mapperBooking;

    @Override
    public int insertBooking(com.wordpython.entity.Booking booking) {
        return 0;
    }

    @Override
    public int updateBooking(com.wordpython.entity.Booking booking) {
        return 0;
    }

    @Override
    public int deleteBooking(com.wordpython.entity.Booking booking) {
        return 0;
    }

    @Override
    public com.wordpython.entity.Booking selectBooking(com.wordpython.entity.Booking booking) {
        return null;
    }

    @Override
    public List<com.wordpython.entity.Booking> selectPartBooking(com.wordpython.entity.Booking booking) {
        return null;
    }

    @Override
    public int selectUserCount() {
        return 0;
    }
}
