package com.ashwin.realmsandbox.read;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ashwin.realmsandbox.R;

public class ReadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        Button readAllButton = findViewById(R.id.read_all_button);
        readAllButton.setOnClickListener(view -> {
            startActivity(new Intent(ReadActivity.this, ReadAllActivity.class));
        });

        Button readOneButton = findViewById(R.id.read_one_button);
        readOneButton.setOnClickListener(view -> {
            startActivity(new Intent(ReadActivity.this, ReadOneActivity.class));
        });
    }
}
