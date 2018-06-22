package com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.TeachingResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeachingResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("employees")
    @Expose
    private List<Teaching> employees = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Teaching> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Teaching> employees) {
        this.employees = employees;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
