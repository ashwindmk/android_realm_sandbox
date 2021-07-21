package com.ashwin.realmsandbox.create;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.ashwin.realmsandbox.Constant;
import com.ashwin.realmsandbox.R;
import com.ashwin.realmsandbox.model.Record;
import com.ashwin.realmsandbox.model.RecordModel;
import com.ashwin.realmsandbox.model.Skill;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import io.realm.Realm;
import io.realm.exceptions.RealmPrimaryKeyConstraintException;

public class CreateActivity extends AppCompatActivity {
    private Realm mRealm;

    private EditText idEditText;
    private EditText nameEditText;
    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mRealm = Realm.getDefaultInstance();

        idEditText = findViewById(R.id.id_edit_text);
        nameEditText = findViewById(R.id.name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);

        Button createButton = findViewById(R.id.create_button);
        createButton.setOnClickListener(view -> {
            createRecord();
        });
    }

    private void createRecord() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.d(Constant.TAG, "createRecord: executing...");
                try {
                    Long id = Long.valueOf(idEditText.getText().toString());
                    String name = nameEditText.getText().toString();
                    String email = emailEditText.getText().toString();
                    Random random = new Random();
                    int min = 1, max = 10;
                    int r1 = min + random.nextInt(max);
                    int r2 = min + random.nextInt(max);
                    List<Skill> skills = Arrays.asList(new Skill("maths", r1), new Skill("code", r2));

                    Record record = new Record(id, name);
                    record.setEmail(email);
                    record.setSkills(skills);

                    RecordModel s = RecordModel.from(record);

                    realm.copyToRealmOrUpdate(s);

                } catch (RealmPrimaryKeyConstraintException e) {
                    Log.e(Constant.TAG, "Exception while creating", e);
                } catch (Exception e) {
                    Log.e(Constant.TAG, "Unknown exception while creating", e);
                } finally {
                    Log.d(Constant.TAG, "createRecord: done");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
