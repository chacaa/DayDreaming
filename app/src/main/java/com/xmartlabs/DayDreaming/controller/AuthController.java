package com.xmartlabs.DayDreaming.controller;

import android.support.annotation.NonNull;

import com.xmartlabs.DayDreaming.model.AuthResponse;
import com.xmartlabs.DayDreaming.model.LoginRequest;
import com.xmartlabs.DayDreaming.model.Session;
import com.xmartlabs.DayDreaming.service.AuthService;

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
