package com.xmartlabs.daydreaming.service;

import com.xmartlabs.daydreaming.model.Video;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chaca on 4/21/17.
 */
public interface VideoService {
  String VIDEOS = "videos";

  @GET(VIDEOS)
  Single<List<Video>> getVideosByTheme(@Query("theme") String theme);

  @GET(VIDEOS)
  Single<List<Video>> getVideosByType(@Query("type") String type);
}
