package com.touhidapps.androidwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class ToastActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonToast, buttonCustomToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        setTitle("Toast Example");

        buttonToast = (Button) findViewById(R.id.buttonToast);
        buttonCustomToast = (Button) findViewById(R.id.buttonCustomToast);
        // event listener
        buttonToast.setOnClickListener(this);
        buttonCustomToast.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonToast:

                Toast.makeText(this, "This is my toast", Toast.LENGTH_SHORT).show();
//                Toast.makeText(this, "This is my toast", Toast.LENGTH_LONG).show();

                break;

            case R.id.buttonCustomToast:

                LayoutInflater li = getLayoutInflater();
                View layout = li.inflate(R.layout.widget_custom_toast, (ViewGroup) findViewById(R.id.linearLayoutCustomToast));

                // Creating the Toast object
                Toast toast = new Toast(this);
                toast.setDuration(Toast.LENGTH_SHORT);
//                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setView(layout); //setting the view of custom toast layout
                toast.show();

                break;
        }
    }
}
