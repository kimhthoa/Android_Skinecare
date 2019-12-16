package com.example.thesameskincare.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thesameskincare.R;
import com.example.thesameskincare.adapter.GioHang_Adapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GioHang extends AppCompatActivity implements View.OnClickListener {
    ImageView imgBack, imgMess;
    CheckBox checkBox;
    static TextView txtGia;
    Button thanhToan;
    static GioHang_Adapter gioHang_adapter;
    static int count;
    ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giohang);

        initData();
        checkGioHang();
        imgBack.setOnClickListener(this);
        imgMess.setOnClickListener(this);
        thanhToan.setOnClickListener(this);

    }
    public void initData(){
        imgBack = findViewById(R.id.giohang_fragment_imgback);
        imgMess = findViewById(R.id.giohang_fragment_imgmess);
        listView = (ListView) findViewById(R.id.giohang);
        checkBox = findViewById(R.id.giohang_fragment_checkbox);
        txtGia =(TextView) findViewById(R.id.giohang_fragment_giatien);
        thanhToan = findViewById(R.id.giohang_fragment_btnMuahang);
        gioHang_adapter = new GioHang_Adapter(getApplicationContext(),R.layout.giohang_item, Contain_All.gioHangs);
        listView.setAdapter(gioHang_adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.giohang_fragment_imgback:
                Intent intent = new Intent(GioHang.this, Contain_All.class);
                startActivity(intent);
                break;
            case R.id.giohang_fragment_imgmess: break;
            case R.id.giohang_fragment_btnMuahang:
                if(Contain_All.checklogin == false)
                {
                    Intent intent1 = new Intent(GioHang.this, DangNhap.class);
                    startActivity(intent1);
                }else Toast.makeText(this, "Bạn đã đặt hàng thành công", Toast.LENGTH_SHORT).show();

        }
    }
    public static void checkGioHang() {
        if (Contain_All.gioHangs.size() > 0) {
            int sum = 0; count = 0;
            for (int i = 0; i < Contain_All.gioHangs.size(); i++) {
                if (Contain_All.gioHangs.get(i).getSoluong() == 0) {
                    Contain_All.gioHangs.remove(i);
                    gioHang_adapter.notifyDataSetChanged();
                } else{
                    count++;
                    sum += Contain_All.gioHangs.get(i).getSoluong() * Contain_All.gioHangs.get(i).getGia();
                }
            }
            if (Contain_All.gioHangs.size() > 0) {
            }


            txtGia.setText(sum + "");
        } else {
            txtGia.setText(0 + "");
        }
    }
}
