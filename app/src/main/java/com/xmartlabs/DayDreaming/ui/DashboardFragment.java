package com.xmartlabs.DayDreaming.ui;

import android.os.Bundle;
import android.support.annotation.Dimension;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.DayDreaming.R;
import com.xmartlabs.DayDreaming.helper.ui.MetricsHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by scasas on 3/31/17.
 */
@FragmentWithArgs
public class DashboardFragment extends BaseFragment {
  @Dimension(unit = Dimension.PX)
  private static final int BLACK_LINE_HEIGHT = MetricsHelper.dpToPxInt(10);
  private static final int QUANTITY_OF_OPTIONS = 3; //TODO change it for a list

  @BindView(R.id.bottom_black_diagonal_separator_view)
  DiagonalLayoutView bottomBlackView;
  @BindView(R.id.custom_dashboard_option_view)
  DiagonalLayoutView customOptionView;
  @BindView(R.id.first_black_line_separator_view)
  DiagonalLayoutView firstBlackLineView;
  @BindView(R.id.random_dashboard_option_view)
  DiagonalLayoutView randomOptionView;
  @BindView(R.id.second_black_line_separator_view)
  DiagonalLayoutView secondBlackLineView;
  @BindView(R.id.trending_dashboard_option_view)
  DiagonalLayoutView trendingOptionView;

  @LayoutRes
  @Override
  protected int getLayoutResId() {
    return R.layout.dashboard_fragment;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setUpView(view);
  }

  @OnClick(R.id.custom_dashboard_option_view)
  void onClickedCustom() {
    customOptionView.setTextColor(R.color.white);
    //TODO call to custom view
  }

  @OnClick(R.id.trending_dashboard_option_view)
  void onClickedTrending() {
    trendingOptionView.setTextColor(R.color.white);
    //TODO call custom view
  }

  @OnClick(R.id.random_dashboard_option_view)
  void onClickedRandom() {
    randomOptionView.setTextColor(R.color.white);
    //TODO call random view
  }

  private void setUpView(View view) {
    view.post(() -> {
      @Dimension(unit = Dimension.PX)
      int height = view.getMeasuredHeight() - (2 * BLACK_LINE_HEIGHT);
      @Dimension(unit = Dimension.PX)
      int margin = (int) (view.getMeasuredWidth() * Math.tan(Math.toRadians(7)));
      @Dimension(unit = Dimension.PX)
      int optionHeight = ((height + margin * 2) / QUANTITY_OF_OPTIONS);

      setUpDiagonalLayoutView(customOptionView, optionHeight, 0);
      setUpDiagonalLayoutView(firstBlackLineView, optionHeight, margin);
      setUpDiagonalLayoutView(trendingOptionView, optionHeight, optionHeight - BLACK_LINE_HEIGHT);
      setUpDiagonalLayoutView(secondBlackLineView, optionHeight, margin);
      setUpDiagonalLayoutView(randomOptionView, optionHeight, optionHeight - BLACK_LINE_HEIGHT);
      setUpDiagonalLayoutView(bottomBlackView, optionHeight, margin);
    });
  }

  private void setUpDiagonalLayoutView(DiagonalLayoutView diagonalLayout, int optionHeightInPx, int marginTopInPx) {
    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) diagonalLayout.getLayoutParams();
    layoutParams.setMargins(0, -marginTopInPx, 0, 0);
    layoutParams.height = optionHeightInPx;
    diagonalLayout.setLayoutParams(layoutParams);
  }

  //TODO change the animation. It will be used later.
//  private void setupAnimator(DiagonalLayout diagonalLayout, int duration) {
//    final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) diagonalLayout.getLayoutParams();
//    ValueAnimator animator = ValueAnimator.ofInt(params.bottomMargin, 25);//TODO calculate the value dynamically
//    animator.addUpdateListener(valueAnimator -> {
//      params.bottomMargin = (Integer) valueAnimator.getAnimatedValue();
//      diagonalLayout.requestLayout();
//    });
//    animator.setDuration(duration);
//    animator.start();
//  }
}
