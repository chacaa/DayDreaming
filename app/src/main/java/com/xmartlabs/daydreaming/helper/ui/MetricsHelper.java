package com.xmartlabs.daydreaming.helper.ui;

import android.content.res.TypedArray;
import android.support.annotation.DimenRes;
import android.support.annotation.Dimension;

import com.xmartlabs.daydreaming.DayDreamingApplication;
import com.xmartlabs.daydreaming.R;

@SuppressWarnings("unused")
public class MetricsHelper {
  /**
   * Converts the {@code dp} value to pixels dimension.
   *
   * @param dp the value in dp dimension
   * @return the converted {@code dp} value to pixels
   */
  @Dimension(unit = Dimension.PX)
  public static float dpToPx(@Dimension(unit = Dimension.DP) float dp) {
    return dp * getContext().getResources().getDisplayMetrics().density;
  }

  /**
   * Converts the {@code sp} value to pixels dimension.
   *
   * @param sp the value in sp dimension
   * @return the converted {@code sp} value to pixels
   */
  @Dimension(unit = Dimension.PX)
  public static float spToPx(@Dimension(unit = Dimension.SP) float sp) {
    return sp * getContext().getResources().getDisplayMetrics().scaledDensity;
  }

  /**
   * Converts the {@code dp} value to pixels dimension.
   *
   * @param dp the value in dp dimension
   * @return the converted {@code dp} value to integer pixels
   */
  @Dimension(unit = Dimension.PX)
  public static int dpToPxInt(@Dimension(unit = Dimension.DP) float dp) {
    return (int) dpToPx(dp);
  }

  /**
   * Converts the given {@code dimenId} resource to pixels.
   *
   * @param dimenId the resource to convert
   * @return the converted {@code dimenId} resource value to integer pixels
   */
  @Dimension(unit = Dimension.DP)
  public static int dimResToPxInt(@DimenRes int dimenId) {
    return (int) getContext().getResources().getDimension(dimenId);
  }

  /**
   * Converts the {@code px} value to dp.
   *
   * @param px the value in pixels to convert to dp
   * @return the converted {@code px} value to dp
   */
  @Dimension(unit = Dimension.PX)
  public static float pxToDp(@Dimension(unit = Dimension.PX) float px) {
    return px / getContext().getResources().getDisplayMetrics().density;
  }

  /**
   * Retrieves the toolbar height of the current app theme.
   *
   * @return the toolbar height of the current app theme
   */
  public static int getToolbarHeight() {
    final TypedArray styledAttributes = getContext().getTheme().obtainStyledAttributes(new int[] {R.attr.actionBarSize});
    int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
    styledAttributes.recycle();
    return toolbarHeight;
  }

  /**
   * Calculates the Euclidean distance.
   *
   * @param x1 the x coordinate of the first point
   * @param y1 the y coordinate of the first point
   * @param x2 the x coordinate of the second point
   * @param y2 the y coordinate of the second point
   * @return the euclidean distance
   */
  public static float euclideanDistance(float x1, float y1, float x2, float y2) {
    float dx = x1 - x2;
    float dy = y1 - y2;
    return (float) Math.sqrt(dx * dx + dy * dy);
  }

  private static DayDreamingApplication getContext() {
    return DayDreamingApplication.getContext();
  }
}
