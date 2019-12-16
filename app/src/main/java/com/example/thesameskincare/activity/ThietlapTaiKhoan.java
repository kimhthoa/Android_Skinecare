package com.example.thesameskincare.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.thesameskincare.activity.HoSo;
import com.example.thesameskincare.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ThietlapTaiKhoan extends AppCompatActivity implements View.OnClickListener {
    TextView hoso;
    Button dangxuat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thiet_lap_tk);
        setTitle("Thiết lập tài khoản");

        initdata();

        Toolbar toolbar = (Toolbar) findViewById(R.id.thiet_lap_tk_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dangxuat.setOnClickListener(this);

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
        dangxuat = findViewById(R.id.dangxuat);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dangxuat:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Đăng xuất");
                dialog.setMessage("Bạn có muốn đăng xuất không?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1 = new Intent(ThietlapTaiKhoan.this, DangNhap.class);
                        Contain_All.checklogin = false;
                        startActivity(intent1);
                    }
                });
                dialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog1 = dialog.create();
                dialog1.show();
        }
    }
}
