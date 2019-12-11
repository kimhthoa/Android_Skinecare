package com.example.thesameskincare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.thesameskincare.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Welcom extends AppCompatActivity {
    public static int TIME = 2000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Welcom.this, Contain_All.class);
                startActivity(intent);
                finish();
            }
        }, TIME);
    }
}
