package com.touhidapps.databindingexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.touhidapps.databindingexample.R;
import com.touhidapps.databindingexample.databinding.ActivityMyObservableBinding;
import com.touhidapps.databindingexample.model.PersonObs;


public class MyObservableActivity extends AppCompatActivity {

    private ActivityMyObservableBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_observable);

//        PropertyChangeRegistry registry = new PropertyChangeRegistry();

        // Object will remain null until set object and observable will not work
        
//        binding.setPersonObsObj(new Observable() {
//            @Override
//            public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
//                registry.add(callback);
//            }
//
//            @Override
//            public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
//                registry.add(callback);
//            }
//        });
        binding.setPersonObs(new PersonObs("Lorem", "Ipsum"));


    } // onCreate



}
