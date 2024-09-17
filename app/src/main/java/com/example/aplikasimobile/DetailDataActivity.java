package com.example.aplikasimobile;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailDataActivity extends AppCompatActivity {
    TextView txt_number, txt_name, txt_birth, txt_gender, txt_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_data);

        txt_number = findViewById(R.id.txt_number);
        txt_name = findViewById(R.id.txt_name);
        txt_birth = findViewById(R.id.txt_birth);
        txt_gender = findViewById(R.id.txt_gender);
        txt_address = findViewById(R.id.txt_address);

        // Menerima data dari Intent
        String id = getIntent().getStringExtra(DataView.TAG_ID);
        String number = getIntent().getStringExtra(DataView.TAG_NUMBER);
        String name = getIntent().getStringExtra(DataView.TAG_NAME);
        String birth = getIntent().getStringExtra(DataView.TAG_BIRTH);
        String gender = getIntent().getStringExtra(DataView.TAG_GENDER);
        String address = getIntent().getStringExtra(DataView.TAG_ADDRESS);

        // Set data ke TextView
        txt_number.setText(number);
        txt_name.setText(name);
        txt_birth.setText(birth);
        txt_gender.setText(gender);
        txt_address.setText(address);
    }
}