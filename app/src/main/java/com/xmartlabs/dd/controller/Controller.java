package com.xmartlabs.dd.controller;

import com.xmartlabs.dd.DDApplication;

public abstract class Controller {
  public Controller() {
    DDApplication.getContext().inject(this);
  }
}
