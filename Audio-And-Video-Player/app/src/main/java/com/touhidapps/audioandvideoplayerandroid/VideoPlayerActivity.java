package com.touhidapps.audioandvideoplayerandroid;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoPlayerActivity extends AppCompatActivity {

    VideoView videoView;
    TextView textViewBuffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        videoView = findViewById(R.id.videoView);
        textViewBuffer = findViewById(R.id.textViewBuffer);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        // from app raw dir
//        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.pink);
//        videoView.setMediaController(mediaController);
//        videoView.setKeepScreenOn(true);
//        videoView.start();


        // from url
        videoView.setVideoURI(Uri.parse("https://touhidapps.com/api/demo/pink.mp4"));
//        videoView.setVideoURI(Uri.parse("http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4"));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start(); // start again after end
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textViewBuffer.setText(videoView.getBufferPercentage() + "");
                        }
                    });
                }

            }
        }).start();


    }
}
