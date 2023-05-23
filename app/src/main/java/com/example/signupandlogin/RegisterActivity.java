package com.example.signupandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText regName;
    EditText regUsername;
    EditText regPassword;
    EditText regConfirmPassword;
    Button reg_btn;
    TextView reg_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regName = findViewById(R.id.et_name);
        regUsername = findViewById(R.id.et_email);
        regPassword = findViewById(R.id.et_password);
        regConfirmPassword = findViewById(R.id.et_repassword);
        reg_tv = findViewById(R.id.to_login);
        reg_btn = findViewById(R.id.btn_register);

        reg_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = regUsername.getText().toString();
                String password = regPassword.getText().toString();
                String confirm = regConfirmPassword.getText().toString();
                String username = regName.getText().toString();
                if(email.length() == 0 || password.length() == 0){
                    Toast.makeText(getApplicationContext(), "Please fill all the required details", Toast.LENGTH_SHORT).show();
                } else {
                    if(password.compareTo(confirm)==0){
                        if(isValid(password)) {

                            Toast.makeText(getApplicationContext(), "Response recorded. You can now login to the app", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having letter, digit and special characters", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password and Confirmed Password did not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValid(String passwordhere) {
        int f1 = 0;
        int f2 = 0;
        int f3 = 0;
        int length = passwordhere.length();
        if ( length < 0) {
            return false;
        } else {
            for (int p = 0; p < length; p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }for (int r = 0; r < length; r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }for (int s = 0; s < length; s++) {
                char c = passwordhere.charAt(s);
                if(c >= 33 && c <= 46 || c ==64) {
                    f3 = 1;
                }
            }
            if(f1 ==1 && f2 ==1 && f3 ==1)
                return true;
            return false;
        }
    }
}