
package com.touhidapps.jsondataparsing.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyJsonObject {

    @SerializedName("Category")
    @Expose
    private List<Category> category = null;
    @SerializedName("Collection")
    @Expose
    private List<Collection> collection = null;
    @SerializedName("app_data_date_time")
    @Expose
    private String appDataDateTime;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Collection> getCollection() {
        return collection;
    }

    public void setCollection(List<Collection> collection) {
        this.collection = collection;
    }

    public String getAppDataDateTime() {
        return appDataDateTime;
    }

    public void setAppDataDateTime(String appDataDateTime) {
        this.appDataDateTime = appDataDateTime;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}
