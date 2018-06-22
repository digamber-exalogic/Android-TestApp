package com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmailReadResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("sender")
    @Expose
    private String sender;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("attachment")
    @Expose
    private String attachment;
    @SerializedName("sent_date")
    @Expose
    private String sent_date;
    @SerializedName("sent_time")
    @Expose
    private String sent_time;
    @SerializedName("sender_id")
    @Expose
    private String sender_id;
    @SerializedName("receiver_ids")
    @Expose
    private String receiver_ids;
    @SerializedName("profile")
    @Expose
    private String profile;
    @SerializedName("time")
    @Expose
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getSent_date() {
        return sent_date;
    }

    public void setSent_date(String sent_date) {
        this.sent_date = sent_date;
    }

    public String getSent_time() {
        return sent_time;
    }

    public void setSent_time(String sent_time) {
        this.sent_time = sent_time;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_ids() {
        return receiver_ids;
    }

    public void setReceiver_ids(String receiver_ids) {
        this.receiver_ids = receiver_ids;
    }
}
