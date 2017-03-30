package com.xmartlabs.dd.controller;

import com.xmartlabs.dd.BaseProjectApplication;

public abstract class Controller {
  public Controller() {
    BaseProjectApplication.getContext().inject(this);
  }
}
