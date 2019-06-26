package com.sugar.example.model.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductDetails implements Parcelable {

    @SerializedName("id")
    private long id;
    @SerializedName("product_type")
    private String title;
    @SerializedName("images")
    private ArrayList<Images> images;
    @SerializedName("body_html")
    private String body_html;
    @SerializedName("variants")
    private ArrayList<Variants> variants;

    public ArrayList<Variants> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<Variants> variants) {
        this.variants = variants;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public ArrayList<Images> getImages() {
        return images;
    }

    public void setImages(ArrayList<Images> images) {
        this.images = images;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeList(this.images);
    }

    public ProductDetails() {
    }

    protected ProductDetails(Parcel in) {
        this.id = in.readLong();
        this.title = in.readString();
        this.images = new ArrayList<Images>();
        in.readList(this.images, Images.class.getClassLoader());
    }

    public static final Parcelable.Creator<ProductDetails> CREATOR = new Parcelable.Creator<ProductDetails>() {
        @Override
        public ProductDetails createFromParcel(Parcel source) {
            return new ProductDetails(source);
        }

        @Override
        public ProductDetails[] newArray(int size) {
            return new ProductDetails[size];
        }
    };
}
