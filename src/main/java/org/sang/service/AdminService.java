package org.sang.service;

import org.sang.mapper.AdminMapper;
import org.sang.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sang on 2018/7/15.
 */
@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
    public int addAdmin(Admin admin) {
        return adminMapper.addAdmin(admin);
    }
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdminById(admin);
    }
    public int deleteAdminById(Integer id) {
        return adminMapper.deleteAdminById(id);
    }
    public Admin getAdminById(Integer id) {
        return adminMapper.getAdminById(id);
    }
    public List<Admin> getAllAdmins() {
        return adminMapper.getAllAdmins();
    }
    public Admin adminLogin(String name, String password){ return adminMapper.adminLogin(name, password);}

}
