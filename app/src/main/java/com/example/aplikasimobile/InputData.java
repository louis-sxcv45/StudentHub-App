package com.example.aplikasimobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplikasimobile.helper.DbHelper;

public class InputData extends AppCompatActivity {
    EditText txt_id, txt_number, txt_name, txt_birth, txt_gender, txt_address;
    Button btn_submit, btn_cancel;
    DbHelper SQLite = new DbHelper(this);
    String id, number, name, birth, gender, address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_data);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        txt_id = findViewById(R.id.txt_id);
        txt_number = findViewById(R.id.txt_number);
        txt_name = findViewById(R.id.txt_name);
        txt_birth = findViewById(R.id.txt_birth);
        txt_gender = findViewById(R.id.txt_gender);
        txt_address = findViewById(R.id.txt_address);
        btn_submit = findViewById(R.id.btn_submit);
        btn_cancel = findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(DataView.TAG_ID);
        number = getIntent().getStringExtra(DataView.TAG_NUMBER);
        name = getIntent().getStringExtra(DataView.TAG_NAME);
        birth = getIntent().getStringExtra(DataView.TAG_BIRTH);
        gender = getIntent().getStringExtra(DataView.TAG_GENDER);
        address = getIntent().getStringExtra(DataView.TAG_ADDRESS);

        if (id == null || id.equals("")) {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_number.setText(number);
            txt_name.setText(name);
            txt_birth.setText(birth);
            txt_gender.setText(gender);
            txt_address.setText(address);
        }

        btn_submit.setOnClickListener(v -> {
            try {
                if (txt_id.getText().toString().equals("")) {
                    save();
                } else {
                    edit();
                }
            } catch (Exception e) {
                Log.e("Submit", e.toString()); // Ubah log disini
            }
        });

        btn_cancel.setOnClickListener(v -> {
            blank();
            finish();
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(InputData.this, Dashboard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void blank() {
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_number.setText(null);
        txt_name.setText(null);
        txt_birth.setText(null);
        txt_gender.setText(null);
        txt_address.setText(null);
    }

    private void save() {
        if (txt_number.getText().toString().isEmpty() || txt_name.getText().toString().isEmpty() || txt_birth.getText().toString().isEmpty() || txt_gender.getText().toString().isEmpty()
                || txt_address.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(txt_number.getText().toString().trim(), txt_name.getText().toString().trim(), txt_birth.getText().toString().trim(),
                    txt_gender.getText().toString().trim(), txt_address.getText().toString().trim());
            blank();
            finish();
        }
    }

    private void edit() {
        if (txt_number.getText().toString().isEmpty() || txt_name.getText().toString().isEmpty() || txt_birth.getText().toString().isEmpty() || txt_gender.getText().toString().isEmpty()
                || txt_address.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Please input name or address ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()), txt_number.getText().toString().trim(), txt_name.getText().toString().trim(), txt_birth.getText().toString().trim(),
                    txt_gender.getText().toString().trim(), txt_address.getText().toString().trim());
            blank();
            finish();
        }
    }

}