package com.xmartlabs.daydreaming.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xmartlabs.daydreaming.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chaca on 4/4/17.
 */
public class DiagonalLayoutView extends LinearLayout {
  @BindView(R.id.diagonal_image)
  ImageView imageView;
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
  }
}
