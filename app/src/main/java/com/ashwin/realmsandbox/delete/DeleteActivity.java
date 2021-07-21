package com.ashwin.realmsandbox.delete;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;

import com.ashwin.realmsandbox.R;
import com.ashwin.realmsandbox.RecordRepository;

public class DeleteActivity extends AppCompatActivity {
    private DeleteViewModel deleteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        deleteViewModel = new ViewModelProvider(this, new DeleteModule(this, null, new RecordRepository())).get(DeleteViewModel.class);

        Button deleteAllButton = findViewById(R.id.delete_all_button);
        deleteAllButton.setOnClickListener(view -> {
            deleteViewModel.deleteAll();
        });
    }
}
