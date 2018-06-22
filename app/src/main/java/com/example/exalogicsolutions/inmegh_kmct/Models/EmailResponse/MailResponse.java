package com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MailResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("inbox_list")
    @Expose
    private List<ReceivedMail> inboxList = null;
    @SerializedName("unread_mails_count")
    @Expose
    private Integer unreadMailsCount;
    @SerializedName("sent_lists")
    @Expose
    private List<SentEmail> sentLists = null;
    @SerializedName("trash_list")
    @Expose
    private List<DeletedEmail> trashList = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ReceivedMail> getInboxList() {
        return inboxList;
    }

    public void setInboxList(List<ReceivedMail> inboxList) {
        this.inboxList = inboxList;
    }

    public Integer getUnreadMailsCount() {
        return unreadMailsCount;
    }

    public void setUnreadMailsCount(Integer unreadMailsCount) {
        this.unreadMailsCount = unreadMailsCount;
    }

    public List<SentEmail> getSentLists() {
        return sentLists;
    }

    public void setSentLists(List<SentEmail> sentLists) {
        this.sentLists = sentLists;
    }

    public List<DeletedEmail> getTrashList() {
        return trashList;
    }

    public void setTrashList(List<DeletedEmail> trashList) {
        this.trashList = trashList;
    }
}
