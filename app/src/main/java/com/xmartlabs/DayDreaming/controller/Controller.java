package com.xmartlabs.DayDreaming.controller;

import com.xmartlabs.DayDreaming.DayDreamingApplication;

public abstract class Controller {
  public Controller() {
    DayDreamingApplication.getContext().inject(this);
  }
}
