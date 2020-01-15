package com.vukihai.unisecchatbot;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.vukihai.unisecchatbot.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private Intent loginIntent, mainIntent;
    public static final int LOGIN_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loginIntent = new Intent(this, LoginActivity.class);
        startActivityForResult(loginIntent, LOGIN_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOGIN_REQUEST){
            if(resultCode == RESULT_OK) {
                mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
            } else {
                finish();
            }
        }
    }
}
