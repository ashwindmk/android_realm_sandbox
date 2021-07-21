package com.ashwin.realmsandbox;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration.Builder()
                        .deleteRealmIfMigrationNeeded()
                        .allowWritesOnUiThread(true)
                        .build();

        Realm.setDefaultConfiguration(config);
    }
}
