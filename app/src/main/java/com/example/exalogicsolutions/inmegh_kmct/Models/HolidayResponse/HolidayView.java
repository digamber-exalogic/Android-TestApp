package com.example.exalogicsolutions.inmegh_kmct.Models.HolidayResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HolidayView {



    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("single_day")
    @Expose
    private String singleDay;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("start_date")
    @Expose
    private String mStartDate;
    @SerializedName("end_date")
    @Expose
    private String mEndDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSingleDay() {
        return singleDay;
    }

    public void setSingleDay(String singleDay) {
        this.singleDay = singleDay;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getmStartDate() {
        return mStartDate;
    }

    public void setmStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }

    public String getmEndDate() {
        return mEndDate;
    }

    public void setmEndDate(String mEndDate) {
        this.mEndDate = mEndDate;
    }

    @Override
    public String toString() {
        return "HolidayView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", singleDay='" + singleDay + '\'' +
                ", image='" + image + '\'' +
                ", startDate=" + mStartDate +
                ", endDate=" + mEndDate +
                '}';
    }
}
