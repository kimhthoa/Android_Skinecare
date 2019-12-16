package com.example.thesameskincare.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thesameskincare.R;
import com.example.thesameskincare.db.db_SanPham;
import com.example.thesameskincare.db.db_User;

import org.w3c.dom.Text;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class HoSo extends AppCompatActivity {
    db_User db_user;
    TextView hoten, tendn, gioitinh, ngsinh, sdt, email;
    CircleImageView anhdd;
    Contain_All db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ho_so);

        initdata();
        getDuLieu();

    }
    public void initdata(){
        hoten = findViewById(R.id.hosoHoTen);
        tendn = findViewById(R.id.hosotendn);
        gioitinh = findViewById(R.id.hosogioitinh);
        ngsinh = findViewById(R.id.hosoNgaySinh);
        sdt = findViewById(R.id.hosoSDT);
        email = findViewById(R.id.hoso_email);
        anhdd = findViewById(R.id.hoso_anhDaiDien);

    }
    public void getDuLieu(){
        anhdd.setImageResource(getIdAvatar(db.user.getAnh()));
        hoten.setText(db.user.getHoten());
        tendn.setText(db.user.getTenUser());
        gioitinh.setText(db.user.getGioiTinh());
        sdt.setText(db.user.getSdt());
        email.setText(db.user.getEmail());
    }
    public int getIdAvatar(String name){
        String pkgName = getApplication().getPackageName();
        // Trả về 0 nếu không tìm thấy
        int resID = getApplication().getResources().getIdentifier(name.toLowerCase() , "drawable", pkgName);
        Log.i("CustomListView", "Res Name: "+ name+"==> Res ID = "+ resID);
        return resID;
    }
}
