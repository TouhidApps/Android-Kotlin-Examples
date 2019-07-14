package com.touhidapps.exoplayer_playvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private PlayerView playerView;
    private SimpleExoPlayer player;
    private List<String> urlList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.player_view);
        playerView.setControllerVisibilityListener(new PlayerControlView.VisibilityListener() {
            @Override
            public void onVisibilityChange(int visibility) {
                Log.d(TAG, "onVisibilityChange: " + visibility);
            }
        });
        playerView.setPlaybackPreparer(new PlaybackPreparer() {
            @Override
            public void preparePlayback() {
                Log.d(TAG, "preparePlayback: ");
            }
        });

        urlList.add("https://touhidapps.com/files/t/demo/media/pink.mp4");
        urlList.add("https://sample-videos.com/video123/mp4/480/big_buck_bunny_480p_10mb.mp4");
        urlList.add("http://0.s3.envato.com/h264-video-previews/80fad324-9db4-11e3-bf3d-0050569255a8/490527.mp4");

        initializeExoPlayer(urlList);

    } // onCreate


    private void initializeExoPlayer(List<String> urls) {

        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(this),
                    new DefaultTrackSelector(),
                    new DefaultLoadControl()
            );

            player.prepare(getMediaSource(urls), true, false); // play a list of items
            // player.prepare(videoSource); // play single item

            player.addListener(new Player.EventListener() {
                @Override
                public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
                    Log.d(TAG, "onTimelineChanged: ");
                }

                @Override
                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
                    Log.d(TAG, "onTracksChanged: ");
                }

                @Override
                public void onLoadingChanged(boolean isLoading) {
                    Log.d(TAG, "onLoadingChanged: ");
                }

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    Log.d(TAG, "onPlayerStateChanged: " + playbackState);
                    // Player.STATE_BUFFERING
                }

                @Override
                public void onRepeatModeChanged(int repeatMode) {
                    Log.d(TAG, "onRepeatModeChanged: ");
                }

                @Override
                public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
                    Log.d(TAG, "onShuffleModeEnabledChanged: ");
                }

                @Override
                public void onPlayerError(ExoPlaybackException error) {
                    Log.d(TAG, "onPlayerError: " + error);
                }

                @Override
                public void onPositionDiscontinuity(int reason) {
                    Log.d(TAG, "onPositionDiscontinuity: " + reason);
                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                    Log.d(TAG, "onPlaybackParametersChanged: ");
                }

                @Override
                public void onSeekProcessed() {
                    Log.d(TAG, "onSeekProcessed: ");
                }
            });

            playerView.setPlayer(player);
            player.setPlayWhenReady(true); // to play
//        player.setPlayWhenReady(false); // to make pause

        }

    } // initializeExoPlayer


    private ConcatenatingMediaSource getMediaSource(List<String> urls) {

        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        // user agent (Ex. ExoPlayer-PlayVideo/1.0 (Linux;Android 9) ExoPlayerLib/2.7.3)
        String userAgent = Util.getUserAgent(this, getString(R.string.app_name));

        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, userAgent, bandwidthMeter);
        // This is the MediaSource representing the media to be played.

        List<MediaSource> mediaSources = new ArrayList<>();

        for (String mUrl : urls) {
            MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(mUrl));
            mediaSources.add(videoSource);
        }

        ConcatenatingMediaSource concatenatedSource = new ConcatenatingMediaSource(mediaSources.toArray(new MediaSource[mediaSources.size()]));

        return concatenatedSource;

    } // getMediaSource

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    } // releasePlayer

    @Override
    protected void onPause() {
        super.onPause();
        player.setPlayWhenReady(false);
        releasePlayer();
    } // onPause

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    } // onStop

}
