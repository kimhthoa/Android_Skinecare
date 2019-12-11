package com.example.thesameskincare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thesameskincare.R;
import com.example.thesameskincare.db.DTB_ALL;
import com.example.thesameskincare.db.db_User;
import com.example.thesameskincare.fragment.TrangChu_Fragment;

import java.util.ArrayList;

public class DangNhap extends AppCompatActivity implements View.OnClickListener {

    DTB_ALL db ;
    Button btnDangNhap, btnDangKy, btnQuenMK;
    EditText edtUser, edtPass;

    int maUser = 0;
    String tenUser ="";
    String matkhau = "";
    String email = "";
    ArrayList<db_User> listuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);

        initData();
        listuser= new ArrayList<>();
       // getListUser();
        //getUser();

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
        Toast.makeText(this, "userName: " + db.getUser(getApplicationContext()).get(0).getTenUser(), Toast.LENGTH_SHORT).show();
        String tname = edtUser.getText().toString() ;
        String tpass = edtPass.getText().toString();
        int kt = 0, x=-1;
        for(int i=0; i<db.user.size(); i++){
            if(tname.equals(db.user.get(i).getTenUser())){
                kt =1;
                x = i;
                break;
            }
        }
        if (kt!= 1)
            edtUser.setError("Tên đăng nhập không đúng!");
        else  if (kt==0){
            if (tpass.equals(db.user.get(x).getMatKhau())){
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DangNhap.this, Contain_All.class );
                startActivity(intent);

            }
            else {
                edtPass.setError("Sai mật khẩu!");
            }
        }



    }
}
