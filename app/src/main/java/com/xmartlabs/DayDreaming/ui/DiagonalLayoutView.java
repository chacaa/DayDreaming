package com.xmartlabs.DayDreaming.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.diagonallayout.DiagonalLayout;
import com.xmartlabs.DayDreaming.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by scasas on 4/4/17.
 */
public class DiagonalLayoutView extends DiagonalLayout {
  @BindView(R.id.diagonal_image)
  ImageView imageView;
  @BindView(R.id.diagonal_text_view)
  TextView textView;

  public DiagonalLayoutView(Context context, AttributeSet attrs) {
    super(context, attrs);
    View view = getView(context);
    ButterKnife.bind(this, view);
    TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DiagonalLayoutView);
    setupViewProperties(typedArray);
    typedArray.recycle();
  }

  private View getView(Context context) {
    LayoutInflater inflater = LayoutInflater.from(context);
    return inflater.inflate(R.layout.diagonal_layout_view, this, true);
  }

  private void setupViewProperties(TypedArray typedArray) {
    imageView.setImageDrawable(typedArray.getDrawable(R.styleable.DiagonalLayoutView_android_src));
    textView.setText(typedArray.getText(R.styleable.DiagonalLayoutView_android_text));
    textView.setTextColor(typedArray.getColor(R.styleable.DiagonalLayoutView_android_textColor, 0));
  }

  public void setTextColor(@ColorRes int color) {
    //noinspection deprecation
    textView.setTextColor(getResources().getColor(color));
  }
}
