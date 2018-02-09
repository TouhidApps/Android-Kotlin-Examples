package com.touhidapps.audioandvideoplayerandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonAudioPlay, buttonVideoPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAudioPlay = findViewById(R.id.buttonAudioPlay);
        buttonVideoPlay = findViewById(R.id.buttonVideoPlay);

        buttonAudioPlay.setOnClickListener(this);
        buttonVideoPlay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAudioPlay:

                startActivity(new Intent(this, AudioPlayerActivity.class));

                break;

            case R.id.buttonVideoPlay:

                startActivity(new Intent(this, VideoPlayerActivity.class));

                break;
        }
    }


}
