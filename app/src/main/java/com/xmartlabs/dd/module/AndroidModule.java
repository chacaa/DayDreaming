package com.xmartlabs.dd.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.xmartlabs.dd.DDApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {
  private final DDApplication application;

  public AndroidModule(DDApplication application) {
    this.application = application;
  }

  @Provides
  @Singleton
  Context provideApplicationContext() {
    return application;
  }

  @Provides
  @Singleton
  SharedPreferences provideSharedPreferences() {
    return PreferenceManager.getDefaultSharedPreferences(application);
  }
}
