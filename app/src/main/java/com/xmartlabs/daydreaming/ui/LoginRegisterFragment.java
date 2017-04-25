package com.xmartlabs.daydreaming.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
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
public class LoginRegisterFragment extends BaseFragment {
  private static final int GONE = View.GONE;
  private static final int MIN_PASSWORD_LENGTH = 5;
  @StringRes
  private static final int PASSWORD = R.string.user;
  @StringRes
  private static final int USER = R.string.pass;
  private static final int VISIBLE = View.VISIBLE;

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
    errorTextView.setVisibility(View.GONE);

  }

  @OnClick(R.id.register_text_view)
  void onClickedRegister() {
    setupErrorFieldsAsInvisible();
    registerTextView.setTextColor(getColor(R.color.pale_teal));
    loginTextView.setTextColor(getColor(R.color.white));
    createLoginTextView.setText(getString(R.string.create_user));
    isRegisterSelected = true;
  }

  @OnClick(R.id.login_text_view)
  void onClickedLogin() {
    setupErrorFieldsAsInvisible();
    registerTextView.setTextColor(getColor(R.color.white));
    loginTextView.setTextColor(getColor(R.color.pale_teal));
    createLoginTextView.setText(getString(R.string.sign_in));
    isRegisterSelected = false;
  }

  @OnClick(R.id.create_login_textview)
  void onClickedCreateLogin() {
    setupErrorFieldsAsInvisible();
    if (isRegisterSelected) {
      checkFieldsDuringRegistration();
    } else {
      checkFieldsDuringLogin();
    }
  }

  private void checkFieldsDuringLogin() {
    //TODO make the validation through an authentication server
    if (!(usernameFieldView.getText().toString().toLowerCase()).equals(getString(USER))) {
      setupErrorNotification(VISIBLE, GONE, R.string.wrong_user);
    } else {
      if (!(passwordFieldView.getText().toString().toLowerCase()).equals(getString(PASSWORD))) {
        setupErrorNotification(GONE, VISIBLE, R.string.wrong_pass);
      } else {
        getActivity().finish();
      }
    }
  }

  private void setupErrorNotification(int userErrorVisibility, int passErrorVisibility,
                                      @StringRes int errorText) {
    usernameErrorView.setVisibility(userErrorVisibility);
    passwordErrorView.setVisibility(passErrorVisibility);
    errorTextView.setVisibility(View.VISIBLE);
    errorTextView.setText(getString(errorText));
  }

  private void checkFieldsDuringRegistration() {
    //TODO establish the constrains for the username and the password and create the user in the server
    if (passwordFieldView.getText().toString().length() < MIN_PASSWORD_LENGTH) {
      setupErrorNotification(GONE, VISIBLE, R.string.password_length_error);
    } else {
      getActivity().finish();
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
