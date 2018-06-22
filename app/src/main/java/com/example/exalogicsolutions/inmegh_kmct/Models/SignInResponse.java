package com.example.exalogicsolutions.inmegh_kmct.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignInResponse {

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("user_role")
    @Expose
    private String userType;

    @SerializedName("user_name")
    @Expose
    private String userName;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("message")
    @Expose
    private String message;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
