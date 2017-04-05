package com.xmartlabs.daydreaming.helper;

import android.support.annotation.NonNull;

import com.xmartlabs.daydreaming.DayDreamingApplication;
import com.xmartlabs.daydreaming.controller.SessionController;
import com.xmartlabs.daydreaming.model.Session;

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
