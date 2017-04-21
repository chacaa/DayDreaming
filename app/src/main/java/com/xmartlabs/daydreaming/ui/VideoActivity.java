package com.xmartlabs.daydreaming.ui;

import android.support.annotation.NonNull;

import com.f2prateek.dart.HensonNavigable;

/**
 * Created by chaca on 4/17/17.
 */
@HensonNavigable
public class VideoActivity extends SingleFragmentActivity {
  @NonNull
  @Override
  protected BaseFragment createFragment() {
    return new VideoFragmentBuilder().build();
  }
}
