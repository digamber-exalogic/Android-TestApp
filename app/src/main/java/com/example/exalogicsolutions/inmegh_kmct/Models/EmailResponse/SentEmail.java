package com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SentEmail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("profile")
    @Expose
    private String profile;
    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("time")
    @Expose
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
