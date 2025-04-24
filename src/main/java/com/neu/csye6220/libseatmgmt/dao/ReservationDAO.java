package com.neu.csye6220.libseatmgmt.dao;

import com.neu.csye6220.libseatmgmt.dao.interfaces.IReservationDAO;
import com.neu.csye6220.libseatmgmt.exception.DataAccessException;
import com.neu.csye6220.libseatmgmt.model.Reservation;
import com.neu.csye6220.libseatmgmt.model.Seat;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationDAO extends BaseDAO implements IReservationDAO {

    @Override
    public void createReservation(Reservation reservation){
            // Implementation to save reservation
            try {
                 begin();
                 getSession().save(reservation);
                 commit();
            } catch (HibernateException e){
                rollback();
                throw new DataAccessException("Error saving Reservation", e);
            } finally {
                close(); // <<< VERY IMPORTANT
            }
    }

    @Override
    public void updateReservation(Reservation reservation){
            // Implementation to update reservation
            try {
                 begin();
                 getSession().merge(reservation);
                 commit();
            } catch (HibernateException e){
                rollback();
                throw new DataAccessException("Error updating Reservation", e);
            } finally {
                close(); // <<< VERY IMPORTANT
            }

    }

    @Override
    public Reservation getReservationById(Long id){
            // Implementation to get reservation by ID
            try {
                  return getSession().get(Reservation.class, id);
            } catch (HibernateException e) {
                throw new DataAccessException("Error Fetching Reservation by ID "+ id, e);
            } finally {
                close(); // <<< VERY IMPORTANT
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
            } finally {
                close(); // <<< VERY IMPORTANT
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
            } finally {
                close(); // <<< VERY IMPORTANT
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
        } finally {
            close(); // <<< VERY IMPORTANT
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
        } finally {
            close(); // <<< VERY IMPORTANT
        }

    }

    @Override
    public boolean isSeatAvailable(Long seatId, Timestamp startTime, Timestamp endTime) {
        try {
            // Check if the seat is available for the given time range
            begin();
            String hql = "FROM Reservation r WHERE r.seat.id = :seatId " +
                    "AND r.startDateTime < :endTime AND r.endDateTime > :startTime";

            List<?> overlapping = getSession().createQuery(hql)
                    .setParameter("seatId", seatId)
                    .setParameter("startTime", startTime)
                    .setParameter("endTime", endTime)
                    .list();
            commit();

            return overlapping.isEmpty();  // true means available
        } catch (Exception e) {
            rollback();
            throw new DataAccessException("Error checking seat availability", e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public List<Reservation> getReservationsBySeatId(Long seatId) {
        // Implementation to get reservations by Seat ID
        try {
            return getSession()
                    .createQuery("FROM Reservation WHERE seat.id = :seatId", Reservation.class)
                    .setParameter("seatId", seatId)
                    .list();
        } catch (HibernateException e) {
            System.out.println("Error found " + e.getMessage());
            throw new DataAccessException("Error fetching Reservations by Seat ID", e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public void deleteReservationBySeatId(Long seatId) {
        // Implementation to delete reservation by seat ID
        try {
            begin();
            Reservation reservation = getSession().find(Reservation.class, seatId);
            if (reservation != null) {
                getSession().remove(reservation);
            }
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new DataAccessException("Unable to delete Reservation with Seat ID: " + seatId, e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public void deleteReservationByUserId(Long userId) {
        // Implementation to delete reservation by user ID
        try {
            begin();
            Reservation reservation = getSession().find(Reservation.class, userId);
            if (reservation != null) {
                getSession().remove(reservation);
            }
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new DataAccessException("Unable to delete Reservation with User ID: " + userId, e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public List<Seat> getAvailableSeats(String seatType, Timestamp startTime, Timestamp endTime){
        // Implementation to get available seats
        try {
            begin();
            return getSession()
                    .createQuery("""
                         FROM Seat s
                            WHERE s.seatType = :seatType 
                         AND NOT EXISTS (
                                SELECT 1 FROM Reservation r 
                                    WHERE r.seat.id = s.id 
                                        AND r.startDateTime < :endTime 
                                        AND r.endDateTime > :startTime
                                        )
                        """, Seat.class)
                    .setParameter("seatType", seatType)
                    .setParameter("startTime", startTime)
                    .setParameter("endTime", endTime)
                    .list();
        } catch (HibernateException e) {
            throw new DataAccessException("Error fetching available Seats", e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public List<Reservation> getReservationsBetween(Timestamp start, Timestamp end){
        try {
            begin();
            return getSession().createQuery(
                            "FROM Reservation r WHERE r.startDateTime >= :start AND r.startDateTime < :end", Reservation.class)
                    .setParameter("start", start)
                    .setParameter("end", end)
                    .list();
        } catch (HibernateException e) {
            throw new DataAccessException("Failed to fetch reservations between dates", e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public List<Reservation> filterReservations(String seatType, Integer floor, Timestamp start, Timestamp end){
        try {
            begin();
            StringBuilder hql = new StringBuilder("FROM Reservation r WHERE r.startDateTime >= :start AND r.endDateTime <= :end");

            if (seatType != null && !seatType.isEmpty()) {
                hql.append(" AND r.seat.seatType = :seatType");
            }
            if (floor != null) {
                hql.append(" AND r.seat.floor = :floor");
            }

            var query = getSession().createQuery(hql.toString(), Reservation.class)
                    .setParameter("start", start)
                    .setParameter("end", end);

            if (seatType != null && !seatType.isEmpty()) {
                query.setParameter("seatType", seatType);
            }
            if (floor != null) {
                query.setParameter("floor", floor);
            }

            return query.list();

        } catch (HibernateException e) {
            throw new DataAccessException("Failed to filter reservations", e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }

    @Override
    public boolean isSeatAvailableForUpdate(Long seatId, Timestamp startTime, Timestamp endTime, Long excludeReservationId){
        try {
            begin();
            String hql = "FROM Reservation r WHERE r.seat.id = :seatId " +
                    "AND r.startDateTime < :endTime AND r.endDateTime > :startTime " +
                    "AND r.id <> :excludeId";

            List<Reservation> overlapping = getSession().createQuery(hql, Reservation.class)
                    .setParameter("seatId", seatId)
                    .setParameter("startTime", startTime)
                    .setParameter("endTime", endTime)
                    .setParameter("excludeId", excludeReservationId)
                    .list();
            commit();
            return overlapping.isEmpty();  // True = available
        } catch (Exception e) {
            rollback();
            throw new DataAccessException("Error checking seat availability for update", e);
        } finally {
            close(); // <<< VERY IMPORTANT
        }
    }



}
