package com.wordpython.service.impl;

import com.wordpython.dao.MapperDiscuss;
import com.wordpython.entity.Discuss;
import com.wordpython.service.AllDiscussService;
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
public class AllDiscussServiceImpl implements AllDiscussService {
    @Autowired
    private MapperDiscuss mapperDiscuss;
    @Override
    public int insertDiscuss(Discuss discuss) {
        return mapperDiscuss.insertDiscuss(discuss);
    }

    @Override
    public List<Discuss> selectDiscuss(Discuss discuss) {
        return mapperDiscuss.selectDiscuss(discuss);
    }
}
