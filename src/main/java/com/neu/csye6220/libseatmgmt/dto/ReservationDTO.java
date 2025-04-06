package com.neu.csye6220.libseatmgmt.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

public class ReservationDTO {

    @NotBlank(message = "Start Date Time cannot be blank")
    @FutureOrPresent(message = "Start Date Time must be in the future or present")
    private Timestamp startDateTime;

    @NotBlank(message = "End Date Time cannot be blank")
    @FutureOrPresent(message = "End Date Time must be in the future or present")
    private Timestamp endDateTime;

    private long sid;
    private long uid;

    public long getSid() {
        return sid;
    }
    public void setSid(long sid) {
        this.sid = sid;
    }

    public long getUid() {
        return uid;
    }
    public void setUid(long uid) {
        this.uid = uid;
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
}

