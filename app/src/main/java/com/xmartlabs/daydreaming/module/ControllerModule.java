package com.xmartlabs.daydreaming.module;

import com.xmartlabs.daydreaming.controller.AuthController;
import com.xmartlabs.daydreaming.controller.SessionController;
import com.xmartlabs.daydreaming.controller.VideoController;

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

  @Provides
  @Singleton
  VideoController provideVideoController() {
    return new VideoController();
  }
}
