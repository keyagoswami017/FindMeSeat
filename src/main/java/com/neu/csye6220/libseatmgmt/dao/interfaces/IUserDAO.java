package com.neu.csye6220.libseatmgmt.dao.interfaces;

import com.neu.csye6220.libseatmgmt.model.User;

import java.util.List;

public interface IUserDAO {
    void createUser(User user);
    User updateUser(User user);
    void registerUser(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
    boolean emailExists(String email);
    List<User> getAllUsers();
}
