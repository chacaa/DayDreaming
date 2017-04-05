package com.xmartlabs.DayDreaming.helper;

import android.support.annotation.NonNull;

import com.xmartlabs.DayDreaming.DayDreamingApplication;
import com.xmartlabs.DayDreaming.controller.SessionController;
import com.xmartlabs.DayDreaming.model.Session;

import javax.inject.Inject;

@SuppressWarnings("unused")
public class DatabaseHelper {
  @Inject
  SessionController sessionController;

  public DatabaseHelper() {
    DayDreamingApplication.getContext().inject(this);
  }

  public void deleteAll() {
    // TODO
  }

  public void migrate(@NonNull Session session) {
    if (session.getDatabaseVersion() == null || session.getDatabaseVersion() != Session.CURRENT_DATABASE_VERSION) { // Drop even if downgrading the version.
      deleteAll();
      session.setDatabaseVersion(Session.CURRENT_DATABASE_VERSION);
      sessionController.setSession(session)
          .toCompletable()
          .blockingAwait();
    }
  }
}
