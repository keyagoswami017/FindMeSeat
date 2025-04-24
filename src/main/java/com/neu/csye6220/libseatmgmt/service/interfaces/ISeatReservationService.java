package com.neu.csye6220.libseatmgmt.service.interfaces;

import com.neu.csye6220.libseatmgmt.model.Reservation;
import com.neu.csye6220.libseatmgmt.model.Seat;

import java.sql.Timestamp;
import java.util.List;

public interface ISeatReservationService {
    void createReservation(Long userId, Long seatId, Timestamp startTime, Timestamp endTime);
    void updateReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    boolean isSeatAvailable(Long seatId, Timestamp startTime, Timestamp endTime);
    List<Reservation> getReservationsBySeatId(Long seatId);
    void deleteReservationBySeatId(Long seatId);
    List<Reservation> getReservationsByUserId(Long userId);
    void deleteReservationByUserId(Long userId);
    Reservation getReservationById(Long id);
    void deleteReservation(Long id);
    void deleteAllReservations();
    List<Seat> getAvailableSeats(String seatType, Timestamp startTime, Timestamp endTime);
    List<Reservation> getReservationsForTodayAndTomorrow();
    List<Reservation> filterReservations(String seatType, Integer floor, Timestamp start, Timestamp end);
    boolean isSeatAvailableForUpdate(Long seatId, Timestamp start, Timestamp end, Long excludeReservationId);


}
