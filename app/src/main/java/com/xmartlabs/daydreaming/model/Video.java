package com.xmartlabs.daydreaming.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by chaca on 4/21/17.
 */
@Builder
@Data
public class Video {
  private String id;
  private String theme;
  private String type;
}
