package com.touhidapps.retrofitexample.model;

import com.google.gson.annotations.SerializedName;

public class MyFlower {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("details")
    public String details;

    @SerializedName("fileName")
    public String fileName;

    @SerializedName("baseUrl")
    public String baseUrl;

}
