package com.xmartlabs.daydreaming.ui;

import android.support.annotation.NonNull;

import com.f2prateek.dart.HensonNavigable;

/**
 * Created by chaca on 4/12/17.
 */
@HensonNavigable
public class LoginRegisterActivity extends SingleFragmentActivity {
  @NonNull
  @Override
  protected BaseFragment createFragment() {
    return new LoginRegisterFragmentBuilder().build();
  }
}
