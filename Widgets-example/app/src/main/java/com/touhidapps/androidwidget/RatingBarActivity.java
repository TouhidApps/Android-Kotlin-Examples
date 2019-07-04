package com.touhidapps.androidwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class RatingBarActivity extends AppCompatActivity {

    RatingBar ratingBar;
    Button buttonRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        setTitle("Rating Bar Example");

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        buttonRating = (Button) findViewById(R.id.buttonRating);
        buttonRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RatingBarActivity.this, "" + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
            }
        });

//        ratingBar.getRating();

    }
}
