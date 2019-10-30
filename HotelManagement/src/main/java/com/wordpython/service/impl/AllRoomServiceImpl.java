package com.wordpython.service.impl;

import com.wordpython.dao.MapperRoom;
import com.wordpython.entity.Photo;
import com.wordpython.entity.Room;
import com.wordpython.service.AllRoomService;
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
public class AllRoomServiceImpl implements AllRoomService {
    @Autowired
    private MapperRoom mapperRoom;
    @Override
    public int insertRoom(Room room) {
        return mapperRoom.insertRoom(room);
    }

    @Override
    public int updateRoom(Room room) {
        return mapperRoom.updateRoom(room);
    }
    @Override
    public int updateRoom_msg(Room room) {
        return mapperRoom.updateRoom(room);
    }

    @Override
    public int deleteRoom(Room room) {
        return mapperRoom.deleteRoom(room);
    }

    @Override
    public Room selectRoom(Room room) {
        return mapperRoom.selectRoom(room);
    }

    @Override
    public List<Room> selectRooms(Room room) {
        return mapperRoom.selectRooms(room);
    }

    @Override
    public List<Room> selectPartRoom(Room room) {
        return mapperRoom.selectPartRoom(room);
    }

    @Override
    public int selectRoomCount(Room room) {
        return mapperRoom.selectRoomCount(room);
    }

    @Override
    public List<Photo> selectPhotos() {
        return mapperRoom.selectPhotos();
    }

    @Override
    public Photo selectPhoto(Room room) {
        return mapperRoom.selectPhoto(room);
    }

    @Override
    public int insertPhoto(Photo photo) {
        return mapperRoom.insertPhoto(photo);
    }

    @Override
    public int updatePhoto(Photo photo) {
        return mapperRoom.updatePhoto(photo);
    }
}
