package com.xmartlabs.daydreaming.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.annimon.stream.Optional;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.daydreaming.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by santiago on 4/10/17.
 */
@FragmentWithArgs
public class CustomScreenFragment extends BaseFragment {
  public static final int MAX = 100;

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

  @LayoutRes
  @Override
  protected int getLayoutResId() {
    return R.layout.custom_option_freagment;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupToolbar();
    moodSeekBarView.setMax(MAX);
  }

  @OnClick(R.id.art_text_view)
  void onClickedArtOption() {
    theme = getString(R.string.art);
    artTextView.setTextColor(getColor(R.color.white));
    natureTextView.setTextColor(getColor(R.color.warm_grey));
    travelTextView.setTextColor(getColor(R.color.warm_grey));
    animalsTextView.setTextColor(getColor(R.color.warm_grey));
    sportsTextView.setTextColor(getColor(R.color.warm_grey));
    foodTextView.setTextColor(getColor(R.color.warm_grey));
  }

  @OnClick(R.id.nature_text_view)
  void onClickedNatureOption() {
    theme = getString(R.string.nature);
    artTextView.setTextColor(getColor(R.color.warm_grey));
    natureTextView.setTextColor(getColor(R.color.white));
    travelTextView.setTextColor(getColor(R.color.warm_grey));
    animalsTextView.setTextColor(getColor(R.color.warm_grey));
    sportsTextView.setTextColor(getColor(R.color.warm_grey));
    foodTextView.setTextColor(getColor(R.color.warm_grey));
  }

  @OnClick(R.id.travel_text_view)
  void onClickedTravelOption() {
    theme = getString(R.string.travel);
    artTextView.setTextColor(getColor(R.color.warm_grey));
    natureTextView.setTextColor(getColor(R.color.warm_grey));
    travelTextView.setTextColor(getColor(R.color.white));
    animalsTextView.setTextColor(getColor(R.color.warm_grey));
    sportsTextView.setTextColor(getColor(R.color.warm_grey));
    foodTextView.setTextColor(getColor(R.color.warm_grey));
  }

  @OnClick(R.id.animals_text_view)
  void onClickedAnimalsOption() {
    theme = getString(R.string.animals);
    artTextView.setTextColor(getColor(R.color.warm_grey));
    natureTextView.setTextColor(getColor(R.color.warm_grey));
    travelTextView.setTextColor(getColor(R.color.warm_grey));
    animalsTextView.setTextColor(getColor(R.color.white));
    sportsTextView.setTextColor(getColor(R.color.warm_grey));
    foodTextView.setTextColor(getColor(R.color.warm_grey));
  }

  @OnClick(R.id.sports_text_view)
  void onClickedSportsOption() {
    theme = getString(R.string.sports);
    artTextView.setTextColor(getColor(R.color.warm_grey));
    natureTextView.setTextColor(getColor(R.color.warm_grey));
    travelTextView.setTextColor(getColor(R.color.warm_grey));
    animalsTextView.setTextColor(getColor(R.color.warm_grey));
    sportsTextView.setTextColor(getColor(R.color.white));
    foodTextView.setTextColor(getColor(R.color.warm_grey));
  }

  @OnClick(R.id.food_text_view)
  void onClickedFoodOption() {
    theme = getString(R.string.food);
    artTextView.setTextColor(getColor(R.color.warm_grey));
    natureTextView.setTextColor(getColor(R.color.warm_grey));
    travelTextView.setTextColor(getColor(R.color.warm_grey));
    animalsTextView.setTextColor(getColor(R.color.warm_grey));
    sportsTextView.setTextColor(getColor(R.color.warm_grey));
    foodTextView.setTextColor(getColor(R.color.white));
  }

  @OnClick(R.id.start_daydreaming_linearlayout)
  void onClickedStartDreaming() {
    //noinspection deprecation
    startDaydreamingView.setBackground(getResources().getDrawable(R.drawable.double_square_shape_2_selected));
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
    if (progress < 34) {
      type = getString(R.string.chiil);
    } else {
      if (progress >= 34 && progress < 67) {
        type = getString(R.string.neutral);
      } else {
        type = getString(R.string.energetic);
      }
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
}
