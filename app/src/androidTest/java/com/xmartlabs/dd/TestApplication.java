package com.xmartlabs.dd;

import com.xmartlabs.dd.module.AndroidModule;

import bullet.ObjectGraph;

public class TestApplication extends DDApplication {
  @Override
  protected ApplicationComponent createComponent() {
    return DaggerTestComponent.builder()
        .androidModule(new AndroidModule(this))
        .build();
  }

  @Override
  protected ObjectGraph createBullet(ApplicationComponent component) {
    return new BulletTestComponent((TestComponent) component);
  }
}
