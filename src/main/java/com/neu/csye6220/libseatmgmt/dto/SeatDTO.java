package com.neu.csye6220.libseatmgmt.dto;

import jakarta.validation.constraints.NotBlank;

public class SeatDTO {

    @NotBlank(message = "Seat number cannot be blank")
    private String seatNumber;

    @NotBlank(message = "Seat type cannot be blank")
    private String seatType;

    @NotBlank(message = "Seat status cannot be blank")
    private int floorNumber;

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

    public int getFloorNumber() {
        return floorNumber;
    }
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
