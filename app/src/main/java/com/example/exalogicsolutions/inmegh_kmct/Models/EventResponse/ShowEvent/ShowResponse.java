package com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.ShowEvent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("event")
    @Expose
    private ShowEvent event;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ShowEvent getEvent() {
        return event;
    }

    public void setEvent(ShowEvent event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "ShowResponse{" +
                "status=" + status +
                ", event=" + event +
                '}';
    }
}
