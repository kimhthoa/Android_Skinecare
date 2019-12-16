package com.example.thesameskincare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thesameskincare.activity.Contain_All;
import com.example.thesameskincare.activity.DangKy;
import com.example.thesameskincare.activity.DangNhap;
import com.example.thesameskincare.R;
import com.example.thesameskincare.activity.ThietlapTaiKhoan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.thesameskincare.R.id.toi_fragment_dangnhap;

public class Toi_Fragment extends Fragment {
    Button btnDangNhap, btnDangky;
    TextView tltaikhoan, danhgia;
    CircleImageView avar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.toi_fragment, container, false);
        initData(view);
        click();
        checkDangNhap();
        return view;

    }
    public void initData(View v){
        btnDangNhap = (Button) v.findViewById(toi_fragment_dangnhap);
        btnDangky = (Button) v.findViewById(R.id.toi_fragment_dangky);
        tltaikhoan = (TextView) v.findViewById(R.id.toi_fragment_thietlaptk);
        avar = (CircleImageView) v.findViewById(R.id.toi_fragment_avatar);
        danhgia = (TextView) v.findViewById(R.id.toi_fragment_DanhGia);
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
                if(Contain_All.checklogin == true){
                    Intent intent = new Intent(getContext(), ThietlapTaiKhoan.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(getContext(), DangNhap.class);
                    startActivity(intent);
                }

            }
        });
        danhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Contain_All.checklogin == true){
                    Toast.makeText(getActivity(), "Đây là trang đánh giá của bạn", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getContext(), DangNhap.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void checkDangNhap(){
        if(Contain_All.checklogin == true){
            btnDangky.setVisibility(View.INVISIBLE);
            btnDangNhap.setVisibility(View.INVISIBLE);

        }else avar.setVisibility(View.INVISIBLE);
    }

}
