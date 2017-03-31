package com.xmartlabs.dd.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.florent37.diagonallayout.DiagonalLayout;
import com.xmartlabs.dd.R;

import butterknife.BindView;

/**
 * Created by scasas on 3/31/17.
 */
public class DashboardFragment extends BaseFragment {
  @BindView(R.id.home_custom)
  DiagonalLayout customView;
  @BindView(R.id.home_trending)
  DiagonalLayout trendingView;
  @BindView(R.id.home_random)
  DiagonalLayout randomView;

  @Override
  protected int getLayoutResId() {
    return R.layout.dashboard_fragment;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
}
