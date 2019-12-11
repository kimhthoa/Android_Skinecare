package com.example.thesameskincare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.thesameskincare.activity.DangKy;
import com.example.thesameskincare.activity.DangNhap;
import com.example.thesameskincare.R;
import com.example.thesameskincare.activity.ThietlapTaiKhoan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.thesameskincare.R.id.toi_fragment_dangnhap;

public class Toi_Fragment extends Fragment {
    Button btnDangNhap, btnDangky;
    TextView tltaikhoan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toi_fragment, container, false);
        initData(view);
        click();


        return view;

    }
    public void initData(View v){
        btnDangNhap = (Button) v.findViewById(toi_fragment_dangnhap);
        btnDangky = (Button) v.findViewById(R.id.toi_fragment_dangky);
        tltaikhoan = (TextView) v.findViewById(R.id.toi_fragment_thietlaptk);
    }
    public void click(){
        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DangKy.class);
                startActivity(intent);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DangNhap.class);
                startActivity(intent);
            }
        });
        tltaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ThietlapTaiKhoan.class);
                startActivity(intent);
            }
        });
    }

}
