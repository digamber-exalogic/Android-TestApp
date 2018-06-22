package com.example.exalogicsolutions.inmegh_kmct.Models.NoticeBoardResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminNoticeResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("notice_boards")
    @Expose
    private List<NoticeBoard> noticeBoards = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<NoticeBoard> getNoticeBoards() {
        return noticeBoards;
    }

    public void setNoticeBoards(List<NoticeBoard> noticeBoards) {
        this.noticeBoards = noticeBoards;
    }
}
