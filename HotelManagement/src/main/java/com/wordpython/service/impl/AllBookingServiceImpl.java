package com.wordpython.service.impl;

import com.wordpython.dao.MapperBooking;
import com.wordpython.entity.Booking;
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
    public int insertBooking(Booking booking) {
        return mapperBooking.insertBooking(booking);
    }

    @Override
    public int updateBooking(Booking booking) {
        return mapperBooking.updateBooking(booking);
    }

    @Override
    public int deleteBooking(Booking booking) {
        return mapperBooking.deleteBooking(booking);
    }

    @Override
    public List<Booking> selectBooking(Booking booking) {
        return mapperBooking.selectBooking(booking);
    }

    @Override
    public List<Booking> selectPartBooking(Booking booking) {
        return mapperBooking.selectPartBooking(booking);
    }
}
