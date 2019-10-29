package com.wordpython.service.impl;

import com.wordpython.dao.MapperHotel;
import com.wordpython.entity.Hotel;
import com.wordpython.service.AllHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author wordpython
 * @Date 2019/10/27
 **/
@Service
@Transactional
public class AllHotelServiceImpl implements AllHotelService {

    @Autowired
    private MapperHotel mapperHotel;

    @Override
    public int insertHotel(Hotel hotel) {
        return mapperHotel.insertHotel(hotel);
    }

    @Override
    public int deleteHotel(Hotel hotel) {
        return mapperHotel.deleteHotel(hotel);
    }

    @Override
    public int updateHotel(Hotel hotel) {
        return mapperHotel.updateHotel(hotel);
    }

    @Override
    public Hotel selectHotel(Hotel hotel) {
        return mapperHotel.selectHotel(hotel);
    }

    @Override
    public Hotel selectHotelByAdmin_id(String admin_id) {
        return mapperHotel.selectHotelByAdmin_id(admin_id);
    }
}
