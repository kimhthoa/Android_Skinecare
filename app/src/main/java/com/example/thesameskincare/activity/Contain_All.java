package com.example.thesameskincare.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.thesameskincare.R;
import com.example.thesameskincare.db.db_GioHang;
import com.example.thesameskincare.db.db_SanPham;
import com.example.thesameskincare.fragment.DanhMuc_Fragment;
import com.example.thesameskincare.fragment.ThongBao_Fragment;
import com.example.thesameskincare.fragment.Toi_Fragment;
import com.example.thesameskincare.fragment.TrangChu_Fragment;
import com.example.thesameskincare.ultil.CheckConnect;
import com.example.thesameskincare.ultil.server;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Contain_All extends AppCompatActivity implements View.OnClickListener {



    BottomNavigationView bnv;
    ImageView imgGioHang, imgMess;
    Button btnsearch;
    public static ArrayList<db_GioHang> gioHangs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contain_all);

        initData();

        //if(CheckConnect.haveNetworkConnection(getApplicationContext())){
            bnv = (BottomNavigationView) findViewById(R.id.contain_all_bottomNavigation);
            bnv.setOnNavigationItemSelectedListener(navListenr);
            getSupportFragmentManager().beginTransaction().replace(R.id.contain_all_framelayout, new TrangChu_Fragment()).commit();
            //getListUser();
        //}else {
            //CheckConnect.showToatshoft(getApplicationContext(), "Vui lòng kiểm tra lại kết nối");
            //finish();
       // }
        imgGioHang.setOnClickListener(this);
        imgMess.setOnClickListener(this);
        btnsearch.setOnClickListener(this);



    }

    private void getListUser() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //Nhảy vào json đọc dữ liệu
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.DuongDanUser, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //nếu Json trả về có giá trị thi...
                if(response != null){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnect.showToatshoft(getApplicationContext(), error.toString());
            }
        });
    }

    public BottomNavigationView.OnNavigationItemSelectedListener navListenr = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("ResourceType")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectMent = null;
            switch (menuItem.getItemId()){
                case R.id.menu_trangchu:
                    selectMent = new TrangChu_Fragment();
                    break;
                case R.id.menu_danhmuc:
                    selectMent = new DanhMuc_Fragment();
                    break;
                case R.id.menu_thongbao:
                    selectMent = new ThongBao_Fragment();
                    break;
                case R.id.menu_toi:
                    selectMent = new Toi_Fragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.contain_all_framelayout, selectMent).commit();
            return true;
        }
    };
    public void initData(){
        imgGioHang = findViewById(R.id.contain_all_btngiohang);
        imgMess = findViewById(R.id.contain_all_btnmess);
        btnsearch = (Button) findViewById(R.id.Contain_All_btnSearch);
        gioHangs = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.contain_all_btngiohang:
                Intent intent = new Intent(Contain_All.this, GioHang.class );
                startActivity(intent);
                break;
            case R.id.Contain_All_btnSearch:
                Intent intentSearch = new Intent(Contain_All.this, Search.class );
                startActivity(intentSearch);
                break;
        }
    }

}
