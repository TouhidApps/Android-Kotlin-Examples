package com.touhidapps.databindingexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.touhidapps.databindingexample.R;
import com.touhidapps.databindingexample.databinding.ActivityExTwoBinding;

public class ExTwoActivity extends AppCompatActivity {

    ActivityExTwoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_ex_two);




    }

    public void changeMyData(View view) {

        binding.setMyId(1);
        binding.setMyName("Touhid");

    }
}
