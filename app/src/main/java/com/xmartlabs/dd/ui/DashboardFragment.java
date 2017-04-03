package com.xmartlabs.dd.ui;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.diagonallayout.DiagonalLayout;
import com.xmartlabs.dd.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by scasas on 3/31/17.
 */
@SuppressWarnings("deprecation")
public class DashboardFragment extends BaseFragment {
  private static final int ANIMATION_DURATION = 300;
  private static final int QUANTITY_OF_OPTIONS = 3;

  @BindView(R.id.bottom_black)
  DiagonalLayout bottomBlackView;
  @BindView(R.id.custom_image)
  ImageView customImageView;
  @BindView(R.id.custom_text_view)
  TextView customTextView;
  @BindView(R.id.home_custom)
  DiagonalLayout customView;
  @BindView(R.id.random_image)
  ImageView randomImageView;
  @BindView(R.id.random_text_view)
  TextView randomTextView;
  @BindView(R.id.home_random)
  DiagonalLayout randomView;
  @BindView(R.id.trending_image)
  ImageView trendingImageView;
  @BindView(R.id.trending_text_view)
  TextView trendingTextView;
  @BindView(R.id.home_trending)
  DiagonalLayout trendingView;

  @Override
  protected int getLayoutResId() {
    return R.layout.dashboard_fragment;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setUpView(view);
  }

  @OnClick(R.id.home_custom)
  void onClickedCustom() {
    customTextView.setTextColor(getResources().getColor(R.color.white));
    setupAnimator(customView, ANIMATION_DURATION);
    //TODO call to custom view
  }

  @OnClick(R.id.home_trending)
  void onClickedTrending() {
    trendingTextView.setTextColor(getResources().getColor(R.color.white));
    setupAnimator(trendingView, ANIMATION_DURATION);
    //TODO call custom view
  }

  @OnClick(R.id.home_random)
  void onClickedRandom() {
    randomTextView.setTextColor(getResources().getColor(R.color.white));
    setupAnimator(randomView, ANIMATION_DURATION);
    //TODO call random view
  }

  private void setUpView(View view) {
    view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    float heightInPx = view.getMeasuredHeight();
    int optionHeightInPx = (int) heightInPx / QUANTITY_OF_OPTIONS;
    int marginTopInPx = optionHeightInPx / 4;

    //TODO calculate margintop = width * tan(7)
    //int marginTopInPx = (int) (view.getMeasuredWidth() * Math.tan(7));

    setUpDiagonalLayoutView(customView, optionHeightInPx, 0);
    setUpDiagonalLayoutView(trendingView, optionHeightInPx, marginTopInPx);
    setUpDiagonalLayoutView(randomView, optionHeightInPx, marginTopInPx);
    setUpDiagonalLayoutView(bottomBlackView, optionHeightInPx, marginTopInPx);
  }

  private void setUpDiagonalLayoutView(DiagonalLayout diagonalLayout, int optionHeightInPx, int marginTopInPx) {
    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) diagonalLayout.getLayoutParams();
    layoutParams.setMargins(0, (-1) * marginTopInPx, 0, 0);
    layoutParams.height = optionHeightInPx;
    diagonalLayout.setLayoutParams(layoutParams);
  }

  private void setupAnimator(DiagonalLayout diagonalLayout, int duration) {
    final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) diagonalLayout.getLayoutParams();
    ValueAnimator animator = ValueAnimator.ofInt(params.bottomMargin, 25);//TODO calculate the value dynamically
    animator.addUpdateListener(valueAnimator -> {
      params.bottomMargin = (Integer) valueAnimator.getAnimatedValue();
      diagonalLayout.requestLayout();
    });
    animator.setDuration(duration);
    animator.start();
  }
}
