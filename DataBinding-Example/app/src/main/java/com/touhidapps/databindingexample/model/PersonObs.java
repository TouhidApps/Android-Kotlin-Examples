package com.touhidapps.databindingexample.model;

import androidx.databinding.ObservableField;



public class PersonObs {
    /**
     * For observable field example
     */
    public ObservableField<String> firstName = new ObservableField<>();
    public ObservableField<String> lastName = new ObservableField<>();

    public PersonObs(String firstName, String lastName) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
    }

}
