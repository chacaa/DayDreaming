package com.xmartlabs.daydreaming.ui;

import android.content.Context;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
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
  public static final int DELAY_IN_MS = 100;
  //TODO receive this from a service and remove it from here
  public static final String VIDEO_ID = "https://redirector.googlevideo.com/videoplayback?lmt=1471578643331692&spara" +
      "ms=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequ" +
      "iressl%2Csource%2Cupn%2Cexpire&ipbits=0&requiressl=yes&initcwndbps=892500&source=youtube&ei=uXX5WMyrHMW24wKA9" +
      "JXAAg&dur=161.100&ratebypass=yes&key=yt6&mime=video%2Fmp4&expire=1492765209&mn=sn-a5m7lnez&ip=2001%3A19f0%3A7" +
      "001%3Ad32%3A5400%3Aff%3Afe58%3A19e7&mm=31&itag=22&pl=48&signature=BDF0A6A6CD5DAA072864E620C6B56B0EA71C46F1.84" +
      "ACFAAFAA22A096AFE23CBC1CE2237356108E0D&id=o-ANaqAFXmy3ap61faSAI3Hwu_AtyqNn6g6QzodksIpZ6d&mv=m&upn=2zjhaDBAdoY" +
      "&mt=1492743503&ms=au";

  @BindView(R.id.mute_button)
  ImageView muteButtonView;
  @BindView(R.id.play_pause_button)
  ImageView playPauseButtonView;
  @BindView(R.id.video_progress_bar)
  AppCompatSeekBar videoProgressSeekBarView;
  @BindView(R.id.toolbar)
  Toolbar toolbarView;
  @BindView(R.id.video_control_view)
  RelativeLayout videoControllersView;
  @BindView(R.id.video_player_view)
  VideoView videoPlayerView;

  private final Handler handler = new Handler();
  private final Runnable updateTimeTask = new Runnable() {
    public void run() {
      if (isAdded()) {
        videoProgressSeekBarView.setProgress(videoPlayerView.getCurrentPosition());
        videoProgressSeekBarView.setMax(videoPlayerView.getDuration());
        handler.postDelayed(this, DELAY_IN_MS);
      }
    }
  };
  private int volume;

  @LayoutRes
  @Override
  protected int getLayoutResId() {
    return R.layout.video_layout;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupToolbar();
    setupVideoPlayer();
    setupVolumeImage();
  }

//TODO see if i can make the toolbar hide and show by touching the screen, it is working sometimes but needs to be fix
//  @BindView(R.id.main_appbar)
//  AppBarLayout appBarLayout;
//
//  private boolean isHidden = true;
//
//  @OnTouch(R.id.video_player_view)
//  boolean onTouchedScreen() {
//    AppCompatActivity activity = (AppCompatActivity) getActivity();
//    if (isHidden) {
//      Optional.ofNullable(activity.getSupportActionBar())
//          .ifPresent(ActionBar::show);
//      appBarLayout.setVisibility(View.VISIBLE);
//    } else {
//      Optional.ofNullable(activity.getSupportActionBar())
//          .ifPresent(ActionBar::hide);
//      appBarLayout.setVisibility(View.GONE);
//    }
//    isHidden = !isHidden;
//    return true;
//  }

  @OnClick(R.id.play_pause_button)
  void onClickedPauseButton() {
    if (videoPlayerView.isPlaying()) {
      pause();
    } else {
      play();
    }
  }

  private void play() {
    videoPlayerView.start();
    setPlayPauseButtonImage(R.drawable.pause);
  }

  private void pause() {
    videoPlayerView.pause();
    setPlayPauseButtonImage(R.drawable.play);
  }

  @OnClick(R.id.mute_button)
  void onClickedMuteButton() {
    AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
    if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) > 0) {
      mute(audioManager);
    } else {
      unmute(audioManager);
    }
  }

  @OnClick(R.id.next_button)
  void onClickedNextButton() {
    //TODO go to next video
  }

  @OnClick(R.id.prev_button)
  void onClickedPrevButton() {
    //TODO go to prev video
  }

  private void setPlayPauseButtonImage(@DrawableRes int image) {
    //noinspection deprecation
    playPauseButtonView.setImageDrawable(getResources().getDrawable(image));
  }

  private void setupVideoPlayer() {
    setupVideo();
    setupVideoProgressBar();
  }

  private void setupVideo() {
    Uri uri = Uri.parse(VIDEO_ID);
    videoPlayerView.setVideoURI(uri);
    videoPlayerView.start();
    videoPlayerView.setOnPreparedListener(mediaPlayer -> mediaPlayer.setLooping(true));
  }

  private void setupVideoProgressBar() {
    updateProgressBar();
    videoProgressSeekBarView.setOnSeekBarChangeListener(
        new SeekBar.OnSeekBarChangeListener() {
          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {
          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {
          }

          @Override
          public void onProgressChanged(SeekBar seekBar, int progress,
                                        boolean fromUser) {
            if (fromUser) {
              videoPlayerView.seekTo(seekBar.getProgress());
            }
          }
        });
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

  private void updateProgressBar() {
    handler.postDelayed(updateTimeTask, 100);
  }

  private void setupVolumeImage() {
    AudioManager audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
    volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    setVolumeButtonImage();
  }

  private void unmute(AudioManager audioManager) {
    setVolume(audioManager, volume == 0 ? 100 : volume);
    //noinspection deprecation
    muteButtonView.setImageDrawable(getResources().getDrawable(R.drawable.sound_off));
  }

  private void mute(AudioManager audioManager) {
    volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    setVolume(audioManager, 0);
    //noinspection deprecation
    muteButtonView.setImageDrawable(getResources().getDrawable(R.drawable.sound_on));
  }

  private void setVolumeButtonImage() {
    //noinspection deprecation
    muteButtonView.setImageDrawable(getResources().getDrawable(volume > 0 ? R.drawable.sound_off : R.drawable.sound_on));
  }

  private void setVolume(AudioManager audioManager, int volume) {
    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
  }
}
