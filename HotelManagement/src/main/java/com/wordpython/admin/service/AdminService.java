package com.wordpython.admin.service;

import com.wordpython.admin.entity.Admin;

public interface AdminService {
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
