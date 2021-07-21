package com.ashwin.realmsandbox;

import com.ashwin.realmsandbox.model.RecordModel;

import io.realm.Realm;
import io.realm.RealmResults;

public class RecordRepository {
    private Realm mRealm;

    public RecordRepository() {
        mRealm = Realm.getDefaultInstance();
    }

    public RealmResults<RecordModel> getAll() {
        return mRealm.where(RecordModel.class).findAll();
    }

    public void updateRecord(RecordModel record) {
        Long id = record.id;
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RecordModel s = realm.where(RecordModel.class).equalTo(RecordModel.PROPERTY_ID, id).findFirst();
                if (s != null) {
                    s.email = record.email;
                }
            }
        });
    }

    public void deleteAllRecords() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
    }

    public void onDestroy() {
        mRealm.close();
    }
}
