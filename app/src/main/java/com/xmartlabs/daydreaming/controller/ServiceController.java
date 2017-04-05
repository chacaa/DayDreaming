package com.xmartlabs.daydreaming.controller;

import com.xmartlabs.daydreaming.helper.GeneralErrorHelper;

import javax.inject.Inject;

/** To distinguish between controllers that use the services, and those which don't. */
public abstract class ServiceController extends Controller {
  @Inject
  GeneralErrorHelper generalErrorHelper;

  public GeneralErrorHelper getGeneralErrorHelper() {
    return generalErrorHelper;
  }
}
