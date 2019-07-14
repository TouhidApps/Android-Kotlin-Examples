//package com.touhidapps.databindingexample.model;
//
//import androidx.databinding.Bindable;
//import androidx.databinding.Observable;
//import androidx.databinding.PropertyChangeRegistry;
//
//public class PersonObsObj implements Observable {
//    /**
//     * For observable Object example
//     */
//
//    private PropertyChangeRegistry registry = new PropertyChangeRegistry();
//
//    public String firstName;
//    public String lastName;
//
//    public PersonObsObj(String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//
//    }
//
//
//    @Bindable // It generates id of firstName to use in notifyPropertyChanged(id)
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//
//        registry.notifyChange(this, BR.firstName);
//
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    @Override
//    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
//        registry.add(callback);
//    }
//
//    @Override
//    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
//        registry.remove(callback);
//    }
//}
