package com.xmartlabs.daydreaming.module;

import com.xmartlabs.daydreaming.helper.DatabaseHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
  @Provides
  @Singleton
  DatabaseHelper provideDatabaseHelper() {
    return new DatabaseHelper();
  }
}
