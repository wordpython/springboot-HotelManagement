package com.wordpython.admin.service.impl;

import com.wordpython.admin.dao.RoomMapper;
import com.wordpython.admin.entity.Photo;
import com.wordpython.admin.entity.Room;
import com.wordpython.admin.service.RoomService;
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
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;
    @Override
    public int insertRoom(Room room) {
        return roomMapper.insertRoom(room);
    }

    @Override
    public int updateRoom(Room room) {
        return roomMapper.updateRoom(room);
    }

    @Override
    public int deleteRoom(Room room) {
        return roomMapper.deleteRoom(room);
    }

    @Override
    public Room selectRoom(Room room) {
        return roomMapper.selectRoom(room);
    }

    @Override
    public List<Room> selectRooms(Room room) {
        return roomMapper.selectRooms(room);
    }

    @Override
    public List<Room> selectPartRoom(Room room) {
        return roomMapper.selectPartRoom(room);
    }

    @Override
    public int selectRoomCount() {
        return roomMapper.selectRoomCount();
    }

    @Override
    public List<Photo> selectPhotos() {
        return roomMapper.selectPhotos();
    }

    @Override
    public Photo selectPhoto(Room room) {
        return roomMapper.selectPhoto(room);
    }

    @Override
    public int insertPhoto(Photo photo) {
        return roomMapper.insertPhoto(photo);
    }

    @Override
    public int updatePhoto(Photo photo) {
        return roomMapper.updatePhoto(photo);
    }
}
