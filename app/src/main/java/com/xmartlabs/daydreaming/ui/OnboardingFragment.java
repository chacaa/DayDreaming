package com.xmartlabs.daydreaming.ui;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.daydreaming.R;

import butterknife.BindView;

/**
 * Created by scasas on 4/5/17.
 */
@FragmentWithArgs
public class OnboardingFragment extends BaseFragment {
  @BindView(R.id.background_video_view)
  VideoView backgroundVideoView;
  @BindView(R.id.splash_view)
  RelativeLayout splashView;

  @LayoutRes
  @Override
  protected int getLayoutResId() {
    return R.layout.onboarding_fragment;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.daydreamingvideo);
    backgroundVideoView.setVideoURI(uri);
    backgroundVideoView.start();

    backgroundVideoView.setOnPreparedListener(mediaPlayer -> mediaPlayer.setLooping(true));
    setupAnimation();
  }

  private void setupAnimation() {
//    Animation animation = new AlphaAnimation(0.001f, 1.0f);
//    animation.setDuration(5000);
//    animation.setInterpolator(new LinearInterpolator());
//    backgroundVideoView.startAnimation(animation);

    splashView.animate()
        .setDuration(1000)
        .alpha(0.001f)
        .setStartDelay(1000)
        .start();
  }
}
