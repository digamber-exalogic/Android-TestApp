package com.example.exalogicsolutions.inmegh_kmct.Models.HolidayResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HolidayDeleteResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HolidayDeleteResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
