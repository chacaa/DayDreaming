package com.xmartlabs.daydreaming;

import com.xmartlabs.daydreaming.module.AndroidModule;

import bullet.ObjectGraph;

public class TestApplication extends DayDreamingApplication {
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
