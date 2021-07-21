package com.ashwin.realmsandbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ashwin.realmsandbox.create.CreateActivity;
import com.ashwin.realmsandbox.delete.DeleteActivity;
import com.ashwin.realmsandbox.read.ReadActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createButton = findViewById(R.id.create_button);
        createButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, CreateActivity.class));
        });

        Button readButton = findViewById(R.id.read_button);
        readButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ReadActivity.class));
        });

        Button deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, DeleteActivity.class));
        });
    }
}