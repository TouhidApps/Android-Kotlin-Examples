package com.touhidapps.androidbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


/**
 * Created by IT-10 on 10/23/2017.
 */

public class InternetCheckReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        switch (action) {
//            case Intent.ACTION_AIRPLANE_MODE_CHANGED
            case ConnectivityManager.CONNECTIVITY_ACTION:
                if (isOnline(context)) {
                    Toast.makeText(context, "Online", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Offline", Toast.LENGTH_SHORT).show();
                }

                // to change ui from here we need to create another broadcast receiver
                Intent i = new Intent("my_message");
                if (isOnline(context)) {
                    // put whatever data you want to send, if any
                    i.putExtra("message", "connected");
                } else {
                    i.putExtra("message", "not_connected");
                }

                // send broadcast
                context.sendBroadcast(i);

                break;
        }
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
            return (netInfo != null && netInfo.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

}
