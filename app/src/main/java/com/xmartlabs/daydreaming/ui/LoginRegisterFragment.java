package com.xmartlabs.daydreaming.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.annimon.stream.Optional;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.daydreaming.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chaca on 4/12/17.
 */
@FragmentWithArgs
public class LoginRegisterFragment extends BaseFragment{
  @BindView(R.id.background_video_view)
  VideoView backgroundVideoView;
  @BindView(R.id.create_login_textview)
  TextView createLoginTextView;
  @BindView(R.id.error_text_view)
  TextView errorTextView;
  @BindView(R.id.login_text_view)
  TextView loginTextView;
  @BindView(R.id.password_error)
  ImageView passwordErrorView;
  @BindView(R.id.password_field)
  EditText passwordFieldView;
  @BindView(R.id.register_text_view)
  TextView registerTextView;
  @BindView(R.id.toolbar)
  Toolbar toolbarView;
  @BindView(R.id.username_error)
  ImageView usernameErrorView;
  @BindView(R.id.username_field)
  EditText usernameFieldView;

  private boolean isRegisterSelected = true;

  @LayoutRes
  @Override
  protected int getLayoutResId() {
    return R.layout.login_register_fragment;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupBackgroundVideo();
    setupToolbar();
    setupErrorFieldsAsInvisible();
  }

  private void setupErrorFieldsAsInvisible() {
    passwordErrorView.setVisibility(View.GONE);
    usernameErrorView.setVisibility(View.GONE);
  }

  @OnClick(R.id.register_text_view)
  void onClickedRegister() {
    registerTextView.setTextColor(getColor(R.color.pale_teal));
    loginTextView.setTextColor(getColor(R.color.white));
    createLoginTextView.setText(getString(R.string.create_user));
    isRegisterSelected = true;
  }

  @OnClick(R.id.login_text_view)
  void onClickedLogin() {
    registerTextView.setTextColor(getColor(R.color.white));
    loginTextView.setTextColor(getColor(R.color.pale_teal));
    createLoginTextView.setText(getString(R.string.sign_in));
    isRegisterSelected = false;
  }

  @OnClick(R.id.create_login_textview)
  void onClickedCreateLogin() {
    //TODO create user and login user
    if (isRegisterSelected) {
      if (passwordFieldView.getText().toString().length() < 5) {
        passwordErrorView.setVisibility(View.VISIBLE);
        errorTextView.setText(getResources().getString(R.string.error_hint_sign_up));
      }
    } else {
      if (!(usernameFieldView.getText().toString().toLowerCase()).equals("santi")){
        usernameErrorView.setVisibility(View.VISIBLE);
        errorTextView.setText("Wrong user account");
      }
    }
  }

  private void setupBackgroundVideo() {
    String uriString = String.format(Locale.getDefault(),
        "android.resource://%s/%d", getActivity().getPackageName(), R.raw.daydreamingvideo);
    Uri uri = Uri.parse(uriString);
    backgroundVideoView.setVideoURI(uri);
    backgroundVideoView.start();
    backgroundVideoView.setOnPreparedListener(mediaPlayer -> mediaPlayer.setLooping(true));
  }

  private void setupToolbar() {
    AppCompatActivity activity = (AppCompatActivity) getActivity();
    activity.setSupportActionBar(toolbarView);
    Optional.ofNullable(activity.getSupportActionBar())
        .ifPresent(actionBar -> {
          actionBar.setDisplayHomeAsUpEnabled(true);
          actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
          actionBar.setDisplayShowTitleEnabled(false);
        });
  }
}
