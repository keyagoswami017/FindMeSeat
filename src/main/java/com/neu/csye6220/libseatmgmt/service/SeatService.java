package com.neu.csye6220.libseatmgmt.service;

import com.neu.csye6220.libseatmgmt.model.Seat;
import com.neu.csye6220.libseatmgmt.dao.SeatDAO;
import com.neu.csye6220.libseatmgmt.service.interfaces.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService implements ISeatService {


    private final SeatDAO seatDAO;

    @Autowired
    public SeatService(SeatDAO seatDAO) {
        this.seatDAO = seatDAO;
    }

    @Override
    public void createSeat(Seat seat) {
        seatDAO.createSeat(seat);
    }

    @Override
    public void deleteSeat(Long id) {
        seatDAO.deleteSeat(id);
    }

    @Override
    public void updateSeat(Seat seat) {
        seatDAO.updateSeat(seat);
    }

    @Override
    public Seat getSeatById(Long id) {
        Seat seat = seatDAO.getSeatById(id);
        return seat;
    }

    @Override
    public List<Seat> getAllSeats() {
        List<Seat> seats = seatDAO.getAllSeats();
        return seats;
    }

    @Override
    public List<Seat> getSeatsByType(String seatType) {
        return seatDAO.getSeatsByType(seatType);
    }

}
