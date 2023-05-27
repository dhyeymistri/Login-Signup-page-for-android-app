package com.example.signupandlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.result.AuthSignInResult;
import com.amplifyframework.auth.result.AuthSignUpResult;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.DataStoreItemChange;
import com.amplifyframework.datastore.generated.model.User;

public class EmailConfirmationActivity extends AppCompatActivity {
//    String currentUserId;
    Intent i;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_confirmation);

        i = getIntent();
        bundle = i.getBundleExtra("MyPackage");
    }

//    private void onError(DataStoreException e) {
//        runOnUiThread(() -> Toast
//                .makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG)
//                .show());
//    }

    public void onConfirmButtonPressed(View view) {
        EditText txtConfirmationCode = findViewById(R.id.txtConfirmationCode);

        Amplify.Auth.confirmSignUp(
                bundle.getString("email"),
                txtConfirmationCode.getText().toString(),
                this::onSuccess,
                this::onError
        );

    }

    private void onError(AuthException e) {
        runOnUiThread(() -> Toast
                .makeText(getApplicationContext(), "Did not Confirm", Toast.LENGTH_LONG)
                .show());
    }

    private void onSuccess(AuthSignUpResult authSignUpResult) {
//        reLogin();
        startActivity(new Intent(EmailConfirmationActivity.this, LoginActivity.class));
        finish();
    }

//    private void reLogin() {
//        String username = bundle.getString("email");
//        String re_password = bundle.getString("password");
//
//        Amplify.Auth.signIn(
//                username,
//                re_password,
//                this::onLoginSuccess,
//                this::onError
//        );
//        Log.i("MyAmplifyApp", "SignIn done");
//    }

//    private void onLoginSuccess(AuthSignInResult authSignInResult) {
//
//
//        Amplify.Auth.getCurrentUser(authUser -> {
//            currentUserId = authUser.getUserId();
//        }, exception -> {
//            Log.e("MyAmplifyApp", "Error getting current user", exception);
//        });
//        String check_name = bundle.getString("name");
//
//        Amplify.DataStore.save(
//                User.builder().id(currentUserId).name(check_name).build(),
//                this::onSavedSuccess,
//                this::onError
//        );
//    }

//    private <T extends Model> void onSavedSuccess(DataStoreItemChange<T> tDataStoreItemChange) {
//        Intent intent = new Intent(this, HomePageActivity.class);
//        startActivity(intent);
//    }
}