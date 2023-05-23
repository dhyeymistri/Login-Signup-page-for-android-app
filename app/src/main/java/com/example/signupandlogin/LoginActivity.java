package com.example.signupandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText logUsername, logPassword;
    Button log_btn;
    TextView log_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logUsername = findViewById(R.id.et_email);
        logPassword = findViewById(R.id.et_password);
        log_btn = findViewById(R.id.btn_login);
        log_tv = findViewById(R.id.to_register);

        log_btn

    }
}