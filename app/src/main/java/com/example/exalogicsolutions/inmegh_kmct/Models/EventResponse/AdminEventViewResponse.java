package com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminEventViewResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("events")
    @Expose
    private List<Adminevents> events = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Adminevents> getEvents() {
        return events;
    }

    public void setEvents(List<Adminevents> events) {
        this.events = events;
    }
}
