package com.wordpython.dao;

import com.wordpython.entity.Users;
import org.apache.ibatis.annotations.Mapper;


public interface UserMapper {
    public Users findByUsername(String username);
}
