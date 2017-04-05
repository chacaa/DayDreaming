package com.xmartlabs.DayDreaming;

import com.xmartlabs.DayDreaming.module.AndroidModule;
import com.xmartlabs.DayDreaming.module.ControllerModule;
import com.xmartlabs.DayDreaming.module.DatabaseModule;
import com.xmartlabs.DayDreaming.module.GeneralErrorHelperModule;
import com.xmartlabs.DayDreaming.module.GsonModule;
import com.xmartlabs.DayDreaming.module.MockRestServiceModule;
import com.xmartlabs.DayDreaming.module.OkHttpModule;
import com.xmartlabs.DayDreaming.module.PicassoModule;
import com.xmartlabs.DayDreaming.ui.common.BaseInstrumentationTest;

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
