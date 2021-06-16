package Fragments;

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

public class WatchFragment extends Fragment {

    String uri1="https://firebasestorage.googleapis.com/v0/b/hind-dc029.appspot.com/o/%D9%85%D8%A7%20%D9%87%D9%8A%20%D8%A7%D9%84%D9%82%D8%AF%D8%B3%20_%20%D9%81%D9%84%D8%B3%D8%B7%D9%8A%D9%86%20%D9%81%D9%8A%20%D8%AF%D9%82%D9%8A%D9%82%D8%A9(240P).mp4?alt=media&token=99330b1f-3b28-4a6e-af17-1ea016eeebf5";
    String uri2 ="https://firebasestorage.googleapis.com/v0/b/hind-dc029.appspot.com/o/%D8%AC%D9%88%D9%84%D8%A9%20%D8%B1%D8%A7%D8%A6%D8%B9%D8%A9%20%D9%81%D9%8A%20%D9%85%D8%AF%D9%8A%D9%86%D8%A9%20%D8%A7%D9%84%D9%82%D8%AF%D8%B3%20-%20like%20_%20subscribe%20_%20comment(360P).mp4?alt=media&token=31b2b7b4-bda8-4aa2-b877-85fba75d8038";
    String uri3 ="https://firebasestorage.googleapis.com/v0/b/hind-dc029.appspot.com/o/VID-20210531-WA0000.mp4?alt=media&token=91c0d802-dd89-4d69-b6f0-9d5bcbd3b8a4";
    String uri4 ="https://firebasestorage.googleapis.com/v0/b/hind-dc029.appspot.com/o/VID-20210530-WA0004.mp4?alt=media&token=6e8d8480-abf5-42de-83ab-682eab6ee119";

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
        View root = inflater.inflate(R.layout.fragment_watch, container, false);

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
        playerView1.onPause();

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