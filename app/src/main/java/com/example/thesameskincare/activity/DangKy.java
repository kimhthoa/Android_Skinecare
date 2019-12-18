package com.example.thesameskincare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.example.thesameskincare.ultil.CheckConnect;
import com.example.thesameskincare.ultil.server;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static java.security.AccessController.getContext;

public class DangKy extends AppCompatActivity implements View.OnClickListener {

    View view;
    EditText hoten,username, email, matkhau, nhaplaimk;
    DangNhap db;
    TextView login;
    Button signUpButton;
    CheckBox cbDongY;
    boolean x = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);

        initdata();
        signUpButton.setOnClickListener(this);
    }
    public void initdata(){
        hoten = findViewById(R.id.dangky_HoTen);
        username = findViewById(R.id.dangky_username);
        email = findViewById(R.id.dangky_email);
        matkhau = findViewById(R.id.dangky_matkhau);
        nhaplaimk = findViewById(R.id.dangky_Nhaplaimk);
        signUpButton = findViewById(R.id.dangky_dangky);
        cbDongY = findViewById(R.id.dangky_checkbox);
        cbDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = true;
            }
        });

    }

    // Check Validation Method
    private void checkValidation() {

        // Get all edittext texts
        String getFullName = hoten.getText().toString();
        String getUserName = username.getText().toString();
        String getEmailId = email.getText().toString();
        String getPassword = matkhau.getText().toString();
        String getConfirmPassword = nhaplaimk.getText().toString();


        // Check if all strings are null or not
        if (x==false)
            cbDongY.setError("Bạn chưa đồng ý");
        else if (getFullName.equals("") || getFullName.length() == 0)
            hoten.setError("Nhập lại họ tên");
        else if (getUserName.equals("") || getUserName.length() == 0)
            username.setError("Nhập lại User name");
        else if (getEmailId.equals("") || getEmailId.length() == 0)
            email.setError("Nhập lại Email");
        else if (getPassword.equals("") || getPassword.length() == 0)
            matkhau.setError("Nhập lại mật khẩu");
        else if (getConfirmPassword.equals("") || getConfirmPassword.length() == 0)
            nhaplaimk.setError("Mật khẩu không đúng");
            // Check if both password should be equal
        else if (!getConfirmPassword.equals(getPassword))
            nhaplaimk.setError("Mật khẩu không đúng");

            // Else do signup or do your stuff
        else {
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            StringRequest stringRequest = new StringRequest(Request.Method.POST, server.DuongDanDangKy, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("id", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("username", getUserName);
                    hashMap.put("password", getPassword);
                    hashMap.put("email", getEmailId);
                    hashMap.put("hoten", getFullName);
                    return hashMap;
                }
            };
            requestQueue.add(stringRequest);
            Intent intent = new Intent(DangKy.this, DangNhap.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Do SignUp.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dangky_dangky:
                checkValidation();
                break;
        }
    }
}
