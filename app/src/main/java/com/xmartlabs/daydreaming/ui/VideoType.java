package com.xmartlabs.daydreaming.ui;

import com.xmartlabs.daydreaming.R;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by chaca on 4/25/17.
 */
@Getter
@RequiredArgsConstructor
public enum VideoType {
  CHILL(R.string.chill, 0, 33),
  NEUTRAL(R.string.neutral, 34, 66),
  ENERGETIC(R.string.energetic, 67, 100),
  ;

  private final int description;
  private final int minValue;
  private final int maxValue;
}
