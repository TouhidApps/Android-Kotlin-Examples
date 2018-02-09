package com.touhidapps.appupdatesnackbar;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    String tempText, mVersion, mMessage = "";
    LinearLayout linearLayoutRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayoutRoot = findViewById(R.id.linearLayoutRoot);

        checkUpdate();

    } // end of onCreate

    public void checkUpdate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create a URL for the desired page
                    URL url = new URL("http://www.vumobile.biz/shaboxbuddy/shaboxbuddy_version.txt");

                    // Read all the text returned by the server
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.setUseCaches(false); // get value from catch disabled
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                    int i = 1;
                    while ((tempText = bufferedReader.readLine()) != null) {
                        if (i == 1) { // First line is version
                            mVersion = tempText;
                        } else { // Second line and subsequent lines have message
                            mMessage = tempText;
                        }
                        i++;
                    }

                    String mVer = BuildConfig.VERSION_NAME;

                    if (!mVersion.equals(mVer)) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateSnackBar(mVersion, mMessage);
                            }
                        });
                    }


                    bufferedReader.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * (...) varargs-- can pass in an array of parameters without explicitly creating the array
     * private void method(String[] args) {} is called like method(new String[]{"first", "second"});
     * private void method(String... args) {} is called like method("first", "second");
     *
     * @param versionAndMessage
     */
    String ver, msg = "";

    public void updateSnackBar(String... versionAndMessage) {
        for (int i = 1; i < versionAndMessage.length; i++) {
            if (i == 1) {
                ver = versionAndMessage[i];
            } else {
                msg = versionAndMessage[i];
            }
        }

        final Snackbar snackbar = Snackbar.make(linearLayoutRoot, msg + versionAndMessage[0], Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("UPDATE NOW", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
                /**
                 * if this button is clicked, go to play store to update
                 */
                final String appPackageName = getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });
        snackbar.show();

    }


}
