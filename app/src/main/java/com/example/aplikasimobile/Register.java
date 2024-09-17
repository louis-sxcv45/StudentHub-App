package com.example.aplikasimobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;

public class Register extends AppCompatActivity {
    private EditText email;
    private EditText password;
    EditText[] ets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        Button register = findViewById(R.id.register);

        ets = new EditText[] {
                email,
                password,
        };
        register.setOnClickListener(v ->{
            if(isValidation()){
                simpanFileData();
            }
            else {
                Toast.makeText(this, "Mohon Lengkapi Seluruh Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean isValidation(){
        for(EditText et : ets){
            if(et.getText().toString().isEmpty()){
                return false;
            }
        }
        return true;
    }

    void simpanFileData() {
        String isiFile = "";
        for(EditText et : ets) {
            isiFile += et.getText().toString() + ";";
        }

        File file = new File(getFilesDir(), email.getText().toString());

        try (FileOutputStream fos = new FileOutputStream(file, false)) {
            fos.write(isiFile.getBytes());
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}