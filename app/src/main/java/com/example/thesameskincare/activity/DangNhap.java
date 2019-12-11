package com.example.thesameskincare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.thesameskincare.R;
import com.example.thesameskincare.db.DTB_ALL;
import com.example.thesameskincare.db.db_SanPham;
import com.example.thesameskincare.db.db_User;
import com.example.thesameskincare.fragment.TrangChu_Fragment;
import com.example.thesameskincare.ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DangNhap extends AppCompatActivity implements View.OnClickListener {

    DTB_ALL db ;
    Button btnDangNhap, btnDangKy, btnQuenMK;
    EditText edtUser, edtPass;

    int maUser = 0;
    String tenUser ="";
    String matkhau = "";
    String email = "";
    ArrayList<db_User> listu;
    //table user
    int mauser = 0;
    String TenUser = "";
    String matKhau = "";
    String anhDD = "";
    String hoten = "";
    String gioiTinh = "";
    String diachi = "";
    String sdt = "";
    String temail = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);

        initData();
        //listuser= new ArrayList<>();
        getUser();

        btnDangNhap.setOnClickListener(this);
        btnDangKy.setOnClickListener(this);
        btnQuenMK.setOnClickListener(this);
    }
    public void initData(){
        btnDangNhap = findViewById(R.id.dangnhap_btndangnhap);
        btnDangKy = findViewById(R.id.dangnhap_btndangky);
        btnQuenMK = findViewById(R.id.dangnhap_btnquenmk);
        edtUser  = findViewById(R.id.dangnhap_edtUser);
        edtPass = findViewById(R.id.dangnhap_edtPass);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dangnhap_btndangnhap:
                checkLognIn();
                break;
            case R.id.dangnhap_btndangky:
                Intent intent1 = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent1);
                break;
            case R.id.dangnhap_btnquenmk:

                break;
        }
    }
    public void checkLognIn(){
        Toast.makeText(this, "userName: " + user.size(), Toast.LENGTH_SHORT).show();
        String tname = edtUser.getText().toString() ;
        String tpass = edtPass.getText().toString();
        listu =  new ArrayList<>();
        for(int i=0; i<user.size(); i++){
            if(tname.equals(user.get(i).getTenUser())){
                listu.add(user.get(i));
            }
        }
        if (listu.size() != 1)
            edtUser.setError("Tên đăng nhập không đúng!");
        else  if (listu.size() == 1){
            if (tpass.equals(user.get(0).getMatKhau())){
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DangNhap.this, Contain_All.class );
                startActivity(intent);

            }
            else {
                edtPass.setError("Sai mật khẩu!");
            }
        }
    }
    public static ArrayList<db_User> user  = new ArrayList<>();
    public void getUser() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.DuongDanUser, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(getApplicationContext(), "User " + response, Toast.LENGTH_SHORT).show();
                if(response!=null){
                    user = new ArrayList<>();
                    for (int i = 0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            mauser = jsonObject.getInt("mauser");
                            TenUser = jsonObject.getString("username");
                            matKhau = jsonObject.getString("password");
                            anhDD = jsonObject.getString("anhdd");
                            hoten = jsonObject.getString("hoten");
                            gioiTinh = jsonObject.getString("gioitinh");
                            diachi = jsonObject.getString("diachi");
                            sdt = jsonObject.getString("sodienthoai");
                            temail = jsonObject.getString("email");
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
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    //Truyền dữ liệu từ activity Dangnhap tới activity HoSo
    public void bundle(ArrayList<db_User> list, int i){
        Intent intent = new Intent(getApplicationContext(), HoSo.class);
        db_User db_user = list.get(i);
        intent.putExtra("parcelable", db_user);
        startActivity(intent);
    }

}
