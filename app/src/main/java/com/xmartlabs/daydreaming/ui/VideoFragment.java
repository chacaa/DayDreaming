package com.xmartlabs.daydreaming.ui;

import android.content.Context;
import android.media.AudioManager;
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
  public static final String VIDEO_ID = "https://redirector.googlevideo.com/videoplayback?initc" +
      "wndbps=5718750&mm=31&mn=sn-aiglln67&ip=78.157.200.133&key=yt6&source=youtube&pl=19&dur=1" +
      "31.378&mt=1492635569&mv=m&id=o-AMGU-yxiAZX1GAzjx7AFD1ouRDrxjHLXdpbszmGfPYVI&ei=IdD3WM3kM" +
      "8Sy4gLU4KvAAw&ms=au&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmim" +
      "e%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&lmt=1472444" +
      "834468162&itag=22&upn=DrHcr_zxS3I&ipbits=0&ratebypass=yes&mime=video%2Fmp4&requiressl=ye" +
      "s&expire=1492657282&signature=9896E9C95C932848C935E7E1144FA8061F50ABCC.CAB9448BA220B33F5" +
      "44CE18D1915A9411E5C290A";

  @BindView(R.id.mute_button)
  ImageView muteButtonView;
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
    toolbarView.collapseActionView();
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

  @OnClick(R.id.mute_button)
  void onClickedMuteButton() {
    AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
    if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) > 0) {
      audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
      //noinspection deprecation
      muteButtonView.setImageDrawable(getResources().getDrawable(R.drawable.sound_on));
    } else {
      audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 100, 0);
      //noinspection deprecation
      muteButtonView.setImageDrawable(getResources().getDrawable(R.drawable.sound_off));
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
