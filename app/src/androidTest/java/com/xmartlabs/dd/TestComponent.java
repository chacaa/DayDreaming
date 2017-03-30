package com.xmartlabs.dd;

import com.xmartlabs.dd.module.AndroidModule;
import com.xmartlabs.dd.module.ControllerModule;
import com.xmartlabs.dd.module.DatabaseModule;
import com.xmartlabs.dd.module.GeneralErrorHelperModule;
import com.xmartlabs.dd.module.GsonModule;
import com.xmartlabs.dd.module.MockRestServiceModule;
import com.xmartlabs.dd.module.OkHttpModule;
import com.xmartlabs.dd.module.PicassoModule;
import com.xmartlabs.dd.ui.common.BaseInstrumentationTest;

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
