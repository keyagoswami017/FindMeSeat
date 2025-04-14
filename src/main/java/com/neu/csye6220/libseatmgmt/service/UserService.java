package com.neu.csye6220.libseatmgmt.service;

import com.neu.csye6220.libseatmgmt.dao.UserDAO;
import com.neu.csye6220.libseatmgmt.model.User;
import com.neu.csye6220.libseatmgmt.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void registerUser(User user) {
        // Check if the user already exists
        if (userDAO.getUserByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }
        userDAO.createUser(user);
    }

    @Override
    public User getUserById(Long id) {
        // Fetch the user by ID
        User us = userDAO.getUserById(id);
        return us;
    }

  @Override
    public boolean emailExists(String email) {
        // Fetch the user by email
        return userDAO.emailExists(email);
    }

    @Override
    public User updateUser(User user) {
        // Update the user details
        return userDAO.updateUser(user);
    }

    @Override
    public long authenticate(String email, String password){
        // Authenticate the user
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            if(passwordEncoder.matches(password, user.getPassword())){
                    return user.getId();
            }
        }
        return -1;
    }
}
