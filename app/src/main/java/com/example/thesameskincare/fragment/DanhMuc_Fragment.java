package com.example.thesameskincare.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.thesameskincare.activity.Contain_All;
import com.example.thesameskincare.activity.DanhMucCon;
import com.example.thesameskincare.adapter.DanhMuc_Adapter;
import com.example.thesameskincare.R;
import com.example.thesameskincare.db.db_DanhMuc;
import com.example.thesameskincare.db.db_SanPham;
import com.example.thesameskincare.ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DanhMuc_Fragment extends Fragment implements AdapterView.OnItemClickListener {

    //table loai


    GridView gv;
    DanhMuc_Adapter dmadapter;
    public static ArrayList<db_SanPham> lssp;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.danhmuc_fragment, container, false);
        initdata(view);
        getLoai();
        dmadapter = new DanhMuc_Adapter(getContext(), R.layout.danhmuc_item, dm1);
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
        getListDMCon(position);
        startActivity(intent);
    }
    public ArrayList<db_SanPham> getListDMCon(int i){
        lssp = new ArrayList<>();
        if(i == 0)
            lssp=TrangChu_Fragment.lsKemCKD;
        else if (i==1)
            lssp=TrangChu_Fragment.lsKemNen;
        else if (i==2)
            lssp=TrangChu_Fragment.lsSonMoi;
        else if (i==3)
            lssp=TrangChu_Fragment.lsKeMat;
        else if (i==4)
            lssp=TrangChu_Fragment.lsMaHong;
        else if (i==5)
            lssp=TrangChu_Fragment.lsDuongAm;
        else if (i==6)
            lssp=TrangChu_Fragment.lsMatNa;
        else if (i==7)
            lssp=TrangChu_Fragment.lsKemDuongMat;
        else if (i==8)
            lssp=TrangChu_Fragment.lsTayTrang;

        return lssp;
    }
    public  ArrayList<db_DanhMuc> dm1 = new ArrayList<>();
    public void getLoai() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.DuongDanLoai, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    int maloai = 0;
                    String tenloai = "";
                    String anhsp= "";
                    for (int i = 0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            maloai = jsonObject.getInt("maloai");
                            tenloai = jsonObject.getString("tenloai");
                            anhsp = jsonObject.getString("anh");
                            dm1.add(new db_DanhMuc(maloai, tenloai, anhsp));
                            dmadapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
