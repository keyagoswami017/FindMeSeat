package com.neu.csye6220.libseatmgmt.service.interfaces;

import com.neu.csye6220.libseatmgmt.model.Seat;

import java.util.List;

public interface ISeatService {
    void createSeat(Seat seat);
    void deleteSeat(Long id);
    void updateSeat(Seat seat);
    Seat getSeatById(Long id);
    List<Seat> getAllSeats();
}
