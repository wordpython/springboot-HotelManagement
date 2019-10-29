package com.wordpython.admin.service.impl;

import com.wordpython.admin.dao.AdminMapper;
import com.wordpython.admin.entity.Admin;
import com.wordpython.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author wordpython
 * @Date 2019/10/26
 **/

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public int insertAdmin(Admin admin) {
        return adminMapper.insertAdmin(admin);
    }

    @Override
    public int deleteAdmin(Admin admin) {
        return adminMapper.deleteAdmin(admin);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public Admin selectAdmin(Admin admin) {
        return adminMapper.selectAdmin(admin);
    }

    @Override
    public Admin selectAdminByUsername(Admin admin) {
        return adminMapper.selectAdminByUsername(admin);
    }
}
