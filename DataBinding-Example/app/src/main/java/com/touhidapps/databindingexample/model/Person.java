package com.touhidapps.databindingexample.model;

public class Person {

    public String firstName;
    public String lastName;
    public String imgUrl;

    public Person(String firstName, String lastName, String imgUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.imgUrl = imgUrl;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
