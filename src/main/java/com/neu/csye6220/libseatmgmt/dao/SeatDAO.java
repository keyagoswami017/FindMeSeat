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
        try{
            begin();
            getSession().persist(seat);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new DataAccessException(" Unable to create Seat for library, Try Again!! ",e);
        }
    }

    @Override
    public void updateSeat(Seat seat){
        // Implementation to update seat in the database
        try{
            begin();
            getSession().merge(seat);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new DataAccessException(" Unable to update Seat, Try Again!! ",e);
        }
    }

    @Override
    public Seat getSeatById(Long id){
        // Implementation to get Seat by ID from the database
        try{
            return getSession().get(Seat.class, id);
        } catch (HibernateException e) {
            throw new DataAccessException("Error Fetching Seat by ID "+ id, e);
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
        }
    }

    @Override
    public void deleteSeat(Long id){
        // Implementation to delete seat from the database
        try{
            begin();
            Seat seat = getSession().find(Seat.class, id);
            if (seat != null) {
                getSession().remove(seat);
            }
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new DataAccessException("Unable to delete Seat with ID: " + id, e);
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
        }
    }

}
