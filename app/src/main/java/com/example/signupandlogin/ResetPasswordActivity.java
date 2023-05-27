package com.example.signupandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.core.Amplify;

public class ResetPasswordActivity extends AppCompatActivity {

    Intent i;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        i = getIntent();
        bundle = i.getBundleExtra("MyPackageReset");
    }

    public void onPressedResetPassword(View view) {
        EditText password = findViewById(R.id.et_new_password);
        EditText rePassword = findViewById(R.id.et_new_repassword);
        EditText verificationCode = findViewById(R.id.et_verification_code);

        String pass_string = password.getText().toString();
        String re_pass_string = rePassword.getText().toString();

        if(pass_string.compareTo(re_pass_string) == 0) {
            Amplify.Auth.confirmResetPassword(
                    bundle.getString("email"),
                    pass_string,
                    verificationCode.getText().toString(),
                    this::onSuccess,
                    this::onError
            );
        } else {
            Toast.makeText(getApplicationContext(), "The password/confirmed password does not match", Toast.LENGTH_SHORT)
                    .show();
        }



    }

    private void onError(AuthException e) {
        this.runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        });
    }

    private void onSuccess() {
//        EditText password = findViewById(R.id.et_new_password);
        this.runOnUiThread(() -> {
            Toast.makeText(getApplicationContext(), "Verification Code correct. You may now login with the new password.", Toast.LENGTH_SHORT).show();
        });
        startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
        finish();

//        Amplify.Auth.updatePassword(
//                "existingPassword",
//                password.getText().toString(),
//                () -> Log.i("AuthQuickstart", "Updated password successfully"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
    }
}