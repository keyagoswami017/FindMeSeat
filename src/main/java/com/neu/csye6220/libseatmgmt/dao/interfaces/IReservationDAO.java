package com.neu.csye6220.libseatmgmt.dao.interfaces;

import com.neu.csye6220.libseatmgmt.model.Reservation;

import java.util.List;

public interface IReservationDAO {
    void saveReservation(Reservation reservation);
    void updateReservation(Reservation reservation);
    Reservation getReservationById(Long id);
    List<Reservation> getReservationsByUserId(Long userId);
    List<Reservation> getAllReservations();
    void deleteReservation(Long id);
    void deleteAllReservations();
}
