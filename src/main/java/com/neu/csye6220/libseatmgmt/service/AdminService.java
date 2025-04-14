package com.neu.csye6220.libseatmgmt.service;

import com.neu.csye6220.libseatmgmt.model.Admin;
import com.neu.csye6220.libseatmgmt.dao.AdminDAO;

import com.neu.csye6220.libseatmgmt.service.interfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {

    private final AdminDAO adminDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminDAO adminDAO, PasswordEncoder passwordEncoder) {
        this.adminDAO = adminDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin getAdminById(Long id) {
        // Fetch the admin by ID
        Admin admin = adminDAO.getAdminById(id);
        return admin;
    }

    @Override
    public Admin getAdminByEmail(String email) {
        // Fetch the admin by email
        return adminDAO.getAdminByEmail(email);
    }

    @Override
    public Admin updateAdmin(Admin user) {
        // Update the admin details
        return adminDAO.updateAdmin(user);
    }

    @Override
    public long authenticate(String email, String password) {
        // Authenticate the user
        Admin admin = adminDAO.getAdminByEmail(email);
        if (admin != null) {
            if(passwordEncoder.matches(password, admin.getPassword())){
                return admin.getId();
            }
        }
        return -1;
    }
}
