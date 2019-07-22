package com.touhidapps.livedataexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();

    private TextView textView;
    private Button button;

    private MainActivityViewModel model;
    private int myNumberCurrentValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Increment of current number
                model.myNumber.setValue(myNumberCurrentValue + 1);

            }
        });

        viewModelInit();


    } // onCreate


    private void viewModelInit() {

        // Call from onCreate to avoid multiple call
        model = ViewModelProviders.of(MainActivity.this).get(MainActivityViewModel.class);

        model.myNumber.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                Log.d(TAG, "onChanged: " + integer);

                myNumberCurrentValue = integer;
                textView.setText(String.valueOf(integer));

            }
        });

    } // viewModelInit


}
