package com.example.exalogicsolutions.inmegh_kmct.Models.LeaveResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdminLeaveResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("leave_applications")
    @Expose
    private List<AdminLeaveApplication> leaveApplications = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<AdminLeaveApplication> getLeaveApplications() {
        return leaveApplications;
    }

    public void setLeaveApplications(List<AdminLeaveApplication> leaveApplications) {
        this.leaveApplications = leaveApplications;
    }
}
