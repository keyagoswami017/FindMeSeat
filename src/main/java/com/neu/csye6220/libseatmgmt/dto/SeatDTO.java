package com.neu.csye6220.libseatmgmt.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SeatDTO {

    @NotBlank(message = "Seat number cannot be blank")
    private String seatNumber;

    @NotBlank(message = "Seat type cannot be blank")
    private String seatType;

    @Min( value = 1, message = "Floor number must be a number greater than 0")
    private int floorNumber;

    @NotNull(message = "Please select if the seat is available or not")
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

    public int getFloorNumber() {
        return floorNumber;
    }
    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public boolean isAvailable() { return available;}
    public void setAvailable(boolean available) {this.available = available;}
}
