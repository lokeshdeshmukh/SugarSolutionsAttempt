package com.sugar.example.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;


public class TypeOfProducts {

    @SerializedName("category")
    private ArrayList<Map<String,ArrayList<String>>> categories;



    public TypeOfProducts(ArrayList<Map<String,ArrayList<String>>> categories) {
        this.categories = categories;

    }

    public ArrayList<Map<String,ArrayList<String>>> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Map<String,ArrayList<String>>> categories) {
        this.categories = categories;
    }
}