package com.example.thesameskincare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.thesameskincare.R;
import com.example.thesameskincare.adapter.DanhMucCon_Adapter;
import com.example.thesameskincare.adapter.DanhMuc_Adapter;
import com.example.thesameskincare.db.db_DanhMuc;
import com.example.thesameskincare.db.db_SanPham;
import com.example.thesameskincare.db.db_User;
import com.example.thesameskincare.fragment.DanhMuc_Fragment;
import com.example.thesameskincare.fragment.TrangChu_Fragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DanhMucCon extends AppCompatActivity implements AdapterView.OnItemClickListener {
    DanhMucCon_Adapter dmCon;
    DanhMuc_Fragment db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhmuc_con);

        ListView lv = findViewById(R.id.danhmuc_con_lv);
        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 1);



        //db.getLoai();
        dmCon = new DanhMucCon_Adapter(DanhMuc_Fragment.lssp, R.layout.danhmuc_con_item, this);
        dmCon.notifyDataSetChanged();
        lv.setAdapter(dmCon);
        lv.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        bundle(DanhMuc_Fragment.lssp, position);

    }
    public void bundle(ArrayList<db_SanPham> list, int i){
        Intent intent = new Intent(DanhMucCon.this, ChiTietSanPham.class);
        db_SanPham db_chitietsp = list.get(i);
        intent.putExtra("sanpham", db_chitietsp);
        startActivity(intent);
    }
}
