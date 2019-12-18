package com.example.thesameskincare.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.thesameskincare.R;
import com.example.thesameskincare.adapter.GioHang_Adapter;
import com.example.thesameskincare.db.db_GioHang;
import com.example.thesameskincare.ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
                }else {


                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    StringRequest stringRequest =new StringRequest(Request.Method.POST, server.DuongDanGioHang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("Mahoadon", madonhang);
                            if(Integer.parseInt(madonhang) > 0){
                                RequestQueue queue = Volley.newRequestQueue(GioHang.this);
                                StringRequest request = new StringRequest(Request.Method.POST, server.DuongDanChiTietGioHang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.d("ChiTiethaha", response);
                                        if(response.equals("1") ){
                                            Contain_All.gioHangs.clear();
                                            Contain_All.gioHangs.size();
                                            Toast.makeText(GioHang.this, "Bạn đã thêm dữ liệu vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(GioHang.this,Contain_All.class);
                                            startActivity(intent);
                                            Toast.makeText(GioHang.this, "Mời bạn tiếp tực mua hàng", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(GioHang.this, "Lỗi", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (db_GioHang gh : Contain_All.gioHangs){
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("masp",gh.getIdSanpham());
                                                jsonObject.put("soluong",gh.getSoluong());
                                                jsonObject.put("magh",madonhang);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap = new HashMap<String, String>();
                                        hashMap.put("json", String.valueOf(jsonArray));
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String , String> hashMap = new HashMap<String,String>();
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = new Date();

                            hashMap.put("mauser", String.valueOf(Contain_All.user.getMaUser()));
                            hashMap.put("ngaymua", format.format(date));
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                    Toast.makeText(this, "Bạn đã đặt hàng thành công", Toast.LENGTH_SHORT).show();
                }

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
