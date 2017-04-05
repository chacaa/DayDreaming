package com.xmartlabs.daydreaming;

import com.xmartlabs.daydreaming.controller.AuthController;
import com.xmartlabs.daydreaming.controller.Controller;
import com.xmartlabs.daydreaming.controller.ServiceController;
import com.xmartlabs.daydreaming.controller.SessionController;
import com.xmartlabs.daydreaming.helper.DatabaseHelper;
import com.xmartlabs.daydreaming.helper.GeneralErrorHelper;
import com.xmartlabs.daydreaming.module.AndroidModule;
import com.xmartlabs.daydreaming.module.ControllerModule;
import com.xmartlabs.daydreaming.module.DatabaseModule;
import com.xmartlabs.daydreaming.module.GeneralErrorHelperModule;
import com.xmartlabs.daydreaming.module.GsonModule;
import com.xmartlabs.daydreaming.module.OkHttpModule;
import com.xmartlabs.daydreaming.module.PicassoModule;
import com.xmartlabs.daydreaming.module.ReceiverModule;
import com.xmartlabs.daydreaming.module.RestServiceModule;
import com.xmartlabs.daydreaming.module.SessionInterceptor;
import com.xmartlabs.daydreaming.ui.BaseActivity;
import com.xmartlabs.daydreaming.ui.BaseAppCompatActivity;
import com.xmartlabs.daydreaming.ui.BaseFragment;
import com.xmartlabs.daydreaming.ui.MainActivity;
import com.xmartlabs.daydreaming.ui.SingleFragmentActivity;
import com.xmartlabs.daydreaming.ui.StartActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
    AndroidModule.class,
    ControllerModule.class,
    DatabaseModule.class,
    GeneralErrorHelperModule.class,
    GsonModule.class,
    OkHttpModule.class,
    PicassoModule.class,
    ReceiverModule.class,
    RestServiceModule.class,
})
public interface ApplicationComponent {
  void inject(DayDreamingApplication DayDreamingApplication);

  void inject(BaseActivity baseActivity);
  void inject(BaseAppCompatActivity baseAppCompatActivity);
  void inject(SingleFragmentActivity singleFragmentActivity);

  void inject(MainActivity mainActivity);
  void inject(StartActivity startActivity);

  void inject(BaseFragment baseFragment);

  void inject(Controller controller);
  void inject(ServiceController serviceController);

  void inject(AuthController authController);
  void inject(SessionController sessionController);

  void inject(SessionInterceptor sessionInterceptor);

  void inject(DatabaseHelper databaseHelper);
  void inject(GeneralErrorHelper generalErrorHelper);
}
