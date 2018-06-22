package com.example.exalogicsolutions.inmegh_kmct.Models.EmailResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserEmail {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    private Integer check_box = 0;

    public Integer getCheck_box() {
        return check_box;
    }

    public void setCheck_box(Integer check_box) {
        this.check_box = check_box;
    }

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserEmail{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", check_box=" + check_box +
                ", isSelected=" + isSelected +
                '}';
    }
}
