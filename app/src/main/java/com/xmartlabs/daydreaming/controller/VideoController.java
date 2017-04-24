package com.xmartlabs.daydreaming.controller;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

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

  @CheckResult
  @NonNull
  private Single<List<Video>> getVideosByTheme(String theme) {
    return videoService.getVideosByTheme(theme)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io());
  }

  @CheckResult
  @NonNull
  private Single<List<Video>> getVideosByType(String type) {
    return videoService.getVideosByType(type)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io());
  }

  @CheckResult
  @NonNull
  public Single<List<Video>> getVideos(String theme, String type) {
    return Single.zip(getVideosByTheme(theme), getVideosByType(type),
        (videos, videos2) -> Stream.concat(Stream.of(videos).filter(video -> !videos2.contains(video)),
            Stream.of(videos2)).toList()
    );
  }
}
