package com.touhidapps.androidserviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by touhid on 1/11/18.
 */

public class MyService extends Service {

    private static final String TAG = "MyService";
    private String name;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null)
            name = intent.getStringExtra("NAME");
        Log.d(TAG, "onStartCommand: " + name);
        Toast.makeText(this, "StartCommand", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Destroy", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onDestroy: ");
    }


}
