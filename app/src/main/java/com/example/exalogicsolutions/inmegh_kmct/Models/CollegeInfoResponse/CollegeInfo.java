package com.example.exalogicsolutions.inmegh_kmct.Models.CollegeInfoResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collection;



public class CollegeInfo {

    @SerializedName("response")
    @Expose
    private InfoResponse response;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("college_name")
    @Expose
    private String collegeName;
    @SerializedName("institution_img")
    @Expose
    private String institutionImg;
    @SerializedName("message")
    @Expose
    private String message;

    public InfoResponse getResponse() {
        return response;
    }

    public void setResponse(InfoResponse response) {
        this.response = response;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getInstitutionImg() {
        return institutionImg;
    }

    public void setInstitutionImg(String institutionImg) {
        this.institutionImg = institutionImg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CollegeInfo{" +
                "response=" + response +
                ", status=" + status +
                ", collegeName='" + collegeName + '\'' +
                ", institutionImg='" + institutionImg + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
