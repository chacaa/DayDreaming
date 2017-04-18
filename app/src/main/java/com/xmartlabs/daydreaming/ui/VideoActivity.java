package com.xmartlabs.daydreaming.ui;

import android.os.Bundle;

import com.f2prateek.dart.HensonNavigable;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.xmartlabs.daydreaming.R;

/**
 * Created by chaca on 4/17/17.
 */
@HensonNavigable
public class VideoActivity extends YouTubeBaseActivity {
  public static final String API_KEY = "AIzaSyAEFt2rWmsB7WdNb064E3nJ0wR5lCm1rGI";
  public static final String VIDEO_ID = "V_BEOsCvKqI";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.video_layout);
    setupYoutubePlayerView();
  }

  private void setupYoutubePlayerView() {
    YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
    youTubePlayerView.initialize(API_KEY,
        new YouTubePlayer.OnInitializedListener() {
          @Override
          public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                              YouTubePlayer youTubePlayer, boolean b) {
            youTubePlayer.loadVideo(VIDEO_ID);
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
          }

          @Override
          public void onInitializationFailure(YouTubePlayer.Provider provider,
                                              YouTubeInitializationResult youTubeInitializationResult) {
          }
        });
  }
}
