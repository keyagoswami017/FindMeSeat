package com.neu.csye6220.libseatmgmt.dao.interfaces;

import com.neu.csye6220.libseatmgmt.model.Seat;

import java.util.List;

public interface ISeatDAO {
    void saveSeat(Seat seat);
    void updateSeat(Seat seat);
    Seat getSeatById(Long id);
    List<Seat> getAllSeats();
    void deleteSeat(Long id);
}
