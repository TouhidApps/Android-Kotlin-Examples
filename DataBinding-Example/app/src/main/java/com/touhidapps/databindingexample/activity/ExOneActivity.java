package com.touhidapps.databindingexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.touhidapps.databindingexample.R;
import com.touhidapps.databindingexample.databinding.ActivityExOneBinding;
import com.touhidapps.databindingexample.model.Person;

public class ExOneActivity extends AppCompatActivity {

    Person person;
    /**
     * ActivityExOneBinding class generates from xml file named activity_ex_one.xml with postfix Binding name
     */
    ActivityExOneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ex_one);
        person = new Person("T", "I");
        binding.setPerson(person);

    }


    public void changePersonName(View view) {

        person.firstName = "Touhidul";
        person.lastName = "Islam";
        binding.setPerson(person);

    }


}
