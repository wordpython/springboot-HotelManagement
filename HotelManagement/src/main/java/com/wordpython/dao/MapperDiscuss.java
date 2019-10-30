package com.wordpython.dao;

import com.wordpython.entity.Discuss;

import java.util.List;

public interface MapperDiscuss {
    //插入
    int insertDiscuss(Discuss discuss);
    //查询
    List<Discuss> selectDiscuss(Discuss discuss);
}
