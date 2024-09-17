package com.example.aplikasimobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Dashboard extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        Button viewData = findViewById(R.id.viewData);
        Button inputData = findViewById(R.id.inputData);
        Button about = findViewById(R.id.about);

        viewData.setOnClickListener(v->{
            Intent intent = new Intent(this, DataView.class);
            startActivity(intent);
            finish();
        });

        inputData.setOnClickListener(v->{
            Intent intent = new Intent(this, InputData.class);
            startActivity(intent);
            finish();
        });

        about.setOnClickListener(v->{
            Intent intent = new Intent(Dashboard.this, AboutApplication.class);
            startActivity(intent);
            finish();
        });
    }
}