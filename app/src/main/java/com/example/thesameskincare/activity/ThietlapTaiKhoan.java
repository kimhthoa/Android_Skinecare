package com.example.thesameskincare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.thesameskincare.HoSo;
import com.example.thesameskincare.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ThietlapTaiKhoan extends AppCompatActivity {
    TextView hoso;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thiet_lap_tk);
        setTitle("Thiết lập tài khoản");

        initdata();

        Toolbar toolbar = (Toolbar) findViewById(R.id.thiet_lap_tk_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        hoso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThietlapTaiKhoan.this, HoSo.class);
                startActivity(intent);
            }
        });


    }
    public void initdata(){
        hoso = (TextView) findViewById(R.id.thiet_lap_tk_hosocuatoi);
    }
}
