package com.example.thesameskincare.db;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.thesameskincare.ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DTB_ALL {

    //table loai
    int maloai = 0;
    String tenloai = "";
    String anhsp= "";

    //table user
    int mauser = 0;
    String TenUser = "";
    String matKhau = "";
    String anhDD = "";
    String hoten = "";
    String gioiTinh = "";
    String diachi = "";
    String sdt = "";
    String email = "";

    //table sanpham
     int maSanPham = 0;
     String tenSanPham ="";
     String thuonghieu ="";
     int dungtich = 0;
     String mota ="";
     int dongia = 0;
     int soluongcosan = 0;
     int phivanchuyen = 0;
     String muc ="";
     String anh ="";
    int maloai1 = 0;
    int mashop = 0;

    public static ArrayList<db_DanhMuc> dm1 = new ArrayList<>();;
    public void getLoai(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.DuongDanLoai, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){

                    for (int i = 0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            maloai = jsonObject.getInt("maloai");
                            tenloai = jsonObject.getString("tenloai");
                            anhsp = jsonObject.getString("anh");
                            dm1.add(new db_DanhMuc(maloai, tenloai, anhsp));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    public static ArrayList<db_User> user  = new ArrayList<>();
    public ArrayList<db_User> getUser(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.DuongDanUser, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    user = new ArrayList<>();
                    for (int i = 0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            mauser = jsonObject.getInt("mauser");
                            TenUser = jsonObject.getString("username");
                            matKhau = jsonObject.getString("password");
                            anhDD = jsonObject.getString("anh");
                            hoten = jsonObject.getString("hoten");
                            gioiTinh = jsonObject.getString("gioitinh");
                            diachi = jsonObject.getString("diachi");
                            sdt = jsonObject.getString("sodienthoai");
                            email = jsonObject.getString("email");
                            user.add(new db_User(mauser, TenUser, matKhau, anhDD, hoten, gioiTinh, diachi, sdt, email ));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
        Toast.makeText(context, "Sẵn sàng", Toast.LENGTH_SHORT).show();
        return user;
    }

    public static ArrayList<db_SanPham> sanpham = new ArrayList<>();
    public void getSanPham(Context context) {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.DuongDanSanPham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                sanpham = new ArrayList<>();
                if(response!=null){
                    for (int i = 0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            maSanPham = jsonObject.getInt("masp");
                            tenSanPham = jsonObject.getString("tensp");
                            thuonghieu = jsonObject.getString("thuonghieu");
                            dungtich = jsonObject.getInt("dungtich");
                            mota = jsonObject.getString("mota");
                            dongia = jsonObject.getInt("dongia");
                            soluongcosan = jsonObject.getInt("soluongcosan");
                            phivanchuyen = jsonObject.getInt("phivanchuyen");
                            muc = jsonObject.getString("muc");
                            anh = jsonObject.getString("anh");
                            maloai1= jsonObject.getInt("maloai");
                            mashop = jsonObject.getInt("mashop");
                            sanpham.add(new db_SanPham(maSanPham, tenSanPham, thuonghieu, dungtich, mota, dongia, soluongcosan, phivanchuyen, muc, anh, maloai1, mashop));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, "ôi đệt", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}
