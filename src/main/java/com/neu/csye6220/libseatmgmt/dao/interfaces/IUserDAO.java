package com.neu.csye6220.libseatmgmt.dao.interfaces;

import com.neu.csye6220.libseatmgmt.model.User;

public interface IUserDAO {
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
}
