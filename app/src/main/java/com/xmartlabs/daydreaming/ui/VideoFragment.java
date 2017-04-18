package com.xmartlabs.daydreaming.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.annimon.stream.Optional;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.daydreaming.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chaca on 4/18/17.
 */
@FragmentWithArgs
public class VideoFragment extends BaseFragment {
  //TODO receive this from a service and remove it from here
  public static final String VIDEO_ID = "https://redirector.googlevideo.com/videoplayback?ei=L2f" +
      "2WPiyHq7i8gTcxa7QAw&source=youtube&upn=dFlbb6tHzTs&initcwndbps=2707500&lmt=1472444834468162&pl=" +
      "38&id=o-AOJlsTerGTKSrR76KSdx4ZGeFwTVROpPbZcLnATiSYUa&mn=sn-ab5l6nzd&mm=31&ms=au&mv=m&sparams" +
      "=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Crat" +
      "ebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&mt=1492543166&requiressl=yes&ip=2001%3A19f0%3A" +
      "5%3A1de%3A5400%3Aff%3Afe4f%3A2207&expire=1492564879&ratebypass=yes&dur=131.378&ipbits=0&it" +
      "ag=22&key=yt6&mime=video%2Fmp4&signature=CD75E53EE1ACEBB9D102BD44C0C2E3914661D285.019BD" +
      "5695672D9F38C25666F73427EFC786901FE";

  @BindView(R.id.toolbar)
  Toolbar toolbarView;
  @BindView(R.id.video_player_view)
  VideoView videoPlayerView;
  @BindView(R.id.video_control_view)
  RelativeLayout videoControllersView;
  @BindView(R.id.play_pause_button)
  ImageView playPauseButtonView;

  @LayoutRes
  @Override
  protected int getLayoutResId() {
    return R.layout.video_layout;
  }

  @NonNull
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);
    setupVideoPlayer();
    setupToolbar();
    return view;
  }

  @OnClick(R.id.close_controllers)
  void onClickedCloseButton() {
    videoControllersView.setVisibility(View.GONE);
  }

  @OnClick(R.id.play_pause_button)
  void onClickedPauseButton() {
    if (videoPlayerView.isPlaying()) {
      videoPlayerView.pause();
      setPlayPauseButtonImage(R.drawable.play);
    } else {
      videoPlayerView.start();
      setPlayPauseButtonImage(R.drawable.pause);
    }
  }

  private void setPlayPauseButtonImage(@DrawableRes int image) {
    //noinspection deprecation
    playPauseButtonView.setImageDrawable(getResources().getDrawable(image));
  }

  private void setupVideoPlayer() {
    Uri uri = Uri.parse(VIDEO_ID);
    videoPlayerView.setVideoURI(uri);
    videoPlayerView.start();
    videoPlayerView.setOnPreparedListener(mediaPlayer -> mediaPlayer.setLooping(true));
  }

  private void setupToolbar() {
    AppCompatActivity activity = (AppCompatActivity) getActivity();
    activity.setSupportActionBar(toolbarView);
    Optional.ofNullable(activity.getSupportActionBar())
        .ifPresent(actionBar -> {
          actionBar.setDisplayHomeAsUpEnabled(true);
          actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
          actionBar.setDisplayShowTitleEnabled(false);
        });
  }
}
