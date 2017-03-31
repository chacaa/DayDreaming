package com.xmartlabs.dd.module;

import com.xmartlabs.dd.DDApplication;
import com.xmartlabs.dd.controller.SessionController;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class SessionInterceptor implements Interceptor {
  @Inject
  SessionController sessionController;

  @Override
  public Response intercept(Chain chain) throws IOException {
    if (sessionController == null) {
      DDApplication.getContext().inject(this); // Can't do this in constructor because it's called in a module.
    }

    Request request = sessionController.getSession()
        .map(session ->  chain.request().newBuilder()
              //.addHeader("session", sessionInfo) // TODO: Add auth token here if needed
              .build())
        .orElse(chain.request());
    return chain.proceed(request);
  }
}
