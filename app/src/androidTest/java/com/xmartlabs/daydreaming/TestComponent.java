package com.xmartlabs.daydreaming;

import com.xmartlabs.daydreaming.module.AndroidModule;
import com.xmartlabs.daydreaming.module.ControllerModule;
import com.xmartlabs.daydreaming.module.DatabaseModule;
import com.xmartlabs.daydreaming.module.GeneralErrorHelperModule;
import com.xmartlabs.daydreaming.module.GsonModule;
import com.xmartlabs.daydreaming.module.MockRestServiceModule;
import com.xmartlabs.daydreaming.module.OkHttpModule;
import com.xmartlabs.daydreaming.module.PicassoModule;
import com.xmartlabs.daydreaming.ui.common.BaseInstrumentationTest;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
    AndroidModule.class,
    ControllerModule.class,
    DatabaseModule.class,
    GeneralErrorHelperModule.class,
    GsonModule.class,
    MockRestServiceModule.class,
    OkHttpModule.class,
    PicassoModule.class,
})
@Singleton
public interface TestComponent extends ApplicationComponent {
  void inject(BaseInstrumentationTest baseInstrumentationTest);

  void inject(TestRunner testRunner);
}
