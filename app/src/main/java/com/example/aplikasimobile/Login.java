package com.example.aplikasimobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Login extends AppCompatActivity {
    public static final String FILENAME = "login";
    private EditText nohp;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        nohp = findViewById(R.id.nohp);
        password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);

        login.setOnClickListener(v -> login());
        register.setOnClickListener(v -> {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        });

    }

    void simpanFileLogin() {
        String isiFile = nohp.getText().toString() + ";" + password.getText().toString();
        File file = new File(getFilesDir(), FILENAME);

        try (FileOutputStream fos = new FileOutputStream(file, false)) {
            fos.write(isiFile.getBytes());
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
        //keluar dari LoginActivity
        onBackPressed();
    }

    void login(){
        File sdcard = getFilesDir();
        File file = new File(sdcard, nohp.getText().toString());

        if (file.exists()) {
            StringBuffer text = new StringBuffer();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();

                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }

            String data = text.toString();

            //split String to array String
            String[] dataUser = data.split(";");

            if (dataUser[1].equals(password.getText().toString())) {
                simpanFileLogin();
                Intent intent = new Intent(this, SplashScreenLogin.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "User Tidak Ditemukan", Toast.LENGTH_SHORT).show();
        }
    }
}