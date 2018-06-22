package com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComposemailResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("user_emails")
    @Expose
    private List<UserEmail> userEmails = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<UserEmail> getUserEmails() {
        return userEmails;
    }

    public void setUserEmails(List<UserEmail> userEmails) {
        this.userEmails = userEmails;
    }
}
