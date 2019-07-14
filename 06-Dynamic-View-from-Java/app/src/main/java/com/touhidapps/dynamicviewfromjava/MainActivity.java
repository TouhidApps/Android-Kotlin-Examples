package com.touhidapps.dynamicviewfromjava;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //----------------------
        // set view form xml
        setContentView(R.layout.activity_main);
        //----------------------

        //----------------------
        // set view from java
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        );

        linearLayout.setBackgroundColor(Color.GREEN);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.fruit);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // ImageView size
        LinearLayout.LayoutParams layoutParamsImgView = new LinearLayout.LayoutParams(
                300, 300
        );

        imageView.setLayoutParams(layoutParamsImgView);
        linearLayout.addView(imageView);


        setContentView(linearLayout, layoutParams);
        //----------------------


        // set dynamic view
        linearLayout = findViewById(R.id.linearLayout);
        for (int i = 0; i < 5; i++) {
            View view = getLayoutInflater().inflate(R.layout.row_item, null);
            final TextView textView = view.findViewById(R.id.textView);
            textView.setText(String.valueOf(i));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "" + textView.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
            linearLayout.addView(view);
        }


    }
}
