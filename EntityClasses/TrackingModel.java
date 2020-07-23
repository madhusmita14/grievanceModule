package com.madhusmita.final__ois.EntityClasses;

public class TrackingModel {
    String status, time;

    public TrackingModel(String status, String time) {
        this.status = status;
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
