package com.xmartlabs.daydreaming.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.annimon.stream.Optional;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.daydreaming.R;

import butterknife.BindView;

/**
 * Created by santiago on 4/10/17.
 */
@FragmentWithArgs
public class AboutScreenFragment extends BaseFragment {
  @BindView(R.id.toolbar)
  Toolbar toolbarView;

  @LayoutRes
  @Override
  protected int getLayoutResId() {
    return R.layout.about_fragment;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupToolbar();
  }

  private void setupToolbar() {
    AppCompatActivity activity = (AppCompatActivity) getActivity();
    activity.setSupportActionBar(toolbarView);
    Optional.ofNullable(activity.getSupportActionBar())
        .ifPresent(actionBar -> {
          actionBar.setDisplayHomeAsUpEnabled(true);
          actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        });
  }
}
