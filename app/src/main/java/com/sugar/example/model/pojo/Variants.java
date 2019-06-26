package com.sugar.example.model.pojo;

import com.google.gson.annotations.SerializedName;

public class Variants {

    @SerializedName("price")
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
