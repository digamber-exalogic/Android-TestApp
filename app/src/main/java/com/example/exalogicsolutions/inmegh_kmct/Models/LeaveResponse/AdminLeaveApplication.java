package com.example.exalogicsolutions.inmegh_kmct.Models.LeaveResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminLeaveApplication {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("emp_name")
    @Expose
    private String empName;
    @SerializedName("leave_type")
    @Expose
    private String leaveType;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("max_count")
    @Expose
    private String maxCount;
    @SerializedName("available")
    @Expose
    private String available;
    @SerializedName("no_days_lop")
    @Expose
    private Double noDaysLop;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("course_name")
    @Expose
    private String courseName;
    @SerializedName("approved")
    @Expose
    private String approved;

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
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

    public String getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(String maxCount) {
        this.maxCount = maxCount;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public Double getNoDaysLop() {
        return noDaysLop;
    }

    public void setNoDaysLop(Double noDaysLop) {
        this.noDaysLop = noDaysLop;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
