package com.neu.csye6220.libseatmgmt.dao;

import com.neu.csye6220.libseatmgmt.dao.interfaces.ISeatDAO;
import com.neu.csye6220.libseatmgmt.exception.DataAccessException;
import com.neu.csye6220.libseatmgmt.model.Seat;
import com.neu.csye6220.libseatmgmt.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeatDAO extends BaseDAO implements ISeatDAO {

    @Override
    public void createSeat(Seat seat){
        // Implementation to save seat to the database
        try {
                begin();
                //If seat available then throw seat already exists exception //
                getSession().persist(seat);
                commit();
        } catch (HibernateException e) {
            rollback();
            throw new DataAccessException(" Unable to create Seat for library, Try Again!! ",e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public void updateSeat(Seat seat){
        // Implementation to update seat in the database
        try {
                begin();
                getSession().merge(seat);
                commit();
        } catch (HibernateException e) {
            rollback();
            throw new DataAccessException(" Unable to update Seat, Try Again!! ",e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public Seat getSeatById(Long id){
        // Implementation to get Seat by ID from the database
        try {
              return getSession().get(Seat.class, id);
        } catch (HibernateException e) {
            throw new DataAccessException("Error Fetching Seat by ID "+ id, e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public List<Seat> getAllSeats(){
        // Implementation to get all seats from the database
        try {
            begin();
            CriteriaQuery<Seat> criteriaQuery = getSession()
                    .getCriteriaBuilder()
                    .createQuery(Seat.class);
            criteriaQuery.from(Seat.class);
            List<Seat> seats = getSession()
                    .createQuery(criteriaQuery)
                    .getResultList();
            commit();
            return seats;
        } catch (HibernateException e) {
            throw new DataAccessException("Error fetching all Seats", e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public void deleteSeat(Long id){
        // Implementation to delete seat from the database
        try {
                begin();
                Seat seat = getSession().find(Seat.class, id);
                if (seat != null) {
                    getSession().remove(seat);
                }
                commit();
        } catch (HibernateException e) {
            rollback();
            throw new DataAccessException("Unable to delete Seat with ID: " + id, e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public List<Seat> getSeatsByType(String seatType){
        // Implementation to get seats by type from the database
        try {
            begin();
            List<Seat> seats = getSession()
                    .createQuery("FROM Seat WHERE seatType = :seatType AND available = true", Seat.class)
                    .setParameter("seatType", seatType)
                    .getResultList();
            commit();
            return seats;
        } catch (HibernateException e) {
            rollback();
            throw new DataAccessException("Error fetching Seats by type: " + seatType, e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public  boolean seatExists(String seatType, String seatNumber, int floorNumber){
        try {
            begin();
            String hql = "FROM Seat s WHERE s.seatNumber = :seatNumber AND s.floorNumber = :floorNumber AND s.seatType = :seatType";
            boolean exists = !getSession()
                    .createQuery(hql, Seat.class)
                    .setParameter("seatNumber", seatNumber)
                    .setParameter("floorNumber", floorNumber)
                    .setParameter("seatType", seatType)
                    .list().isEmpty();
            commit();
            return exists;
        } catch (HibernateException e) {
            rollback();
            throw new DataAccessException("Error checking if seat exists", e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }

}
