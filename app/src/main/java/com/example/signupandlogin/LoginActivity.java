package com.example.signupandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.core.Amplify;

public class LoginActivity extends AppCompatActivity {

    EditText logUsername;
    EditText logPassword;
    Button log_btn;
    TextView log_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//        if(sharedPreferences.getBoolean("logged", true)) {
//            startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
//            finish();
//        }

        logUsername = findViewById(R.id.et_email);
        logPassword = findViewById(R.id.et_password);
        log_btn = findViewById(R.id.btn_login);
        log_tv = findViewById(R.id.to_register);

//        IMPLEMENTING LOGIN BASED ON SQLITE DATABASE--------------------------------------------------------------------------------------------
//        log_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//        log_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = logUsername.getText().toString();
//                String password = logPassword.getText().toString();
//                Database db = new Database(getApplicationContext(), "LifeAndLimbUserDatabase", null, 1);
//                if(username.length() == 0 || password.length() == 0){
//                    Toast.makeText(getApplicationContext(), "Please fill all the required details", Toast.LENGTH_SHORT).show();
//                } else {
//                    if(db.login(username, password) == 1) {
//                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
//                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("username", username);
//                        editor.putBoolean("logged", true);
//                        editor.apply() ; //to save our data with key and value
//                        startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
//                        finish();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Invalid E-mail/Password", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//
//            }
//        });


        log_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    public void onLoginPressed(View v){
        Amplify.Auth.signIn(
                logUsername.getText().toString(),
                logPassword.getText().toString(),
                this::onLoginSuccess,
                this::onLoginError
        );
    }

    private void onLoginSuccess(AuthSignInResult authSignInResult) {
        //Redirect to the Home Page
        startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
        finish();
    }

    private void onLoginError(AuthException e) {
        this.runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }

}
