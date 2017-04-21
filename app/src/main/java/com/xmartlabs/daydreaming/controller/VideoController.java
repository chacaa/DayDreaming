package com.xmartlabs.daydreaming.controller;

import com.annimon.stream.Stream;
import com.xmartlabs.daydreaming.model.Video;
import com.xmartlabs.daydreaming.service.VideoService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chaca on 4/21/17.
 */
public class VideoController extends ServiceController {
  @Inject
  VideoService videoService;

  public Single<List<Video>> getVideosByTheme(String theme) {
    return videoService.getVideosByTheme(theme)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io());
  }

  public Single<List<Video>> getVideosByType(String type) {
    return videoService.getVideosByType(type)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io());
  }

  public Single<List<Video>> getVideos(String theme, String type) {
    return Single.zip(getVideosByTheme(theme), getVideosByType(type),
        (videos, videos2) -> {
          videos2.addAll(Stream.of(videos)
              .filter(value -> !videos2.contains(value))
              .toList());
          return videos2;
        }
    );
  }
}
