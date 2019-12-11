package com.example.thesameskincare.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.thesameskincare.R;
import com.example.thesameskincare.db.db_SanPham;
import com.example.thesameskincare.db.db_User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HoSo extends AppCompatActivity {
    db_User db_user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ho_so);

        //nhận dữ liệu từ activity DangNhap
        Intent intent = getIntent();
        db_user = (db_User) intent.getSerializableExtra("parcelable" );
    }
}
