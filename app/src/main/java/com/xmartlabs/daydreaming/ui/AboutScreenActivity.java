package com.xmartlabs.daydreaming.ui;

import android.support.annotation.NonNull;

import com.f2prateek.dart.HensonNavigable;

/**
 * Created by santiago on 4/10/17.
 */
@HensonNavigable
public class AboutScreenActivity extends SingleFragmentActivity {
  @NonNull
  @Override
  protected BaseFragment createFragment() {
    return new AboutScreenFragmentBuilder().build();
  }
}
