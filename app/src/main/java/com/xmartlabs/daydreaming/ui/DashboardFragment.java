package com.xmartlabs.daydreaming.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Dimension;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.annimon.stream.Optional;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.daydreaming.R;
import com.xmartlabs.daydreaming.helper.ui.MetricsHelper;

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
  @BindView(R.id.dashboard_view)
  LinearLayout dashboardView;
  @BindView(R.id.drawer_layout)
  DrawerLayout drawerView;
  @BindView(R.id.first_black_line_separator_view)
  DiagonalLayoutView firstBlackLineView;
  @BindView(R.id.nav_view)
  NavigationView navigationView;
  @BindView(R.id.random_dashboard_option_view)
  DiagonalLayoutView randomOptionView;
  @BindView(R.id.second_black_line_separator_view)
  DiagonalLayoutView secondBlackLineView;
  @BindView(R.id.toolbar)
  Toolbar toolbarView;
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
    setupToolbar();
  }

  @OnClick(R.id.custom_dashboard_option_view)
  void onClickedCustom() {
    customOptionView.setTextColor(R.color.white);
    Intent intent = Henson.with(getContext())
        .gotoCustomScreenActivity()
        .build();
    getContext().startActivity(intent);
  }

  @OnClick(R.id.trending_dashboard_option_view)
  void onClickedTrending() {
    trendingOptionView.setTextColor(R.color.white);
    //TODO call trending view
  }

  @OnClick(R.id.random_dashboard_option_view)
  void onClickedRandom() {
    randomOptionView.setTextColor(R.color.white);
    //TODO call random view
  }

  private void setUpView(View view) {
    view.post(() -> {
      @Dimension(unit = Dimension.PX)
      int height = dashboardView.getMeasuredHeight() - (2 * BLACK_LINE_HEIGHT);
      @Dimension(unit = Dimension.PX)
      int margin = (int) (dashboardView.getMeasuredWidth() * Math.tan(Math.toRadians(7)));
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

  private void setUpDiagonalLayoutView(@NonNull DiagonalLayoutView diagonalLayout, int optionHeightInPx, int marginTopInPx) {
    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) diagonalLayout.getLayoutParams();
    layoutParams.setMargins(0, -marginTopInPx, 0, 0);
    layoutParams.height = optionHeightInPx;
    diagonalLayout.setLayoutParams(layoutParams);
  }

  private void setupToolbar() {
    AppCompatActivity activity = (AppCompatActivity) getActivity();
    activity.setSupportActionBar(toolbarView);
    Optional.ofNullable(activity.getSupportActionBar())
        .ifPresent(actionBar -> {
          actionBar.setDisplayHomeAsUpEnabled(true);
          actionBar.setHomeButtonEnabled(true);
          actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
          actionBar.setDisplayShowTitleEnabled(false);
        });

    toolbarView.setNavigationOnClickListener(v -> drawerView.openDrawer(Gravity.START));

    Optional.ofNullable(navigationView)
        .ifPresent(this::setupDrawerContent);
  }

  private void setupDrawerContent(@NonNull NavigationView navigationView) {
    NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = menuItem -> {
      switch (menuItem.getItemId()) {
        case R.id.nav_review_tutorial:
          Intent intent = Henson.with(getContext())
              .gotoOnboardingActivity()
              .build()
              .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
          getContext().startActivity(intent);
          break;
        case R.id.nav_rate:
          //TODO go to rate app
          break;
        case R.id.nav_about:
          //TODO go to about
          break;
        case R.id.nav_login:
          //TODO go to login
          break;
        default:
      }
      return closeDrawer(menuItem);
    };
    navigationView.setNavigationItemSelectedListener(
        onNavigationItemSelectedListener);
  }

  private boolean closeDrawer(@NonNull MenuItem menuItem) {
    menuItem.setChecked(false);
    drawerView.closeDrawers();
    return true;
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
