package com.xmartlabs.daydreaming.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.annimon.stream.Optional;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.daydreaming.R;
import com.xmartlabs.daydreaming.controller.VideoController;
import com.xmartlabs.daydreaming.model.Video;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Created by chaca on 4/18/17.
 */
@FragmentWithArgs
public class VideoFragment extends BaseFragment {
  private static final int DELAY_IN_MS = 100;
  private static final float INVISIBLE = 0.0001f;

  @Inject
  VideoController videoController;

  @Arg
  @DrawableRes
  int image;
  @Arg(required = false)
  String theme;
  @Arg(required = false)
  String type;

  @BindView(R.id.mute_button)
  ImageView muteButtonView;
  @BindView(R.id.image_while_waiting)
  ImageView imageWhileWaitingView;
  @BindView(R.id.play_pause_button)
  ImageView playPauseButtonView;
  @BindView(R.id.text_while_waiting)
  TextView textView;
  @BindView(R.id.toolbar)
  Toolbar toolbarView;
  @BindView(R.id.video_control_view)
  RelativeLayout videoControllersView;
  @BindView(R.id.video_player_view)
  VideoView videoPlayerView;
  @BindView(R.id.video_progress_bar)
  AppCompatSeekBar videoProgressSeekBarView;
  @BindView(R.id.while_waiting_view)
  RelativeLayout whileWaitingView;

  private final Handler handler = new Handler();
  private boolean nextButtonWasClicked = false;
  private boolean prevButtonWasClicked = false;
  private final Runnable updateTimeTask = new Runnable() {
    public void run() {
      if (isAdded()) {
        videoProgressSeekBarView.setProgress(videoPlayerView.getCurrentPosition());
        videoProgressSeekBarView.setMax(videoPlayerView.getDuration());
        handler.postDelayed(this, DELAY_IN_MS);
      }
    }
  };
  private List<Video> videos = Collections.emptyList();
  private int volume;
  private Video video;
  private int currentIndex;

  @LayoutRes
  @Override
  protected int getLayoutResId() {
    return R.layout.video_layout;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupToolbar();
    getVideos();
    setupVolumeImage();
    setTextFont();
    setupWhileWaitingImage();
  }

  private void setupWhileWaitingImage() {
    //noinspection deprecation
    imageWhileWaitingView.setImageDrawable(getResources().getDrawable(image));
  }

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
    currentIndex++;
    nextButtonWasClicked = true;
    playVideo(getVideoIndex());
  }

  @OnClick(R.id.prev_button)
  void onClickedPrevButton() {
    currentIndex--;
    prevButtonWasClicked = true;
    playVideo(getVideoIndex());
  }

  private int getVideoIndex() {
    int index;
    if (nextButtonWasClicked) {
      index = currentIndex > videos.size() - 1 ? 0 : currentIndex;
    } else {
      index = currentIndex < 0 ? videos.size() - 1 : currentIndex;
    }
    setNextAndPrevWasClickedAsFalse();
    return index;
  }

  private void setNextAndPrevWasClickedAsFalse() {
    nextButtonWasClicked = false;
    prevButtonWasClicked = false;
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
    Uri uri = Uri.parse(video.getId());
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

  private void unmute(@NonNull AudioManager audioManager) {
    setVolume(audioManager, volume == 0 ? 100 : volume);
    //noinspection deprecation
    muteButtonView.setImageDrawable(getResources().getDrawable(R.drawable.sound_off));
  }

  private void mute(@NonNull AudioManager audioManager) {
    volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    setVolume(audioManager, 0);
    //noinspection deprecation
    muteButtonView.setImageDrawable(getResources().getDrawable(R.drawable.sound_on));
  }

  private void setVolumeButtonImage() {
    //noinspection deprecation
    muteButtonView.setImageDrawable(getResources().getDrawable(volume > 0 ? R.drawable.sound_off : R.drawable.sound_on));
  }

  private void setVolume(@NonNull AudioManager audioManager, int volume) {
    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
  }

  private void getVideos() {
    videoController.getVideos(theme, type).subscribe(new SingleObserver<List<Video>>() {
      @Override
      public void onSubscribe(Disposable d) {
      }

      @Override
      public void onSuccess(List<Video> videosList) {
        videos = videosList;
        animateView(whileWaitingView, 2000, 500, INVISIBLE);
        playVideo(0);
      }

      @Override
      public void onError(Throwable e) {
        Timber.e(e.toString());
      }
    });
  }

  private void playVideo(int index) {
    currentIndex = index;
    video = videos.get(index);
    setupVideoPlayer();
  }

  @Override
  public void onResume() {
    super.onResume();
    if (!videos.isEmpty()) {
      playVideo(currentIndex);
    }
  }

  private void animateView(@NonNull View view, long durationInMs, long delayInMs, float alpha) {
    view.animate()
        .setDuration(durationInMs)
        .alpha(alpha)
        .setStartDelay(delayInMs)
        .start();
  }

  private void setTextFont() {
    Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "LibreBaskerville-Italic.otf");
    textView.setTypeface(font);
  }
}
