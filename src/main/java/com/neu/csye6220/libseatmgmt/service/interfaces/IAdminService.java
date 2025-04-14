package com.neu.csye6220.libseatmgmt.service.interfaces;

import com.neu.csye6220.libseatmgmt.model.Admin;

public interface IAdminService {
    Admin getAdminById(Long id);
    Admin getAdminByEmail(String email);
    Admin updateAdmin(Admin admin);
    long authenticate(String email, String password);
}
