package com.xmartlabs.daydreaming.controller;

import android.support.annotation.NonNull;

import com.xmartlabs.daydreaming.model.AuthResponse;
import com.xmartlabs.daydreaming.model.LoginRequest;
import com.xmartlabs.daydreaming.model.Session;
import com.xmartlabs.daydreaming.service.AuthService;

import javax.inject.Inject;

import io.reactivex.Single;

public class AuthController extends ServiceController {
  @Inject
  AuthService authService;
  @Inject
  SessionController sessionController;

  public Single<Session> login(LoginRequest loginRequest) {
    // TODO
    //authService.login();
    return Single.just(new AuthResponse())
            .flatMap(sessionController::setSession)
            .doOnSuccess(this::setLoginInfo);
  }

  public void setLoginInfo(@NonNull Session session) {
    // TODO
  }
}
