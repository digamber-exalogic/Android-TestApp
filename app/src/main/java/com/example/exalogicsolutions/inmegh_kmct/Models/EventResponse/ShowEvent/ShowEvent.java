package com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.ShowEvent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShowEvent {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("is_common_to_all")
    @Expose
    private Boolean isCommonToAll;
    @SerializedName("is_single_day")
    @Expose
    private Boolean isSingleDay;
    @SerializedName("department")
    @Expose
    private Object department;
    @SerializedName("images")
    @Expose
    private List<ShowImage> images = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Boolean getIsCommonToAll() {
        return isCommonToAll;
    }

    public void setIsCommonToAll(Boolean isCommonToAll) {
        this.isCommonToAll = isCommonToAll;
    }

    public Boolean getIsSingleDay() {
        return isSingleDay;
    }

    public void setIsSingleDay(Boolean isSingleDay) {
        this.isSingleDay = isSingleDay;
    }

    public Object getDepartment() {
        return department;
    }

    public void setDepartment(Object department) {
        this.department = department;
    }

    public List<ShowImage> getImages() {
        return images;
    }

    public void setImages(List<ShowImage> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "showevent{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", isCommonToAll=" + isCommonToAll +
                ", isSingleDay=" + isSingleDay +
                ", department=" + department +
                ", images=" + images +
                '}';
    }
}
