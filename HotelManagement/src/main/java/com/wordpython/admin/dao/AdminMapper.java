package com.wordpython.admin.dao;

import com.wordpython.admin.entity.Admin;

/**
 * @Author wordpython
 * @Date 2019/10/26
 **/

public interface AdminMapper {
    //添加
    int insertAdmin(Admin admin);
    //删除
    int deleteAdmin(Admin admin);
    //更新
    int updateAdmin(Admin admin);
    //查询
    Admin selectAdmin(Admin admin);

    Admin selectAdminByUsername(Admin admin);
}
