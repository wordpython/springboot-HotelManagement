package com.wordpython.dao;

import com.wordpython.entity.Hotel;

/**
 * @Author wordpython
 * @Date 2019/10/26
 **/

public interface MapperHotel {
    //添加
    int insertHotel(Hotel hotel);
    //删除
    int deleteHotel(Hotel hotel);
    //更新
    int updateHotel(Hotel hotel);
    //查询
    Hotel selectHotel(Hotel hotel);
    //根据店主主键查询酒店
    Hotel selectHotelByAdmin_id(String admin_id);
}
