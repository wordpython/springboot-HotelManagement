package com.wordpython.service;

import com.wordpython.entity.Discuss;

import java.util.List;

public interface AllDiscussService {
    //插入
    int insertDiscuss(Discuss discuss);
    //查询
    List<Discuss> selectDiscuss(Discuss discuss);
}
