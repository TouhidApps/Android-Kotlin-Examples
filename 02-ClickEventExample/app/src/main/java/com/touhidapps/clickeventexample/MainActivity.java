package com.touhidapps.clickeventexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button myButton, myButtonTwo;
    TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = findViewById(R.id.buttonOne);
        myButtonTwo = findViewById(R.id.buttonTwo);
        myTextView = findViewById(R.id.textViewOne);

//        myButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Hello Touhid", Toast.LENGTH_SHORT).show();
//            }
//        });

        myButton.setOnClickListener(this);
        myButtonTwo.setOnClickListener(this);
        myTextView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonOne:
                Toast.makeText(getApplicationContext(), "Hello Touhid - 1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonTwo:
                Toast.makeText(getApplicationContext(), "Hello Touhid - 2", Toast.LENGTH_SHORT).show();
                break;

            case R.id.textViewOne:
                Toast.makeText(this, "From Text View Click", Toast.LENGTH_SHORT).show();
                break;
        }


    }



    public void showSomeText(View view){
        Toast.makeText(this, "Something", Toast.LENGTH_SHORT).show();
    }


}
