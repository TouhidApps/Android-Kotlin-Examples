package com.touhidapps.recyclerviewandvolleyrequests;

/**
 * Created by Touhid on 7/26/2017.
 */

public class DemoDataModel {

    private int id;
    private String name, details, fileName, baseUrl;

    public DemoDataModel() {

    }

    public DemoDataModel(int id, String name, String details, String fileName, String baseUrl) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.fileName = fileName;
        this.baseUrl = baseUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
