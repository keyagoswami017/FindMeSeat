package com.neu.csye6220.libseatmgmt.dao;

import com.neu.csye6220.libseatmgmt.dao.interfaces.IUserDAO;
import com.neu.csye6220.libseatmgmt.model.User;
import jakarta.persistence.Column;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO implements IUserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveUser(User user) {
        // Implementation to save user to the database
    }
    public void updateUser(User user) {
        // Implementation to update user in the database
    }
    public void deleteUser(User user)  {
        // Implementation to delete user from the database
    }
    public User getUserById(Long id)   {
        // Implementation to get user by ID from the database
        return null;
    }
    public User getUserByEmail(String email)   {
        // Implementation to get user by email from the database
        return null;
    }
}
