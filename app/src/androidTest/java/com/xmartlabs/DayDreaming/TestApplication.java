package com.xmartlabs.DayDreaming;

import com.xmartlabs.DayDreaming.module.AndroidModule;

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
