package com.touhidapps.androidsqlite;

/**
 * Created by touhid on 8/3/17.
 */

public class InfoModel {

    private String id;
    private String name;
    private String phone;

    public InfoModel() {

    }

    public InfoModel(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public InfoModel(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " Name: " + this.name + " Phone: " + this.phone;
    }

}
