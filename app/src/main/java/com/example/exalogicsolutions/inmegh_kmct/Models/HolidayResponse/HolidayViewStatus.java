package com.example.exalogicsolutions.inmegh_kmct.Models.HolidayResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HolidayViewStatus {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("holidays")
    @Expose
    private List<HolidayView> holidays = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<HolidayView> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<HolidayView> holidays) {
        this.holidays = holidays;
    }

    @Override
    public String toString() {
        return "HolidayViewStatus{" +
                "status=" + status +
                ", holidays=" + holidays +
                '}';
    }
}
