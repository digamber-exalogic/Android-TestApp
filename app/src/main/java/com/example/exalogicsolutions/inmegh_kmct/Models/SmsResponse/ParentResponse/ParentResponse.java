package com.example.exalogicsolutions.inmegh_kmct.Models.SmsResponse.ParentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ParentResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("students")
    @Expose
    private List<Parent> students = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Parent> getStudents() {
        return students;
    }

    public void setStudents(List<Parent> students) {
        this.students = students;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
