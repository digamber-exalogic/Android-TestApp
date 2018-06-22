package com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Adminevents {

    @SerializedName("course")
    @Expose
    private String course;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("is_common_to_all")
    @Expose
    private Boolean isCommonToAll;
    @SerializedName("is_single_day")
    @Expose
    private Boolean isSingleDay;
    @SerializedName("images")
    @Expose
    private List<AdminEventImage> images = null;
    public Integer getId() {
        return id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCommonToAll() {
        return isCommonToAll;
    }

    public void setCommonToAll(Boolean commonToAll) {
        isCommonToAll = commonToAll;
    }

    public Boolean getSingleDay() {
        return isSingleDay;
    }

    public void setSingleDay(Boolean singleDay) {
        isSingleDay = singleDay;
    }

    public List<AdminEventImage> getImages() {
        return images;
    }

    public void setImages(List<AdminEventImage> images) {
        this.images = images;
    }
}
