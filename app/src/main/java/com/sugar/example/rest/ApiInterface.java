package com.sugar.example.rest;

import com.sugar.example.model.pojo.Products;
import com.sugar.example.model.pojo.TypeOfProducts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public interface ApiInterface {
    @Headers({"Content-Type: application/json",
            "xAccessToken:abc",
            "user_id:abc"})
    @GET("category.json")
    Call<TypeOfProducts> getCategories();

    @GET("{category_name}/{id}.json")
    Call<Products> getSpecificCategoryDetails(@Path("category_name") String categoryName, @Path("id") String id);
}
