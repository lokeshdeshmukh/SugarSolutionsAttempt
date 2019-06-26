package com.sugar.example.model.pojo;

import com.google.gson.annotations.SerializedName;

public class Images {
    @SerializedName("src")
    private String urlImage;

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
