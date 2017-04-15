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
import com.annimon.stream.Stream;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.daydreaming.R;
import com.xmartlabs.daydreaming.helper.ui.MetricsHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;

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
  @BindView(R.id.food_option_view)
  DiagonalLayoutView foodOptionView;
  @BindView(R.id.nature_option_view)
  DiagonalLayoutView natureOptionView;
  @BindView(R.id.scroll_view)
  ScrollView scrollView;
  @BindView(R.id.sports_option_view)
  DiagonalLayoutView sportsOptionView;
  @BindView(R.id.toolbar)
  Toolbar toolbarView;
  @BindView(R.id.travel_option_view)
  DiagonalLayoutView travelOptionView;

  @BindViews({R.id.nature_option_view, R.id.travel_option_view, R.id.animals_option_view, R.id.sports_option_view})
  List<DiagonalLayoutView> diagonalViews;

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
      if (isAdded()) {
        @Dimension(unit = Dimension.PX)
        int height = scrollView.getMeasuredHeight() - (2 * BLACK_LINE_HEIGHT);
        @Dimension(unit = Dimension.PX)
        int margin = (int) (scrollView.getMeasuredWidth() * Math.tan(Math.toRadians(7)));
        @Dimension(unit = Dimension.PX)
        int optionHeight = ((height + margin * 2) / QUANTITY_OF_OPTIONS);

        setupOptionsItems(margin, optionHeight);
        bottomViewSetup(optionHeight, margin);
      }
    });
  }

  private void setupOptionsItems(int margin, int optionHeight) {
    setUpDiagonalLayoutView(artOptionView, optionHeight, 0);
    artOptionView.setDiagonalSeparatorPositionAndSize(optionHeight, margin);
    setupOption(artOptionView);
    setUpDiagonalLayoutView(foodOptionView, optionHeight, margin - BLACK_LINE_HEIGHT);
    setupOption(foodOptionView);
    foodOptionView.hideBottomLineSeparator();
    Stream.of(diagonalViews)
        .forEach(diagonalLayoutView -> {
          setUpDiagonalLayoutView(diagonalLayoutView, optionHeight, margin - BLACK_LINE_HEIGHT);
          setupOption(diagonalLayoutView);
          diagonalLayoutView.setDiagonalSeparatorPositionAndSize(optionHeight, margin);
        });
  }

  private void bottomViewSetup(@Dimension(unit = Dimension.PX) int optionHeight,
                               @Dimension(unit = Dimension.PX) int margin) {
    setUpDiagonalLayoutView(bottomGrayView, optionHeight / 2, margin);
    bottomGrayView.hideBottomLineSeparator();
    bottomGrayView.setTextSize(14);
    bottomGrayView.setTextColor(R.color.pale_teal);
    bottomGrayView.setSecondaryText(R.string.random_daydream);
    bottomGrayView.hideTitle();
    bottomGrayView.showSecondaryText();
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
    diagonalLayoutView.showSubtitle();
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
