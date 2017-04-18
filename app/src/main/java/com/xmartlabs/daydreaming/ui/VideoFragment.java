package com.xmartlabs.daydreaming.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.daydreaming.R;

/**
 * Created by chaca on 4/18/17.
 */
@FragmentWithArgs
public class VideoFragment extends BaseFragment {
  public static final String VIDEO_ID = "V_BEOsCvKqI"; //TODO change it for a parameter received

  @Override
  protected int getLayoutResId() {
    return R.layout.video_layout;
  }

  @NonNull
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);
    setupYoutubePlayer();
    return view;
  }

  private void setupYoutubePlayer() {
    YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
    transaction.add(R.id.youtube_fragment, youTubePlayerFragment).commit();

    youTubePlayerFragment.initialize(getString(R.string.api_key), new YouTubePlayer.OnInitializedListener() {
      @Override
      public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(VIDEO_ID);
        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
      }

      @Override
      public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
      }
    });
  }
}
