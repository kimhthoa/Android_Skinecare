package com.example.thesameskincare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.thesameskincare.R;
import com.example.thesameskincare.adapter.DanhMuc_Adapter;
import com.example.thesameskincare.adapter.ResuiltSearch_Adater;
import com.example.thesameskincare.db.db_SanPham;
import com.example.thesameskincare.fragment.TrangChu_Fragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Resuilt_Search extends AppCompatActivity {

    ListView list;
    ResuiltSearch_Adater resuiltSearch_adater;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resuilt_search);

        list = (ListView) findViewById(R.id.resuilt_search_rcv);
        //db.getLoai(getApplicationContext());
        Intent intent = getIntent();
        String tukhoa = intent.getStringExtra("search");
        resuiltSearch_adater = new ResuiltSearch_Adater(getlistSearch(tukhoa), R.layout.item_resuilt_search, getApplicationContext());
        resuiltSearch_adater.notifyDataSetChanged();
        list.setAdapter(resuiltSearch_adater);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bundle(getlistSearch(tukhoa), position);
            }
        });

    }
    private ArrayList<db_SanPham> getlistSearch(String query){
        ArrayList<db_SanPham> listSearch=new ArrayList<>();
        for(int i = 0; i< TrangChu_Fragment.sanpham.size(); i++){
            if(TrangChu_Fragment.sanpham.get(i).getTenSanPham().toLowerCase().contains(query.toLowerCase())){
                listSearch.add(TrangChu_Fragment.sanpham.get(i));
            }
        }
        return listSearch;
    }
    public void bundle(ArrayList<db_SanPham> list, int i){
        Intent intent = new Intent(Resuilt_Search.this, ChiTietSanPham.class);
        db_SanPham db_chitietsp = list.get(i);
        intent.putExtra("sanpham", db_chitietsp);
        startActivity(intent);
    }


    }
