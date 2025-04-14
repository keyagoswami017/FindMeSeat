package com.neu.csye6220.libseatmgmt.dao.interfaces;

import com.neu.csye6220.libseatmgmt.model.Admin;

public interface IAdminDAO {
    void createAdmin(Admin admin);
    Admin updateAdmin(Admin admin);
    Admin getAdminById(Long id);
    Admin getAdminByEmail(String email);
}
