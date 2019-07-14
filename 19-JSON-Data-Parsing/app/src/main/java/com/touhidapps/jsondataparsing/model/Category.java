
package com.touhidapps.jsondataparsing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("application_id")
    @Expose
    private String applicationId;
    @SerializedName("parent_id")
    @Expose
    private String parentId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("icon")
    @Expose
    private Object icon;
    @SerializedName("sort_weight")
    @Expose
    private String sortWeight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public String getSortWeight() {
        return sortWeight;
    }

    public void setSortWeight(String sortWeight) {
        this.sortWeight = sortWeight;
    }

}
