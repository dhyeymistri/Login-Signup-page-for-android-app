package com.example.signupandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.result.AuthResetPasswordResult;
import com.amplifyframework.core.Amplify;

public class ResetPasswordActivity0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password0);
    }

    public void onPressedSendVerificationCode(View view) {
        EditText email = findViewById(R.id.et_reset_email);

        Amplify.Auth.resetPassword(
                email.getText().toString(),
                this::onSuccess,
                this::onError
        );
    }

    private void onError(AuthException e) {
        this.runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }

    private void onSuccess(AuthResetPasswordResult authResetPasswordResult) {
        EditText email = findViewById(R.id.et_reset_email);

//        Toast.makeText(getApplicationContext(), "Verification Code sent", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ResetPasswordActivity0.this, ResetPasswordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("email", email.getText().toString());
        intent.putExtra("MyPackageReset", bundle);
        startActivity(intent);
        finish();
    }
}