package com.example.exalogicsolutions.inmegh_kmct.Models.CollegeInfoResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("college_id")
    @Expose
    private Integer collegeId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("address_line_1")
    @Expose
    private String addressLine1;
    @SerializedName("address_line_2")
    @Expose
    private String addressLine2;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("student_login_prefix")
    @Expose
    private String studentLoginPrefix;
    @SerializedName("employee_login_prefix")
    @Expose
    private String employeeLoginPrefix;
    @SerializedName("approved_by")
    @Expose
    private Object approvedBy;
    @SerializedName("affilated_by")
    @Expose
    private Object affilatedBy;
    @SerializedName("logo_file_name")
    @Expose
    private Object logoFileName;
    @SerializedName("logo_content_type")
    @Expose
    private Object logoContentType;
    @SerializedName("logo_file_size")
    @Expose
    private Object logoFileSize;
    @SerializedName("logo_updated_at")
    @Expose
    private Object logoUpdatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getStudentLoginPrefix() {
        return studentLoginPrefix;
    }

    public void setStudentLoginPrefix(String studentLoginPrefix) {
        this.studentLoginPrefix = studentLoginPrefix;
    }

    public String getEmployeeLoginPrefix() {
        return employeeLoginPrefix;
    }

    public void setEmployeeLoginPrefix(String employeeLoginPrefix) {
        this.employeeLoginPrefix = employeeLoginPrefix;
    }

    public Object getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Object approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Object getAffilatedBy() {
        return affilatedBy;
    }

    public void setAffilatedBy(Object affilatedBy) {
        this.affilatedBy = affilatedBy;
    }

    public Object getLogoFileName() {
        return logoFileName;
    }

    public void setLogoFileName(Object logoFileName) {
        this.logoFileName = logoFileName;
    }

    public Object getLogoContentType() {
        return logoContentType;
    }

    public void setLogoContentType(Object logoContentType) {
        this.logoContentType = logoContentType;
    }

    public Object getLogoFileSize() {
        return logoFileSize;
    }

    public void setLogoFileSize(Object logoFileSize) {
        this.logoFileSize = logoFileSize;
    }

    public Object getLogoUpdatedAt() {
        return logoUpdatedAt;
    }

    public void setLogoUpdatedAt(Object logoUpdatedAt) {
        this.logoUpdatedAt = logoUpdatedAt;
    }

    @Override
    public String toString() {
        return "InfoResponse{" +
                "id=" + id +
                ", collegeId=" + collegeId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", address='" + address + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", studentLoginPrefix='" + studentLoginPrefix + '\'' +
                ", employeeLoginPrefix='" + employeeLoginPrefix + '\'' +
                ", approvedBy=" + approvedBy +
                ", affilatedBy=" + affilatedBy +
                ", logoFileName=" + logoFileName +
                ", logoContentType=" + logoContentType +
                ", logoFileSize=" + logoFileSize +
                ", logoUpdatedAt=" + logoUpdatedAt +
                '}';
    }
}
