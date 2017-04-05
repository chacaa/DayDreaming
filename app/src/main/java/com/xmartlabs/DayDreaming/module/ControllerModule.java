package com.xmartlabs.DayDreaming.module;

import com.xmartlabs.DayDreaming.controller.AuthController;
import com.xmartlabs.DayDreaming.controller.SessionController;

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
