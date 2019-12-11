package com.example.thesameskincare;

import android.os.Bundle;

import com.example.thesameskincare.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HoSo extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ho_so);
        setTitle("Hồ sơ của tôi");

        Toolbar toolbar = (Toolbar) findViewById(R.id.ho_so_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
