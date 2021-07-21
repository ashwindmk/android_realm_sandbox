package com.ashwin.realmsandbox.read;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.ashwin.realmsandbox.Constant;
import com.ashwin.realmsandbox.R;
import com.ashwin.realmsandbox.model.RecordModel;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ReadOneActivity extends AppCompatActivity {
    private Realm mRealm;

    private TextView oneRecordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_one);
        Log.d(Constant.TAG, "ReadOneActivity: onCreate");

        mRealm = Realm.getDefaultInstance();

        oneRecordTextView = findViewById(R.id.one_record_text_view);

        Button readOneButton = findViewById(R.id.read_one_button);
        readOneButton.setOnClickListener(view -> {
            readOneRecord(getId());
        });

        Button changeOneButton = findViewById(R.id.change_one_button);
        changeOneButton.setOnClickListener(view -> {
            changeOneRecord(getId());
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constant.TAG, "ReadOneActivity: onStart");
    }

    private Long getId() {
        return 1L;
    }

    private void readOneRecord(Long id) {
        Log.d(Constant.TAG, "readOneRecord: " + id + ": executing...");
        RealmResults<RecordModel> record = mRealm.where(RecordModel.class)
                .equalTo(RecordModel.PROPERTY_ID, id)
                .findAll();
        if (!record.isEmpty()) {
            Log.d(Constant.TAG, "readOneRecord: found: " + record.first());
            record.addChangeListener(new RealmChangeListener<RealmResults<RecordModel>>() {
                @Override
                public void onChange(RealmResults<RecordModel> records) {
                    RecordModel s = records.first();
                    Log.d(Constant.TAG, "readOneRecord.onChange: " + s);
                    oneRecordTextView.setText(record.first().toString());
                }
            });
            oneRecordTextView.setText(record.first().toString());
        } else {
            Log.e(Constant.TAG, "readOneRecord: empty for id: " + id);
        }

//        mRealm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                Log.d(Constant.TAG, "readOneRecord: executing...");
//                RealmResults<Student> record = realm.where(Student.class).equalTo("id", id).findAll();
//                record.addChangeListener(new RealmChangeListener<RealmResults<Student>>() {
//                    @Override
//                    public void onChange(RealmResults<Student> students) {
//                        Student s = students.first();
//                        Log.d(Constant.TAG, "readOneRecord.onChange: " + s);
//                    }
//                });
//            }
//        });
    }

    private void changeOneRecord(Long id) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RecordModel s = realm.where(RecordModel.class).equalTo(RecordModel.PROPERTY_ID, id).findFirst();
                if (s != null) {
                    int r = new Random().nextInt(9);
                    s.email = "alice" + r + "@email.com";
                    oneRecordTextView.setText(s.toString());
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constant.TAG, "ReadOneActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constant.TAG, "ReadOneActivity: onDestroy");
        mRealm.close();
    }
}
