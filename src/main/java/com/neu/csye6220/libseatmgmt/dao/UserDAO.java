package com.neu.csye6220.libseatmgmt.dao;

import com.neu.csye6220.libseatmgmt.dao.interfaces.IUserDAO;
import com.neu.csye6220.libseatmgmt.exception.DataAccessException;
import com.neu.csye6220.libseatmgmt.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends BaseDAO implements IUserDAO {

    @Override
    public void saveUser(User user) {
        // Implementation to save user to the database
        try{
            begin();
            getSession().persist(user);
            commit();
        }catch(HibernateException e){
            rollback();
            throw new DataAccessException("Error saving User", e);
        }
    }
    @Override
    public void updateUser(User user) {
        // Implementation to update user in the database
        try{
            begin();
            getSession().merge(user);
            commit();
        }catch(HibernateException e){
            rollback();
            throw new DataAccessException("Error updating User", e);
        }
    }
    @Override
    public void registerUser(User user)  {
        // Implementation to delete user from the database
        saveUser(user);
    }
    @Override
    public User getUserById(Long id)   {
        // Implementation to get user by ID from the database
        try{
            return getSession().get(User.class, id);
        } catch (HibernateException e) {
            throw new DataAccessException("Error Fetching User by ID "+ id, e);
        }
    }
    @Override
    public User getUserByEmail(String email)   {
        // Implementation to get user by email from the database
       try{
           Session session = getSession();
           CriteriaBuilder builder = session.getCriteriaBuilder();
           CriteriaQuery<User> query = builder.createQuery(User.class);
           Root<User> root = query.from(User.class);
           query.select(root).where(builder.equal(root.get("email"), email));
              return session.createQuery(query).uniqueResult();

       } catch (HibernateException e) {
           throw new DataAccessException("Error Fetching User by Email "+ email, e);
       }
    }
}
