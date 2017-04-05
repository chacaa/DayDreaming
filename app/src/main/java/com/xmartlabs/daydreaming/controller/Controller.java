package com.xmartlabs.daydreaming.controller;

import com.xmartlabs.daydreaming.DayDreamingApplication;

public abstract class Controller {
  public Controller() {
    DayDreamingApplication.getContext().inject(this);
  }
}
