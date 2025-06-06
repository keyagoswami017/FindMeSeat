package com.neu.csye6220.libseatmgmt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "seats")
public class Seat extends BaseEntity{

    @Column(name = "seat_number", nullable = false, unique = true, length = 10)
    private String seatNumber;
    @Column(name = "seat_type", nullable = false, length = 20)
    private String seatType;
    @Column(name = "floor_number", nullable = false)
    private int floorNumber;
    @Column(name = "is_available", nullable = false)
    private boolean available;

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getFloorNumber() {return floorNumber;}

    public void setFloorNumber(int floorNumber) {this.floorNumber = floorNumber;}

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


}
