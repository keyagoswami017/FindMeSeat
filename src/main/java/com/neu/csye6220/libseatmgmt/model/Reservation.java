package com.neu.csye6220.libseatmgmt.model;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "reservations")
public class Reservation extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "id" , nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id" , nullable = false)
    private Seat seat;

    @Column(name = "start_time", nullable = false)
    private Timestamp startDateTime;
    @Column(name = "end_time", nullable = false)
    private Timestamp endDateTime;
    @Column(name = "status", nullable = false, length = 15)
    private String reservedStatus;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Seat getSeat() {
        return seat;
    }
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Timestamp getStartDateTime() {
        return startDateTime;
    }
    public void setStartDateTime(Timestamp startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Timestamp getEndDateTime() {
        return endDateTime;
    }
    public void setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getReservedStatus() {
        return reservedStatus;
    }
    public void setReservedStatus(String reservedStatus) {
        this.reservedStatus = reservedStatus;
    }
}
