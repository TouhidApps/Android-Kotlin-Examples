package com.touhidapps.androidnotificationexample;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    TextView textViewName, textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        textViewName = findViewById(R.id.textViewName);
        textViewInfo = findViewById(R.id.textViewInfo);

        // Notification will be received here
        if (getIntent().getAction().equals("my.action")) {
            textViewName.setText(getIntent().getStringExtra(MainActivity.NAME));
            textViewInfo.setText(getIntent().getStringExtra(MainActivity.INFO));
            String a = getIntent().getStringExtra(MainActivity.NOTIF_ID);

            // remove notification when clicked on button
            if (a != null) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.cancel(Integer.valueOf(a));
            }

        }


    }
}
