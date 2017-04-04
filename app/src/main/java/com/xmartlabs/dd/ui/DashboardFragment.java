package com.xmartlabs.dd.ui;

import android.os.Bundle;
import android.support.annotation.Dimension;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.dd.R;
import com.xmartlabs.dd.helper.ui.MetricsHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by scasas on 3/31/17.
 */
@FragmentWithArgs
public class DashboardFragment extends BaseFragment {
  private static final int BLACK_LINE_HEIGHT_IN_PX = MetricsHelper.dpToPxInt(10);
  private static final int QUANTITY_OF_OPTIONS = 3;

  @BindView(R.id.bottom_black)
  DiagonalLayoutView bottomBlackView;
  @BindView(R.id.custom)
  DiagonalLayoutView customView;
  @BindView(R.id.first_black)
  DiagonalLayoutView firstBlackLineView;
  @BindView(R.id.menu)
  LinearLayout menuView;
  @BindView(R.id.random)
  DiagonalLayoutView randomView;
  @BindView(R.id.second_black)
  DiagonalLayoutView secondBlackLineView;
  @BindView(R.id.trending)
  DiagonalLayoutView trendingView;

  @Override
  protected int getLayoutResId() {
    return R.layout.dashboard_fragment;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setUpView(view);
  }

  @OnClick(R.id.custom)
  void onClickedCustom() {
    customView.setTextColor(R.color.white);
    //TODO call to custom view
  }

  @OnClick(R.id.trending)
  void onClickedTrending() {
    trendingView.setTextColor(R.color.white);
    //TODO call custom view
  }

  @OnClick(R.id.random)
  void onClickedRandom() {
    randomView.setTextColor(R.color.white);
    //TODO call random view
  }

  private void setUpView(View view) {
    view.post(() -> {
      @Dimension(unit = Dimension.PX)
      int heightInPx = view.getMeasuredHeight() - (2 * BLACK_LINE_HEIGHT_IN_PX);
      @Dimension(unit = Dimension.PX)
      int marginTopInPx = (int) (view.getMeasuredWidth() * Math.tan(Math.toRadians(7)));
      @Dimension(unit = Dimension.PX)
      int optionHeightInPx = ((heightInPx + marginTopInPx * 2) / QUANTITY_OF_OPTIONS);

      setUpDiagonalLayoutView(customView, optionHeightInPx, 0);
      setUpDiagonalLayoutView(firstBlackLineView, optionHeightInPx, marginTopInPx);
      setUpDiagonalLayoutView(trendingView, optionHeightInPx, optionHeightInPx - BLACK_LINE_HEIGHT_IN_PX);
      setUpDiagonalLayoutView(secondBlackLineView, optionHeightInPx, marginTopInPx);
      setUpDiagonalLayoutView(randomView, optionHeightInPx, optionHeightInPx - BLACK_LINE_HEIGHT_IN_PX);
      setUpDiagonalLayoutView(bottomBlackView, optionHeightInPx, marginTopInPx);
    });
  }

  private void setUpDiagonalLayoutView(DiagonalLayoutView diagonalLayout, int optionHeightInPx, int marginTopInPx) {
    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) diagonalLayout.getLayoutParams();
    layoutParams.setMargins(0, - marginTopInPx, 0, 0);
    layoutParams.height = optionHeightInPx;
    diagonalLayout.setLayoutParams(layoutParams);
  }

  //TODO change the animation, i left this here to use it later
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
