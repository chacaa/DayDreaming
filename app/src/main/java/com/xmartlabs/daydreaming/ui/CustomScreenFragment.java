package com.xmartlabs.daydreaming.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.annimon.stream.Optional;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.daydreaming.R;
import com.xmartlabs.daydreaming.model.Video;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by santiago on 4/10/17.
 */
@FragmentWithArgs
public class CustomScreenFragment extends BaseFragment {
  private static final int MAX_SEEKBAR_PROGRESS_VALUE = 100;

  @BindView(R.id.animals_text_view)
  TextView animalsTextView;
  @BindView(R.id.art_text_view)
  TextView artTextView;
  @BindView(R.id.food_text_view)
  TextView foodTextView;
  @BindView(R.id.mood_seekbar)
  SeekBar moodSeekBarView;
  @BindView(R.id.nature_text_view)
  TextView natureTextView;
  @BindView(R.id.sports_text_view)
  TextView sportsTextView;
  @BindView(R.id.start_dreaming_textview)
  TextView startDaydreamingTextView;
  @BindView(R.id.start_daydreaming_linearlayout)
  LinearLayout startDaydreamingView;
  @BindView(R.id.main_toolbar)
  Toolbar toolbarView;
  @BindView(R.id.travel_text_view)
  TextView travelTextView;

  private String theme;
  private String type;
  private int warmGreyColor = getColor(R.color.warm_grey);

  @LayoutRes
  @Override
  protected int getLayoutResId() {
    return R.layout.custom_option_freagment;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupToolbar();
    moodSeekBarView.setMax(MAX_SEEKBAR_PROGRESS_VALUE);
  }

  @OnClick(R.id.art_text_view)
  void onClickedArtOption() {
    setOptionAsSelected(R.string.art, artTextView);
  }

  @OnClick(R.id.nature_text_view)
  void onClickedNatureOption() {
    setOptionAsSelected(R.string.nature, natureTextView);
  }

  @OnClick(R.id.travel_text_view)
  void onClickedTravelOption() {
    setOptionAsSelected(R.string.travel, travelTextView);
  }

  @OnClick(R.id.animals_text_view)
  void onClickedAnimalsOption() {
    setOptionAsSelected(R.string.animals, animalsTextView);
  }

  @OnClick(R.id.sports_text_view)
  void onClickedSportsOption() {
    setOptionAsSelected(R.string.sports, sportsTextView);
  }

  @OnClick(R.id.food_text_view)
  void onClickedFoodOption() {
    setOptionAsSelected(R.string.food, foodTextView);
  }

  private void setOptionAsSelected(@StringRes int text, @NonNull TextView textView) {
    theme = getString(text);
    paintAllAsUnselected();
    textView.setTextColor(getColor(R.color.white));
  }

  private void paintAllAsUnselected() {
    artTextView.setTextColor(warmGreyColor);
    natureTextView.setTextColor(warmGreyColor);
    travelTextView.setTextColor(warmGreyColor);
    animalsTextView.setTextColor(warmGreyColor);
    sportsTextView.setTextColor(warmGreyColor);
    foodTextView.setTextColor(warmGreyColor);
  }

  @OnClick(R.id.start_daydreaming_linearlayout)
  void onClickedStartDreaming() {
    //noinspection deprecation
    startDaydreamingView
        .setBackground(getResources().getDrawable(R.drawable.double_square_shape_2_selected));
    startDaydreamingTextView.setTextColor(getColor(R.color.white));
    reviewProgressAndSetType();
    callVideoActivity(R.drawable.home_custom);
  }

  private void callVideoActivity(@DrawableRes int image) {
    Intent intent = Henson.with(getContext())
        .gotoVideoActivity()
        .image(image)
        .theme(theme)
        .type(type)
        .build();
    getContext().startActivity(intent);
  }

  private void reviewProgressAndSetType() {
    int progress = moodSeekBarView.getProgress();
    if (progress <= VideoType.CHILL.getMaxValue()) {
      type = getString(VideoType.CHILL.getDescription());
    } else {
      type = progress <= VideoType.NEUTRAL.getMaxValue() ?
          getString(VideoType.NEUTRAL.getDescription()) :
          getString(VideoType.ENERGETIC.getDescription());
    }
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

  @Override
  public void onResume() {
    super.onResume();
    paintAllAsUnselected();
    removeSeekBarProgress();
  }

  private void removeSeekBarProgress() {
    moodSeekBarView.setProgress(0);
  }
}
