package com.example.thesameskincare.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.thesameskincare.R;
import com.example.thesameskincare.adapter.RecycleViewAdapter_DanhGiaSP;
import com.example.thesameskincare.db.db_DanhGiaSP;
import com.example.thesameskincare.db.db_SanPham;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.View.INVISIBLE;

public class ChiTietSanPham extends AppCompatActivity  {
    TextView phivc, slcosan, mota, thuonghieu, dungtich, gia;
    EditText edtBinhLuan;
    ImageView imgSend, img;
    RecyclerView rvdanhgia;
    Button chonmua, chonmuascroll;
    ScrollView scrollView;
    db_SanPham sanpham;
    public ArrayList<db_DanhGiaSP> listdg = new ArrayList<>();
//    RecycleViewAdapter_DanhGiaSP recycleViewAdapter_danhGiaSP;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietsanpham);

        initData();

        //nhận dữ liệu từ Trang chủ
        getBundlesp();
        //đỗ dữ liệu ra
        setDuLieu();

        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.chitietsanpham_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);
        rvdanhgia.setLayoutManager(linearLayout);

//        recycleViewAdapter_danhGiaSP = new RecycleViewAdapter_DanhGiaSP(listdg, this);
//        rvdanhgia.setAdapter(recycleViewAdapter_danhGiaSP);

        chonmuascroll.setVisibility(View.INVISIBLE);
        ScrollView scrollView = findViewById(R.id.chitietsanpham_scroll);
        Rect scrollBounds = new Rect();
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                if (!chonmua.getLocalVisibleRect(scrollBounds)) {
                    chonmuascroll.setVisibility(View.VISIBLE);
                }else chonmuascroll.setVisibility(INVISIBLE);
            }
        });

        //imgSend.setOnClickListener(this);
    }

    public void initData(){
//        edtBinhLuan = (EditText) findViewById(R.id.chitietsanpham_edtbinhluan);
//        imgSend = (ImageView) findViewById(R.id.chitietsanpham_imgsend);

        img = (ImageView) findViewById(R.id.chitietsanpham_img);
        phivc = (TextView)findViewById(R.id.chitietsanpham_phivanchuyen);
        thuonghieu = (TextView)findViewById(R.id.chitietsanpham_thuonghieu);
        dungtich = (TextView)findViewById(R.id.chitietsanpham_dungtich);
        mota = (TextView)findViewById(R.id.chitietsanpham_txtmota);
        gia = (TextView) findViewById(R.id.chitietsanpham_txtGia);
        slcosan = (TextView)findViewById(R.id.chitietsanpham_soluongsosan);
        rvdanhgia = (RecyclerView) findViewById(R.id.chitietsanpham_lv_danhgia);
        chonmua = (Button) findViewById(R.id.chitietsanpham_btn_chonmua);
        chonmuascroll = (Button) findViewById(R.id.chitietsanpham_btn_scrollchonmua);

    }


    public void getBundlesp(){
        //3 intent
        Intent intent = getIntent();
        sanpham = (db_SanPham) intent.getSerializableExtra("sanpham" );
    }
    public String getNgThN(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        dateFormat.setLenient(false);
        Date today = new Date();
        String s = dateFormat.format(today);
        return s;
    }
    public int getIdAvatar(String name){
        String pkgName = getApplication().getPackageName();
        // Trả về 0 nếu không tìm thấy.
        int resID = getApplication().getResources().getIdentifier(name.toLowerCase() , "drawable", pkgName);
        Log.i("CustomListView", "Res Name: "+ name+"==> Res ID = "+ resID);
        return resID;
    }
    public void setDuLieu(){
        setTitle(sanpham.getTenSanPham());
        phivc.setText(sanpham.getPhivanchuyen()+"");
        slcosan.setText(sanpham.getSoluongcosan()+"");
        thuonghieu.setText(sanpham.getThuonghieu());
        dungtich.setText(sanpham.getDungtich()+"");
        mota.setText(sanpham.getMota());
        gia.setText(sanpham.getDongia()+"");

    }
}
