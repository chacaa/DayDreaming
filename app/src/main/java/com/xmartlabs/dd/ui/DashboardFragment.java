package com.xmartlabs.dd.ui;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.diagonallayout.DiagonalLayout;
import com.xmartlabs.dd.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by scasas on 3/31/17.
 */
@SuppressWarnings("deprecation")
public class DashboardFragment extends BaseFragment {
  @BindView(R.id.home_custom)
  DiagonalLayout customView;
  @BindView(R.id.home_trending)
  DiagonalLayout trendingView;
  @BindView(R.id.home_random)
  DiagonalLayout randomView;
  @BindView(R.id.custom_text_view)
  TextView customTextView;
  @BindView(R.id.trending_text_view)
  TextView trendingTextView;
  @BindView(R.id.random_text_view)
  TextView randomTextView;

  @Override
  protected int getLayoutResId() {
    return R.layout.dashboard_fragment;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

  }

  @OnClick(R.id.home_custom)
  void onClickedCustom() {
    customTextView.setTextColor(getResources().getColor(R.color.white));
    trendingTextView.setTextColor(getResources().getColor(R.color.warm_grey));
    randomTextView.setTextColor(getResources().getColor(R.color.warm_grey));
  }

  @OnClick(R.id.home_trending)
  void onClickedTrending() {
    customTextView.setTextColor(getResources().getColor(R.color.warm_grey));
    trendingTextView.setTextColor(getResources().getColor(R.color.white));
    randomTextView.setTextColor(getResources().getColor(R.color.warm_grey));

    final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) trendingView.getLayoutParams();
    ValueAnimator animator = ValueAnimator.ofInt(params.bottomMargin, 30);
    animator.addUpdateListener(valueAnimator -> {
      params.bottomMargin = (Integer) valueAnimator.getAnimatedValue();
      trendingView.requestLayout();
    });
    animator.setDuration(300);
    animator.start();
  }

  @OnClick(R.id.home_random)
  void onClickedRandom() {
    customTextView.setTextColor(getResources().getColor(R.color.warm_grey));
    trendingTextView.setTextColor(getResources().getColor(R.color.warm_grey));
    randomTextView.setTextColor(getResources().getColor(R.color.white));
  }
}
