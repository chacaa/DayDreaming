package com.xmartlabs.daydreaming;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.runner.AndroidJUnitRunner;

import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.xmartlabs.daydreaming.common.ImmediateNewThreadScheduler;
import com.xmartlabs.daydreaming.module.MockClockModule;
import com.xmartlabs.daydreaming.module.OkHttpModule;

import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.inject.Named;

import io.appflate.restmock.RESTMockServerStarter;
import io.appflate.restmock.android.AndroidAssetsFileParser;
import io.appflate.restmock.android.AndroidLogger;
import io.reactivex.plugins.RxJavaPlugins;
import lombok.Getter;
import okhttp3.OkHttpClient;

public class TestRunner extends AndroidJUnitRunner {
  @Getter
  private static AndroidAssetsFileParser androidAssetsFileParser;
  @Getter
  private static Context testContext;

  @Inject
  @Named(OkHttpModule.CLIENT_PICASSO)
  OkHttpClient picassoOkHttpClient;
  @Inject
  @Named(OkHttpModule.CLIENT_SERVICE)
  OkHttpClient serviceOkHttpClient;

  @Override
  public void onCreate(Bundle arguments) {
    super.onCreate(arguments);

    TimeZone.setDefault(MockClockModule.DEFAULT_TIME_ZONE);
    RxJavaPlugins.setIoSchedulerHandler(scheduler -> new ImmediateNewThreadScheduler());
  }

  @Override
  public Application newApplication(ClassLoader classLoader, String className, Context context)
      throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    testContext = getContext();
    androidAssetsFileParser = new AndroidAssetsFileParser(testContext);
    RESTMockServerStarter.startSync(androidAssetsFileParser, new AndroidLogger());

    return super.newApplication(classLoader, TestApplication.class.getName(), context);
  }

  @Override
  public void callApplicationOnCreate(Application app) {
    super.callApplicationOnCreate(app);

    TestApplication testApp = (TestApplication) app;
    testApp.inject(this);

    IdlingResource picassoIdlingResource = OkHttp3IdlingResource.create(
        String.format(Locale.US, "%sOkHttp", OkHttpModule.CLIENT_PICASSO), picassoOkHttpClient);
    Espresso.registerIdlingResources(picassoIdlingResource);

    IdlingResource serviceIdlingResource = OkHttp3IdlingResource.create(
        String.format(Locale.US, "%sOkHttp", OkHttpModule.CLIENT_SERVICE), serviceOkHttpClient);
    Espresso.registerIdlingResources(serviceIdlingResource);
  }
}
