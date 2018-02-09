package com.touhidapps.androidnotificationexample;

/**
 * Created by Touhid on 1/8/2018.
 */

public class NotificationDataModel {

    private String name;
    private String info;
    private String titleExpanded;
    private String titleSmall;
    private String detailSmall;
    private String imageUrl;

    public NotificationDataModel(String name, String info, String titleExpanded, String titleSmall, String detailSmall, String imageUrl) {
        this.name = name;
        this.info = info;
        this.titleExpanded = titleExpanded;
        this.titleSmall = titleSmall;
        this.detailSmall = detailSmall;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTitleExpanded() {
        return titleExpanded;
    }

    public void setTitleExpanded(String titleExpanded) {
        this.titleExpanded = titleExpanded;
    }

    public String getTitleSmall() {
        return titleSmall;
    }

    public void setTitleSmall(String titleSmall) {
        this.titleSmall = titleSmall;
    }

    public String getDetailSmall() {
        return detailSmall;
    }

    public void setDetailSmall(String detailSmall) {
        this.detailSmall = detailSmall;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
