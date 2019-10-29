package com.wordpython.admin.service.impl;

import com.wordpython.admin.dao.HotelMapper;
import com.wordpython.admin.entity.Hotel;
import com.wordpython.admin.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author wordpython
 * @Date 2019/10/27
 **/
@Service
@Transactional
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public int insertHotel(Hotel hotel) {
        return hotelMapper.insertHotel(hotel);
    }

    @Override
    public int deleteHotel(Hotel hotel) {
        return hotelMapper.deleteHotel(hotel);
    }

    @Override
    public int updateHotel(Hotel hotel) {
        return hotelMapper.updateHotel(hotel);
    }

    @Override
    public Hotel selectHotel(Hotel hotel) {
        return hotelMapper.selectHotel(hotel);
    }

    @Override
    public Hotel selectHotelByAdmin_id(String admin_id) {
        return hotelMapper.selectHotelByAdmin_id(admin_id);
    }
}
