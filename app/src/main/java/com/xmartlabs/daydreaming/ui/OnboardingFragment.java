package com.xmartlabs.daydreaming.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.daydreaming.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by scasas on 4/5/17.
 */
@FragmentWithArgs
public class OnboardingFragment extends BaseFragment {
  @BindView(R.id.background_video_view)
  VideoView backgroundVideoView;
  @BindView(R.id.daydreaming_image_view)
  ImageView daydreamingImageView;
  @BindView(R.id.splash_view)
  RelativeLayout splashView;
  @BindView(R.id.square_shape_view)
  View squareShapeView;
  @BindView(R.id.start_dreaming_view)
  TextView startDreamingView;

  @LayoutRes
  @Override
  protected int getLayoutResId() {
    return R.layout.onboarding_fragment;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initializeAlphaValues();
    setupBackgroundVideo();
    setupAnimation();
  }

  @OnClick(R.id.start_dreaming_view)
  void onClickedStartDayDreaming() {
    Intent intent = Henson.with(getContext())
        .gotoDashboardActivity()
        .build()
        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    getContext().startActivity(intent);
  }

  private void setupBackgroundVideo() {
    String uriString = String.format("android.resource://%s/%d", getActivity().getPackageName(), R.raw.daydreamingvideo);
    Uri uri = Uri.parse(uriString);
    backgroundVideoView.setVideoURI(uri);
    backgroundVideoView.start();
    backgroundVideoView.setOnPreparedListener(mediaPlayer -> mediaPlayer.setLooping(true));
  }

  private void setupAnimation() {
    animateView(splashView, 2000, 1000, 0.0001f);
    animateView(startDreamingView, 2000, 4000, 1.0f);
    animateView(daydreamingImageView, 1000, 2000, 0.0001f);
    animateView(squareShapeView, 2000, 3000, 1.0f);
  }

  private void initializeAlphaValues() {
    startDreamingView.setAlpha(0.0001f);
    squareShapeView.setAlpha(0.0001f);
  }

  private void animateView(View view, long duration, long delay, float alpha) {
    view.animate()
        .setDuration(duration)
        .alpha(alpha)
        .setStartDelay(delay)
        .start();
  }
}
