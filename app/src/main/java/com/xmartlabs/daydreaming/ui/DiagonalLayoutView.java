package com.xmartlabs.daydreaming.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.support.annotation.Dimension;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xmartlabs.daydreaming.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chaca on 4/4/17.
 */
public class DiagonalLayoutView extends LinearLayout {
  @BindView(R.id.diagonal_separator)
  DiagonalLayout diagonalSeparator;
  @BindView(R.id.diagonal_image)
  ImageView imageView;
  @BindView(R.id.secondary_text_view)
  TextView secondaryTextView;
  @BindView(R.id.subtitle_text_view)
  TextView subtitleView;
  @BindView(R.id.diagonal_text_view)
  TextView textView;

  public DiagonalLayoutView(Context context, AttributeSet attrs) {
    super(context, attrs);

    setOrientation(VERTICAL);
    setGravity(Gravity.CENTER);

    View view = getView(context);
    ButterKnife.bind(this, view);
    setupViewProperties(attrs);
  }

  private View getView(Context context) {
    LayoutInflater inflater = LayoutInflater.from(context);
    return inflater.inflate(R.layout.diagonal_layout_view, this, true);
  }

  private void setupViewProperties(AttributeSet attrs) {
    TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DiagonalLayoutView);
    imageView.setImageDrawable(typedArray.getDrawable(R.styleable.DiagonalLayoutView_android_src));
    textView.setText(typedArray.getText(R.styleable.DiagonalLayoutView_android_text));
    textView.setTextColor(typedArray.getColor(R.styleable.DiagonalLayoutView_android_textColor, 0));
    typedArray.recycle();
  }

  public void setTextColor(@ColorRes int color) {
    //noinspection deprecation
    textView.setTextColor(getResources().getColor(color));
    //noinspection deprecation
    subtitleView.setTextColor(getResources().getColor(color));
  }

  public void setTextSize(@Dimension(unit = Dimension.SP) float size) {
    textView.setTextSize(Dimension.SP, size);
    subtitleView.setTextSize(Dimension.SP, size);
    secondaryTextView.setTextSize(Dimension.SP, size);
  }

  public void setDiagonalImageAlpha(int alpha) {
    imageView.setImageAlpha(alpha);
  }

  public void setSubtitleText(@StringRes int text) {
    subtitleView.setText(text);
  }

  public void setSubtitleText(@NonNull String text) {
    subtitleView.setText(text);
  }

  public void showSubtitle() {
    subtitleView.setVisibility(View.VISIBLE);
  }

  public void hideTitle() {
    textView.setVisibility(View.GONE);
  }

  public void showSecondaryText() {
    secondaryTextView.setVisibility(View.VISIBLE);
  }

  public void setSecondaryText(@StringRes int text) {
    secondaryTextView.setText(text);
  }

  public void setSecondaryText(@NonNull String text) {
    secondaryTextView.setText(text);
  }

  public void hideBottomLineSeparator() {
    diagonalSeparator.setVisibility(View.GONE);
  }

  public void setDiagonalSeparatorPositionAndSize(@Dimension(unit = Dimension.PX) int optionHeight,
                                                  @Dimension(unit = Dimension.PX) int marginTop) {
    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) diagonalSeparator.getLayoutParams();
    layoutParams.setMargins(0, -marginTop, 0, 0);
    layoutParams.height = optionHeight;
    diagonalSeparator.setLayoutParams(layoutParams);
    diagonalSeparator.requestLayout();
  }
}
