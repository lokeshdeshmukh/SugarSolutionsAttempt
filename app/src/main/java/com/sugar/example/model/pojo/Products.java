package com.sugar.example.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Products {

    @SerializedName("products")
    private ArrayList<ProductDetails> products;

    public ArrayList<ProductDetails> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<ProductDetails> products) {
        this.products = products;
    }
}
