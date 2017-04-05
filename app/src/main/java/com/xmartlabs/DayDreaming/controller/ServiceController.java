package com.xmartlabs.DayDreaming.controller;

import com.xmartlabs.DayDreaming.helper.GeneralErrorHelper;

import javax.inject.Inject;

/** To distinguish between controllers that use the services, and those which don't. */
public abstract class ServiceController extends Controller {
  @Inject
  GeneralErrorHelper generalErrorHelper;

  public GeneralErrorHelper getGeneralErrorHelper() {
    return generalErrorHelper;
  }
}
