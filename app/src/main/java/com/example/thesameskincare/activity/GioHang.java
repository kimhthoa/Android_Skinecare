package com.example.thesameskincare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.thesameskincare.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GioHang extends AppCompatActivity implements View.OnClickListener {
    ImageView imgBack, imgMess;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang);

        initData();

        imgBack.setOnClickListener(this);
        imgMess.setOnClickListener(this);

    }
    public void initData(){
        imgBack = findViewById(R.id.giohang_fragment_imgback);
        imgMess = findViewById(R.id.giohang_fragment_imgmess);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.giohang_fragment_imgback:
                Intent intent = new Intent(GioHang.this, Contain_All.class);
                startActivity(intent);
                break;
            case R.id.giohang_fragment_imgmess: break;
        }
    }
}
