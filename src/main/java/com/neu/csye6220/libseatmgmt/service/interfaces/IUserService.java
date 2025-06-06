package com.neu.csye6220.libseatmgmt.service.interfaces;

import com.neu.csye6220.libseatmgmt.model.User;

import java.util.List;

public interface IUserService {
    void registerUser(User user);
    User getUserById(Long id);
    boolean emailExists(String email);
    User updateUser(User user);
    long authenticate(String email, String password);
    List<User> getAllUsers();
}
