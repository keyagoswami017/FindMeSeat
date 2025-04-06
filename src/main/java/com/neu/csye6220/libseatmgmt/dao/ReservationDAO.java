package com.neu.csye6220.libseatmgmt.dao;

import com.neu.csye6220.libseatmgmt.dao.interfaces.IReservationDAO;
import com.neu.csye6220.libseatmgmt.exception.DataAccessException;
import com.neu.csye6220.libseatmgmt.model.Reservation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDAO extends BaseDAO implements IReservationDAO {

    @Override
    public void saveReservation(Reservation reservation){
            // Implementation to save reservation
            try{
                begin();
                getSession().merge(reservation);
                commit();
            }catch (HibernateException e){
                rollback();
                throw new DataAccessException("Error saving Reservation", e);
            }
    }

    @Override
    public void updateReservation(Reservation reservation){
            // Implementation to update reservation
            try{
                begin();
                getSession().merge(reservation);
                commit();
            }catch (HibernateException e){
                rollback();
                throw new DataAccessException("Error updating Reservation", e);
            }

    }

    @Override
    public Reservation getReservationById(Long id){
            // Implementation to get reservation by ID
            try{
                return getSession().get(Reservation.class, id);
            } catch (HibernateException e) {
                throw new DataAccessException("Error Fetching Reservation by ID "+ id, e);
            }
    }

    @Override
    public List<Reservation> getReservationsByUserId(Long userId){
            // Implementation to get reservations by user ID
            try {
                return getSession()
                        .createQuery("FROM Reservation WHERE user.id = :userId", Reservation.class)
                        .setParameter("userId", userId)
                        .list();
            } catch (HibernateException e) {
                throw new DataAccessException("Error fetching Reservations by User ID", e);
            }
    }

    @Override
    public List<Reservation> getAllReservations(){
            // Implementation to get all reservations
            try {
                begin();
                CriteriaQuery<Reservation> criteriaQuery = getSession()
                        .getCriteriaBuilder()
                        .createQuery(Reservation.class);
                criteriaQuery.from(Reservation.class);
                List<Reservation> reservations = getSession()
                        .createQuery(criteriaQuery)
                        .getResultList();
                commit();
                return reservations;

            } catch (HibernateException e) {
                throw new DataAccessException("Error fetching all Reservations", e);
            }
    }

    @Override
    public void deleteReservation(Long id){
            // Implementation to delete reservation by ID
        try{
            begin();
            Reservation reservation = getSession().find(Reservation.class, id);
            if (reservation != null) {
                getSession().remove(reservation);
            }
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new DataAccessException("Unable to delete Reservation with ID: " + id, e);
        }
    }

    @Override
    public void deleteAllReservations(){
            // Implementation to delete all reservations
        try {
            begin();
            Session session = getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<Reservation> criteriaDelete = builder.createCriteriaDelete(Reservation.class);
            criteriaDelete.from(Reservation.class);
            session.createMutationQuery(criteriaDelete).executeUpdate();
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new DataAccessException("Unable to delete all Reservations", e);
        }

    }
}
