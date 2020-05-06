package com.vukihai.unisecchatbot.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vukihai.unisecchatbot.R;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        getSupportActionBar().hide();
    }
}
