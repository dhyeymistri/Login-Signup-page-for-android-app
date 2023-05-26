package com.example.signupandlogin;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Amplify.Auth.getCurrentUser(authUser -> {
            currentUser = authUser.getUserId();
        }, exception -> {
            Log.e("MyAmplifyApp", "Error getting current user", exception);
        });
//        AuthUser currentUser = Amplify.Auth.currentAuthenticatedUser();

        Intent intent;
        if(currentUser == null) {
            //Go to login page
            intent = new Intent(getApplicationContext(), LoginActivity.class);
        } else {
            //Go to Home page
            intent = new Intent(getApplicationContext(), HomePageActivity.class);
        }

        //Start Activity
        startActivity(intent);
        finish();
    }
}