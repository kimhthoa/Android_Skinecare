package com.example.thesameskincare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.thesameskincare.activity.Contain_All;
import com.example.thesameskincare.activity.DanhMucCon;
import com.example.thesameskincare.adapter.DanhMuc_Adapter;
import com.example.thesameskincare.R;
import com.example.thesameskincare.db.DTB_ALL;
import com.example.thesameskincare.db.db_DanhMuc;
import com.example.thesameskincare.db.db_SanPham;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DanhMuc_Fragment extends Fragment implements AdapterView.OnItemClickListener {

     public static ArrayList<db_SanPham> listkenchekd;
    GridView gv;
    DanhMuc_Adapter dmadapter;
    DTB_ALL db = new DTB_ALL();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.danhmuc_fragment, container, false);
        initdata(view);

        db.getLoai(getContext());
        dmadapter = new DanhMuc_Adapter(getContext(), R.layout.danhmuc_item, DTB_ALL.dm1);
        dmadapter.notifyDataSetChanged();
        listkenchekd = new ArrayList<>();
        gv.setAdapter(dmadapter);
        gv.setOnItemClickListener(this);
        return view;
    }
    public void initdata(View v){
        gv = (GridView) v.findViewById(R.id.danhmuc_fragment_gridview);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getContext(), DanhMucCon.class);
        shareData(DTB_ALL.dm1.get(position).getMaLoai());
        startActivity(intent);
    }
    public ArrayList<db_SanPham> shareData(int ml) {
        ArrayList<db_SanPham> listsp = db.sanpham;
        for (int i = 0; i < listsp.size(); i++) {
            if (listsp.get(i).getMaloai() == ml) {
                listkenchekd.add(listsp.get(i));
            }
        }
        return listkenchekd;
    }
}
