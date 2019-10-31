package com.wordpython.service;

import com.wordpython.entity.Photo;
import com.wordpython.entity.Room;

import java.util.List;

public interface AllRoomService {
    //添加
    int insertRoom(Room room);

    //更新
    int updateRoom(Room room);
    int updateRoom_msg(Room room);

    //删除
    int deleteRoom(Room room);

    //查询
    Room selectRoom(Room room);

//    批量查询
    List<Room> selectRooms(Room room);
    //部分查询
    List<Room> selectPartRoom(Room room);

    //总数
    int selectRoomCount();

    List<Photo> selectPhotos();

    Photo selectPhoto(Room room);
    //添加图片id，图片名和文件名（该文件名保存入session中）
    int insertPhoto(Photo photo);
    //更新房间编号（根据session中的文件名插入）
    int updatePhoto(Photo photo);
}
