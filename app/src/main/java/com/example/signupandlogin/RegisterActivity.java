package com.example.signupandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.datastore.generated.model.User;

public class RegisterActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        EditText regConfirmPassword = findViewById(R.id.et_repassword);
//        Button reg_btn = findViewById(R.id.btn_register);
        TextView reg_tv = findViewById(R.id.to_login);

        reg_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

//        reg_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = regUsername.getText().toString();
//                String password = regPassword.getText().toString();
//                String confirm = regConfirmPassword.getText().toString();
//                String username = regName.getText().toString();
//                Database db = new Database(getApplicationContext(), "LifeAndLimbUserDatabase", null, 1);
//                if(email.length() == 0 || password.length() == 0){
//                    Toast.makeText(getApplicationContext(), "Please fill all the required details", Toast.LENGTH_SHORT).show();
//                } else {
//                    if(password.compareTo(confirm)==0){
//                        if(isValid(password)) {
//                            String saltValue = PassBasedEnc.getSaltValue(30);
//                            String passwordHash = PassBasedEnc.generateSecurePassword(password, saltValue);
//                            if(db.register(username, email, passwordHash, saltValue) == 0) {
//                                Toast.makeText(getApplicationContext(), "Response recorded. You can now login to the app", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                            } else {
//                                Toast.makeText(getApplicationContext(), "This email is already registered. Please login", Toast.LENGTH_SHORT).show();
//                            }
//
//
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having letter, digit and special characters", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Password and Confirmed Password did not match", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
    }

//    public static boolean isValid(String passwordHere) {
//        int f1 = 0;
//        int f2 = 0;
//        int f3 = 0;
//        int length = passwordHere.length();
//        if (length >= 0) {
//            for (int p = 0; p < length; p++) {
//                if (Character.isLetter(passwordHere.charAt(p))) {
//                    f1 = 1;
//                }
//            }
//            for (int r = 0; r < length; r++) {
//                if (Character.isDigit(passwordHere.charAt(r))) {
//                    f2 = 1;
//                }
//            }
//            for (int s = 0; s < length; s++) {
//                char c = passwordHere.charAt(s);
//                if (c >= 33 && c <= 46 || c == 64) {
//                    f3 = 1;
//                }
//            }
//            if (f1 == 1 && f2 == 1 && f3 == 1)
//                return true;
//        }
//        return false;
//    }

    public void onPressedRegister(View view) {
        EditText regUsername = findViewById(R.id.et_email);
        EditText regPassword = findViewById(R.id.et_password);
        EditText regConfirm = findViewById(R.id.et_repassword);
        String password = regPassword.getText().toString();
        String confirm = regConfirm.getText().toString();
        String final_pass;
        if(password.compareTo(confirm) == 0){
            final_pass = password;
            Amplify.Auth.signUp(
                    regUsername.getText().toString(),
                    final_pass,
                    AuthSignUpOptions.builder().userAttribute(
                            AuthUserAttributeKey.email(), regUsername.getText().toString()
                    ).build(),
                    this::onRegisterSuccess,
                    this::onRegisterError
            );
        } else {
            Toast.makeText(getApplicationContext(), "The password/confirmed password does not match", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void onRegisterSuccess(AuthSignUpResult authSignUpResult) {
        EditText regUsername = findViewById(R.id.et_email);
//        EditText regPassword = findViewById(R.id.et_password);
//        EditText regName = findViewById(R.id.et_name);

        Intent intent = new Intent(RegisterActivity.this, EmailConfirmationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("email", regUsername.getText().toString());
//        bundle.putString("password", regPassword.getText().toString());
//        bundle.putString("name", regName.getText().toString());
//        intent.putExtra("password", regPassword.getText().toString());
//        intent.putExtra("name", regName.getText().toString());
        intent.putExtra("MyPackage", bundle);
        startActivity(intent);
//        startActivity(new Intent(RegisterActivity.this, EmailConfirmationActivity.class));
    }

    private void onRegisterError(AuthException e) {
        this.runOnUiThread(() -> {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG)
                            .show();
                }
        );
    }
}