package com.neu.csye6220.libseatmgmt.dao;


import com.neu.csye6220.libseatmgmt.dao.interfaces.IAdminDAO;
import com.neu.csye6220.libseatmgmt.exception.DataAccessException;
import com.neu.csye6220.libseatmgmt.model.Admin;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO extends BaseDAO implements IAdminDAO {

    @Override
    public void createAdmin(Admin admin){
        // Implementation to save admin to the database
        try{
            begin();
            getSession().persist(admin);
            commit();
        }catch(HibernateException e){
            rollback();
            throw new DataAccessException("Error saving Admin", e);
        }finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public Admin updateAdmin(Admin admin){
        // Implementation to update admin in the database
        try{
            begin();
            getSession().merge(admin);
            commit();
            return admin;
        }catch(HibernateException e){
            rollback();
            throw new DataAccessException("Error updating Admin", e);
        }finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public Admin getAdminById(Long id){
        // Implementation to get admin by ID from the database
        try{
            return getSession().get(Admin.class, id);
        } catch (HibernateException e) {
            throw new DataAccessException("Error Fetching Admin by ID "+ id, e);
        }finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public Admin getAdminByEmail(String email){
        // Implementation to get admin by email from the database
        try{
            Session session = getSession();
            return session.createQuery("FROM Admin WHERE email = :email", Admin.class)
                    .setParameter("email", email)
                    .getSingleResultOrNull();

        } catch (HibernateException e) {
            rollback();
            throw new DataAccessException("Error Fetching Admin by Email "+ email, e);
        }finally {
            close(); // <<< VERY IMPORTANT
        }
    }
}
