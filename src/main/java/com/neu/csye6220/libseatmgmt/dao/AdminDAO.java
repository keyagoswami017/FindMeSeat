package com.neu.csye6220.libseatmgmt.dao;


import com.neu.csye6220.libseatmgmt.dao.interfaces.IAdminDAO;
import com.neu.csye6220.libseatmgmt.exception.DataAccessException;
import com.neu.csye6220.libseatmgmt.model.Admin;
import com.neu.csye6220.libseatmgmt.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO extends BaseDAO implements IAdminDAO {

    @Override
    public void saveAdmin(Admin admin){
        // Implementation to save admin to the database
        try{
            begin();
            getSession().persist(admin);
            commit();
        }catch(HibernateException e){
            rollback();
            throw new DataAccessException("Error saving Admin", e);
        }
    }

    @Override
    public void updateAdmin(Admin admin){
        // Implementation to update admin in the database
        try{
            begin();
            getSession().merge(admin);
            commit();
        }catch(HibernateException e){
            rollback();
            throw new DataAccessException("Error updating Admin", e);
        }
    }

    @Override
    public Admin getAdminById(Long id){
        // Implementation to get admin by ID from the database
        try{
            return getSession().get(Admin.class, id);
        } catch (HibernateException e) {
            throw new DataAccessException("Error Fetching Admin by ID "+ id, e);
        }
    }

    @Override
    public Admin getAdminByEmail(String email){
        // Implementation to get admin by email from the database
        try{
            Session session = getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Admin> query = builder.createQuery(Admin.class);
            Root<Admin> root = query.from(Admin.class);
            query.select(root).where(builder.equal(root.get("email"), email));
            return session.createQuery(query).uniqueResult();

        } catch (HibernateException e) {
            throw new DataAccessException("Error Fetching Admin by Email "+ email, e);
        }
    }
}
