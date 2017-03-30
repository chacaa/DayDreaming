package com.xmartlabs.dd;

import com.xmartlabs.dd.controller.AuthController;
import com.xmartlabs.dd.controller.Controller;
import com.xmartlabs.dd.controller.ServiceController;
import com.xmartlabs.dd.controller.SessionController;
import com.xmartlabs.dd.helper.DatabaseHelper;
import com.xmartlabs.dd.helper.GeneralErrorHelper;
import com.xmartlabs.dd.module.AndroidModule;
import com.xmartlabs.dd.module.ControllerModule;
import com.xmartlabs.dd.module.DatabaseModule;
import com.xmartlabs.dd.module.GeneralErrorHelperModule;
import com.xmartlabs.dd.module.GsonModule;
import com.xmartlabs.dd.module.OkHttpModule;
import com.xmartlabs.dd.module.PicassoModule;
import com.xmartlabs.dd.module.ReceiverModule;
import com.xmartlabs.dd.module.RestServiceModule;
import com.xmartlabs.dd.module.SessionInterceptor;
import com.xmartlabs.dd.ui.BaseActivity;
import com.xmartlabs.dd.ui.BaseAppCompatActivity;
import com.xmartlabs.dd.ui.MainActivity;
import com.xmartlabs.dd.ui.SingleFragmentActivity;
import com.xmartlabs.dd.ui.StartActivity;

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
  void inject(BaseProjectApplication baseProjectApplication);

  void inject(BaseActivity baseActivity);
  void inject(BaseAppCompatActivity baseAppCompatActivity);
  void inject(SingleFragmentActivity singleFragmentActivity);

  void inject(MainActivity mainActivity);
  void inject(StartActivity startActivity);

  void inject(Controller controller);
  void inject(ServiceController serviceController);

  void inject(AuthController authController);
  void inject(SessionController sessionController);

  void inject(SessionInterceptor sessionInterceptor);

  void inject(DatabaseHelper databaseHelper);
  void inject(GeneralErrorHelper generalErrorHelper);
}
