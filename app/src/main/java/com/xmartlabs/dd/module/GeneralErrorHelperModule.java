package com.xmartlabs.dd.module;

import com.xmartlabs.dd.helper.GeneralErrorHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GeneralErrorHelperModule {
  @Provides
  @Singleton
  GeneralErrorHelper provideGeneralErrorHelper() {
    return new GeneralErrorHelper();
  }
}
