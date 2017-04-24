package com.xmartlabs.daydreaming.ui;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.f2prateek.dart.HensonNavigable;
import com.f2prateek.dart.InjectExtra;

/**
 * Created by chaca on 4/17/17.
 */
public class VideoActivity extends SingleFragmentActivity {
  @DrawableRes
  @NonNull
  @InjectExtra
  int image;
  @Nullable
  @InjectExtra
  String theme;
  @Nullable
  @InjectExtra
  String type;

  @NonNull
  @Override
  protected BaseFragment createFragment() {
    return new VideoFragmentBuilder(image)
        .theme(theme)
        .type(type)
        .build();
  }
}
