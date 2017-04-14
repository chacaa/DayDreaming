package com.xmartlabs.daydreaming.ui;

import android.os.Bundle;
import android.support.annotation.Dimension;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.annimon.stream.Optional;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.daydreaming.R;
import com.xmartlabs.daydreaming.helper.ui.MetricsHelper;

import butterknife.BindView;

/**
 * Created by chaca on 4/13/17.
 */
@FragmentWithArgs
public class TrendingScreenFragment extends BaseFragment {
  @Dimension(unit = Dimension.PX)
  private static final int BLACK_LINE_HEIGHT = MetricsHelper.dpToPxInt(3);
  private static final int QUANTITY_OF_OPTIONS = 3; //TODO change it for a list

  @BindView(R.id.animals_option_view)
  DiagonalLayoutView animalsOptionView;
  @BindView(R.id.art_option_view)
  DiagonalLayoutView artOptionView;
  @BindView(R.id.bottom_gray_diagonal_view)
  DiagonalLayoutView bottomGrayView;
  @BindView(R.id.fifth_black_line_view)
  DiagonalLayoutView fifthBlackLineView;
  @BindView(R.id.first_black_line_view)
  DiagonalLayoutView firstBlackLineView;
  @BindView(R.id.food_option_view)
  DiagonalLayoutView foodOptionView;
  @BindView(R.id.fourth_black_line_view)
  DiagonalLayoutView fourthBlackLineView;
  @BindView(R.id.nature_option_view)
  DiagonalLayoutView natureOptionView;
  @BindView(R.id.scroll_view)
  ScrollView scrollView;
  @BindView(R.id.second_black_line_view)
  DiagonalLayoutView secondBlackLineView;
  @BindView(R.id.sports_option_view)
  DiagonalLayoutView sportsOptionView;
  @BindView(R.id.third_black_line_view)
  DiagonalLayoutView thirdBlackLineView;
  @BindView(R.id.toolbar)
  Toolbar toolbarView;
  @BindView(R.id.travel_option_view)
  DiagonalLayoutView travelOptionView;

  @LayoutRes
  @Override
  protected int getLayoutResId() {
    return R.layout.trending_option_fragment;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setUpView(view);
    setupToolbar();
  }

  private void setUpView(View view) {
    view.post(() -> {
      @Dimension(unit = Dimension.PX)
      int height = scrollView.getMeasuredHeight() - (2 * BLACK_LINE_HEIGHT);
      @Dimension(unit = Dimension.PX)
      int margin = (int) (scrollView.getMeasuredWidth() * Math.tan(Math.toRadians(7)));
      @Dimension(unit = Dimension.PX)
      int optionHeight = ((height + margin * 2) / QUANTITY_OF_OPTIONS);
      setUpDiagonalLayoutView(artOptionView, optionHeight, 0);
      setUpDiagonalLayoutView(firstBlackLineView, optionHeight, margin);
      setUpDiagonalLayoutView(natureOptionView, optionHeight, optionHeight - BLACK_LINE_HEIGHT);
      setUpDiagonalLayoutView(secondBlackLineView, optionHeight, margin);
      setUpDiagonalLayoutView(travelOptionView, optionHeight, optionHeight - BLACK_LINE_HEIGHT);
      setUpDiagonalLayoutView(thirdBlackLineView, optionHeight, margin);
      setUpDiagonalLayoutView(animalsOptionView, optionHeight, optionHeight - BLACK_LINE_HEIGHT);
      setUpDiagonalLayoutView(fourthBlackLineView, optionHeight, margin);
      setUpDiagonalLayoutView(sportsOptionView, optionHeight, optionHeight - BLACK_LINE_HEIGHT);
      setUpDiagonalLayoutView(fifthBlackLineView, optionHeight, margin);
      setUpDiagonalLayoutView(foodOptionView, optionHeight, optionHeight - BLACK_LINE_HEIGHT);
      setUpDiagonalLayoutView(bottomGrayView, optionHeight / 2, margin);

      setupOptions();
      bottomViewSetup();
    });
  }

  private void bottomViewSetup() {
    bottomGrayView.setTextSize(14);
    bottomGrayView.setTextColor(R.color.pale_teal);
    bottomGrayView.setSecondaryText(R.string.random_daydream);
    bottomGrayView.setTitleInvisible();
    bottomGrayView.setSecondaryTextVisible();
  }

  private void setupOptions() {
    setupOption(artOptionView);
    setupOption(natureOptionView);
    setupOption(travelOptionView);
    setupOption(animalsOptionView);
    setupOption(sportsOptionView);
    setupOption(foodOptionView);
  }

  private void setUpDiagonalLayoutView(@NonNull DiagonalLayoutView diagonalLayout, int optionHeightInPx, int marginTopInPx) {
    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) diagonalLayout.getLayoutParams();
    layoutParams.setMargins(0, -marginTopInPx, 0, 0);
    layoutParams.height = optionHeightInPx;
    diagonalLayout.setLayoutParams(layoutParams);
    diagonalLayout.requestLayout();
  }

  private void setupOption(DiagonalLayoutView diagonalLayoutView) {
    diagonalLayoutView.setTextColor(R.color.white);
    diagonalLayoutView.setSubtitleVisible();
    diagonalLayoutView.setTextSize(14);
    diagonalLayoutView.setSubtitleText("2:33m"); //TODO put the duration of the loaded video
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
