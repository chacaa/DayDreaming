package com.xmartlabs.dd.module;

import com.xmartlabs.dd.controller.AuthController;
import com.xmartlabs.dd.controller.SessionController;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ControllerModule {
  @Provides
  @Singleton
  AuthController provideAuthController() {
    return new AuthController();
  }

  @Provides
  @Singleton
  SessionController provideSessionController() {
    return new SessionController();
  }
}
