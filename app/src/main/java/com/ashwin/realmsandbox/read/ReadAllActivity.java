package com.ashwin.realmsandbox.read;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.ashwin.realmsandbox.Constant;
import com.ashwin.realmsandbox.R;
import com.ashwin.realmsandbox.RecordRepository;
import com.ashwin.realmsandbox.model.Record;
import com.ashwin.realmsandbox.model.RecordModel;

import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ReadAllActivity extends AppCompatActivity {
    private ReadAllViewModel readAllViewModel;
    private Realm mRealm;

    private TextView allRecordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Constant.TAG, "ReadAllActivity: onCreate");
        setContentView(R.layout.activity_read_all);
        readAllViewModel = new ViewModelProvider(this, new ReadAllModule(this, null, new RecordRepository())).get(ReadAllViewModel.class);

        allRecordTextView = findViewById(R.id.all_record_text_view);

        readAllViewModel.getRecordListState().observe(this, records -> {
            String s = serializeRecords(records);
            Log.d(Constant.TAG, "recordListState.onChange: " + s);
            allRecordTextView.setText(s);
        });

        mRealm = Realm.getDefaultInstance();

        Button readAllButton = findViewById(R.id.read_all_button);
        readAllButton.setOnClickListener(view -> {
            readAllViewModel.fetchAll();
        });

        Button changeOneButton = findViewById(R.id.change_one_button);
        changeOneButton.setOnClickListener(view -> {
            readAllViewModel.updateOne();
        });

        Button readOneButton = findViewById(R.id.read_one_button);
        readOneButton.setOnClickListener(view -> {
            startActivity(new Intent(ReadAllActivity.this, ReadOneActivity.class));
        });

        Log.d(Constant.TAG, "ReadAllActivity: created");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(Constant.TAG, "ReadAllActivity: onStart");
    }

    private long getId() {
        return 1L;
    }

    private String serializeRecords(List<Record> list) {
        if (list.isEmpty()) {
            return "empty";
        }
        StringBuilder sb = new StringBuilder();
        for (Record s : list) {
            sb.append(s).append("\n\n");
        }
        return sb.toString();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(Constant.TAG, "ReadAllActivity: onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(Constant.TAG, "ReadAllActivity: onDestroy");
        mRealm.close();
    }
}
