package com.xmartlabs.DayDreaming;

import com.xmartlabs.DayDreaming.controller.AuthController;
import com.xmartlabs.DayDreaming.controller.Controller;
import com.xmartlabs.DayDreaming.controller.ServiceController;
import com.xmartlabs.DayDreaming.controller.SessionController;
import com.xmartlabs.DayDreaming.helper.DatabaseHelper;
import com.xmartlabs.DayDreaming.helper.GeneralErrorHelper;
import com.xmartlabs.DayDreaming.module.AndroidModule;
import com.xmartlabs.DayDreaming.module.ControllerModule;
import com.xmartlabs.DayDreaming.module.DatabaseModule;
import com.xmartlabs.DayDreaming.module.GeneralErrorHelperModule;
import com.xmartlabs.DayDreaming.module.GsonModule;
import com.xmartlabs.DayDreaming.module.OkHttpModule;
import com.xmartlabs.DayDreaming.module.PicassoModule;
import com.xmartlabs.DayDreaming.module.ReceiverModule;
import com.xmartlabs.DayDreaming.module.RestServiceModule;
import com.xmartlabs.DayDreaming.module.SessionInterceptor;
import com.xmartlabs.DayDreaming.ui.BaseActivity;
import com.xmartlabs.DayDreaming.ui.BaseAppCompatActivity;
import com.xmartlabs.DayDreaming.ui.BaseFragment;
import com.xmartlabs.DayDreaming.ui.MainActivity;
import com.xmartlabs.DayDreaming.ui.SingleFragmentActivity;
import com.xmartlabs.DayDreaming.ui.StartActivity;

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
