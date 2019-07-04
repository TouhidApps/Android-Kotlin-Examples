package com.touhidapps.audioandvideoplayerandroid;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class AudioPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mediaPlayer;
    Button buttonPlayPause, buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);

        buttonPlayPause = findViewById(R.id.buttonPlayPause);
        buttonStop = findViewById(R.id.buttonStop);
        buttonPlayPause.setOnClickListener(this);
        buttonStop.setOnClickListener(this);


        // load from raw folder
        mediaPlayer = MediaPlayer.create(this, R.raw.akash);
        mediaPlayer.start();
        buttonPlayPause.setText("Pause");


       // setMediaPlayerFromUrl();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPlayPause:
                playAudioFromUrl();
                break;

            case R.id.buttonStop:
                    mediaPlayer.seekTo(0);
                    mediaPlayer.pause();
                break;
        }
    }


    public void setMediaPlayerFromUrl(){
        // load form url
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(AudioPlayerActivity.this, Uri.parse("https://touhidapps.com/api/demo/akash.mp3"));
            mediaPlayer.prepareAsync(); // returns immediately, rather than blocking until enough data has been buffered
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
                buttonPlayPause.setText("Pause");
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // TODO start again or play next
                Toast.makeText(AudioPlayerActivity.this, "End", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void playAudioFromUrl() {

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            buttonPlayPause.setText("Pause");
        } else {
            mediaPlayer.pause();
            buttonPlayPause.setText("Play");

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}
