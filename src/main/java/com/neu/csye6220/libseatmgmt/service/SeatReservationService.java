package com.neu.csye6220.libseatmgmt.service;

import com.neu.csye6220.libseatmgmt.dao.ReservationDAO;
import com.neu.csye6220.libseatmgmt.dao.UserDAO;
import com.neu.csye6220.libseatmgmt.dao.SeatDAO;
import com.neu.csye6220.libseatmgmt.exception.DataAccessException;
import com.neu.csye6220.libseatmgmt.model.Reservation;
import com.neu.csye6220.libseatmgmt.model.Seat;
import com.neu.csye6220.libseatmgmt.model.User;
import com.neu.csye6220.libseatmgmt.service.interfaces.ISeatReservationService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
public class SeatReservationService implements ISeatReservationService {

    private final SeatDAO seatDAO;
    private final UserDAO userDAO;
    private final ReservationDAO reservationDAO;

    @Autowired
    public SeatReservationService(SeatDAO seatDAO, UserDAO userDAO, ReservationDAO reservationDAO) {
        this.seatDAO = seatDAO;
        this.userDAO = userDAO;
        this.reservationDAO = reservationDAO;
    }

    @Override
    public void createReservation(Long userId, Long seatId, Timestamp startTime, Timestamp endTime) {
        // Get the information of the user and seat
        User user = userDAO.getUserById(userId);
        Seat seat = seatDAO.getSeatById(seatId);

        if (user == null || seat == null) {
            throw new IllegalArgumentException("User or Seat not found");
        }

        // Check if the seat is available
        if (!reservationDAO.isSeatAvailable(seatId, startTime, endTime)) {
            throw new IllegalArgumentException("Seat is not available for the selected time frame");
        }

        // Create a new reservation
            Reservation reservation = new Reservation();
            reservation.setUser(user);
            reservation.setSeat(seat);
            reservation.setStartDateTime(startTime);
            reservation.setEndDateTime(endTime);
            reservation.setReservedStatus("Reserved");
            reservationDAO.createReservation(reservation);

    }

    @Override
    public void updateReservation(Reservation reservation) {
        // Update the existing reservation
        try {
            reservationDAO.updateReservation(reservation);
        } catch (HibernateException e) {
            throw new DataAccessException("Error updating reservation", e);
        }
    }

    @Override
    public List<Reservation> getAllReservations() {
        // Get all reservations
        try {
            return reservationDAO.getAllReservations();
        } catch (HibernateException e) {
            throw new DataAccessException("Error fetching reservations", e);
        }
    }

    @Override
    public boolean isSeatAvailable(Long seatId, Timestamp startTime, Timestamp endTime) {
        // Check if the seat is available for the given time frame
        try {
            return reservationDAO.isSeatAvailable(seatId, startTime, endTime);
        } catch (HibernateException e) {
            throw new DataAccessException("Error checking seat availability", e);
        }
    }

    @Override
    public List<Reservation> getReservationsBySeatId(Long seatId) {
        // Get reservations by seat ID
        try {
            return reservationDAO.getReservationsBySeatId(seatId);
        } catch (HibernateException e) {
            throw new DataAccessException("Error fetching reservations by seat ID", e);
        }
    }

    @Override
    public void deleteReservationBySeatId(Long seatId) {
        // Delete reservations by seat ID
        try {
            reservationDAO.deleteReservationBySeatId(seatId);
        } catch (HibernateException e) {
            throw new DataAccessException("Error deleting reservations by seat ID", e);
        }
    }

    @Override
    public List<Reservation> getReservationsByUserId(Long userId) {
        // Get reservations by user ID
        try {
            return reservationDAO.getReservationsByUserId(userId);
        } catch (HibernateException e) {
            throw new DataAccessException("Error fetching reservations by user ID", e);
        }
    }

    @Override
    public void deleteReservationByUserId(Long userId) {
        // Delete reservations by user ID
        try {
            reservationDAO.deleteReservationByUserId(userId);
        } catch (HibernateException e) {
            throw new DataAccessException("Error deleting reservations by user ID", e);
        }
    }

    @Override
    public Reservation getReservationById(Long id) {
        // Get reservation by ID
        try {
            return reservationDAO.getReservationById(id);
        } catch (HibernateException e) {
            throw new DataAccessException("Error fetching reservation by ID", e);
        }
    }

    @Override
    public void deleteReservation(Long id) {
        // Delete reservation by ID
        try {
            reservationDAO.deleteReservation(id);
        } catch (HibernateException e) {
            throw new DataAccessException("Error deleting reservation", e);
        }
    }

    @Override
    public void deleteAllReservations() {
        // Delete all reservations
        try {
            reservationDAO.deleteAllReservations();
        } catch (HibernateException e) {
            throw new DataAccessException("Error deleting all reservations", e);
        }
    }

    @Override
    public List<Seat> getAvailableSeats(String seatType, Timestamp startTime, Timestamp endTime) {
        // Get available seats for the given time frame
        try {
            return reservationDAO.getAvailableSeats(seatType, startTime, endTime);
        } catch (HibernateException e) {
            throw new DataAccessException("Error fetching available seats", e);
        }
    }

    @Override
    public List<Reservation> getReservationsForTodayAndTomorrow(){
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            Timestamp tomorrowEnd = Timestamp.valueOf(LocalDateTime.now().plusDays(2).withHour(0).withMinute(0).withSecond(0));
            return reservationDAO.getReservationsBetween(now, tomorrowEnd);
        }

    @Override
    public List<Reservation> filterReservations(String seatType, Integer floor, Timestamp start, Timestamp end) {
         return reservationDAO.filterReservations(seatType, floor, start, end);
    }

    @Override
    public boolean isSeatAvailableForUpdate(Long seatId, Timestamp start, Timestamp end, Long excludeReservationId){
        try {
            return reservationDAO.isSeatAvailableForUpdate(seatId, start, end, excludeReservationId);
        } catch (HibernateException e) {
            throw new DataAccessException("Error checking seat availability for update", e);
        }
    }



}



