package com.example.Fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cloudproject.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class VideoFragment extends Fragment {

    String uri1="https://firebasestorage.googleapis.com/v0/b/cloudproject-3862c.appspot.com/o/WatchVideo%2Fاجمل%20تصوير%20جوي%20القدس%20الشريف%20-%20HD.mp4?alt=media&token=10b8490a-a879-4b59-a289-32a9e16e0a4b";
    String uri2 ="https://firebasestorage.googleapis.com/v0/b/cloudproject-3862c.appspot.com/o/WatchVideo%2Fجولة%20رائعة%20في%20مدينة%20القدس%20-%20like%20%26%20subscribe%20%26%20comment.mp4?alt=media&token=7a1af7f6-487a-4a20-985e-32973cd1ec22";
    String uri3 ="https://firebasestorage.googleapis.com/v0/b/cloudproject-3862c.appspot.com/o/WatchVideo%2Fجولة%20على%20أسوار%20القدس%20وأبوابها%20المفتوحة.mp4?alt=media&token=ee1287ff-99ba-4364-aa88-45da720cab18";
    String uri4 ="https://firebasestorage.googleapis.com/v0/b/cloudproject-3862c.appspot.com/o/WatchVideo%2Fجولة%20في%20أسواق%20مدينة%20القدس%20القديمة.mp4?alt=media&token=6948a872-dc6d-4792-a8a5-b34dd2a66e61";

    private boolean isPlay;

    private PlayerView playerView1;
    private PlayerView playerView2;
    private PlayerView playerView3;
    private PlayerView playerView4;

    SimpleExoPlayer player;

    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playPackPosition = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vedio, container, false);

        playerView1 = root.findViewById(R.id.videoview1);
        playerView2 = root.findViewById(R.id.videoview2);
        playerView3 = root.findViewById(R.id.videoview3);
        playerView4 = root.findViewById(R.id.videoview4);
        return  root;
    }



    private void releaseVideo() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playPackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;

        }
    }

    private void initVideo(PlayerView playerView,String videoUrl) {
        player = ExoPlayerFactory.newSimpleInstance(getContext());
        playerView.setPlayer(player);
        Uri uri = Uri.parse(videoUrl);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(), "exoplayer_codelab");
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playPackPosition);
        player.prepare(mediaSource, false, false);
        playerView.onPause();
    }
    @Override
    public void onStart() {
        super.onStart();
        initVideo(playerView1,uri1);
        initVideo(playerView2,uri2);
        initVideo(playerView3,uri3);
        initVideo(playerView4,uri4);
    }

    @Override
    public void onPause() {
        super.onPause();
        releaseVideo();
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseVideo();
    }
}