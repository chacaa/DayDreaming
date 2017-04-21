package com.xmartlabs.daydreaming.model;

import org.parceler.Parcel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Created by chaca on 4/21/17.
 */
@Builder
@Data
public class Video {
  String id;
  String theme;
  String type;

  public String getId() {
    return id;
  }
}
