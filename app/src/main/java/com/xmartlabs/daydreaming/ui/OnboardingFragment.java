package com.xmartlabs.daydreaming.ui;

import android.content.Intent;
import android.graphics.Typeface;
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

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by scasas on 4/5/17.
 */
@FragmentWithArgs
public class OnboardingFragment extends BaseFragment {
  public static final float INVISIBLE = 0.0001f;
  public static final float VISIBLE = 1.0f;

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
  @BindView(R.id.text_in_the_shapes)
  TextView textView;

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
    setTextFont();
  }

  private void setTextFont() {
    Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "LibreBaskerville-Italic.otf");
    textView.setTypeface(font);
  }

  @OnClick(R.id.start_dreaming_view)
  void onClickedStartDayDreaming() {
    Intent intent = Henson.with(getContext())
        .gotoDashboardActivity()
        .build();
    getContext().startActivity(intent);
  }

  private void setupBackgroundVideo() {
    String uriString = String.format(Locale.getDefault(),
        "android.resource://%s/%d", getActivity().getPackageName(), R.raw.daydreamingvideo);
    Uri uri = Uri.parse(uriString);
    backgroundVideoView.setVideoURI(uri);
    backgroundVideoView.start();
    backgroundVideoView.setOnPreparedListener(mediaPlayer -> mediaPlayer.setLooping(true));
  }

  private void setupAnimation() {
    animateView(splashView, 2000, 1000, INVISIBLE);
    animateView(startDreamingView, 2000, 4000, VISIBLE);
    animateView(daydreamingImageView, 1000, 2000, INVISIBLE);
    animateView(squareShapeView, 2000, 3000, VISIBLE);
    animateView(textView, 2000, 3000, VISIBLE);
  }

  private void initializeAlphaValues() {
    startDreamingView.setAlpha(INVISIBLE);
    squareShapeView.setAlpha(INVISIBLE);
    textView.setAlpha(INVISIBLE);
  }

  private void animateView(View view, long durationInMs, long delayInMs, float alpha) {
    view.animate()
        .setDuration(durationInMs)
        .alpha(alpha)
        .setStartDelay(delayInMs)
        .start();
  }

  @Override
  public void onResume() {
    super.onResume();
    setupBackgroundVideo();
  }
}
