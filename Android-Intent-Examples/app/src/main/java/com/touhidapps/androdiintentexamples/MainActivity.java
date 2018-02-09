package com.touhidapps.androdiintentexamples;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonImplicitIntent, buttonExplicitIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonImplicitIntent = (Button) findViewById(R.id.buttonImplicitIntent);
        buttonExplicitIntent = (Button) findViewById(R.id.buttonExplicitIntent);
        buttonImplicitIntent.setOnClickListener(this);
        buttonExplicitIntent.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonImplicitIntent:

                // To open url on browser
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://touhidapps.com"));
//                startActivity(intent);

                // To make a phone call
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:123"));
//                startActivity(intent);

                // To make a message
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:123"));
//                intent.putExtra("sms_body", "This is my message");
//                startActivity(intent);

                // To open android device settings
                startActivity(new Intent(Settings.ACTION_SETTINGS));

                break;

            case R.id.buttonExplicitIntent:
                Intent i = new Intent(this, Main2Activity.class);
                i.putExtra("NAME", "Touhid");
                startActivity(i);

                break;
        }
    }
}
