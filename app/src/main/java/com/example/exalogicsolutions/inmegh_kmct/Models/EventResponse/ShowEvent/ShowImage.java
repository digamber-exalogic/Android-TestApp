package com.example.exalogicsolutions.inmegh_kmct.Models.EventResponse.ShowEvent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowImage {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("images")
    @Expose
    private String images;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ShowImage{" +
                "id=" + id +
                ", images='" + images + '\'' +
                '}';
    }
}
