package com.xmartlabs.daydreaming.ui;

import android.support.annotation.NonNull;

import com.f2prateek.dart.HensonNavigable;

/**
 * Created by chaca on 4/13/17.
 */
@HensonNavigable
public class TrendingScreenActivity extends SingleFragmentActivity {
  @NonNull
  @Override
  protected BaseFragment createFragment() {
    return new TrendingScreenFragmentBuilder().build();
  }
}
