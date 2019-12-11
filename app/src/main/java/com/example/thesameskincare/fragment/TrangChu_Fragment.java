package com.example.thesameskincare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.thesameskincare.activity.ChiTietSanPham;
import com.example.thesameskincare.R;
import com.example.thesameskincare.adapter.RecycleViewAdapter_BigSale;
import com.example.thesameskincare.db.DTB_ALL;
import com.example.thesameskincare.db.db_SanPham;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TrangChu_Fragment extends Fragment {

    DTB_ALL db = new DTB_ALL();
    public static final String parce = "parce";
    //ArrayList<db_SanPham> listBigsale = new ArrayList<>();
    int[] arrayImg = {R.drawable.bia1, R.drawable.bia2, R.drawable.bia3, R.drawable.bia4};
    private RecycleViewAdapter_BigSale recycleViewAdapter_bigSale, recycleViewAdapter_spBanChayNhat, recycleViewAdapter_spMoiNhat;
    RecyclerView recyclerViewSale,recyclerViewMoiNhat,recyclerViewBanChayNhat;
    ArrayList<db_SanPham> spMoiNhat,spBigSale, spChay;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trangchu_fragment, container, false);

         recyclerViewSale = (RecyclerView) view.findViewById(R.id.danhmuc_fragment_rcvBigSale);
         recyclerViewMoiNhat = (RecyclerView) view.findViewById(R.id.danhmuc_fragment_rcvMoiNhat);
         recyclerViewBanChayNhat = (RecyclerView) view.findViewById(R.id.danhmuc_fragment_rcvBanChayNhat);
        ViewFlipper viewFlipper = (ViewFlipper) view.findViewById(R.id.trangchu_fragment_viewFlipper);

        for (int i=0; i<arrayImg.length; i++)
        {
            ImageView img = new ImageView(getContext());
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setImageResource(arrayImg[i]);
            viewFlipper.addView(img);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        db.getSanPham(getContext());

        Toast.makeText(getContext(), "haha"+db.sanpham.size(), Toast.LENGTH_SHORT).show();

        spBigSale = new ArrayList<db_SanPham>();
        spChay = new ArrayList<db_SanPham>();
        spMoiNhat = new ArrayList<db_SanPham>();
        getData();

        recycleViewAdapter_bigSale =  new RecycleViewAdapter_BigSale(spBigSale,getContext());
        recycleViewAdapter_spMoiNhat =  new RecycleViewAdapter_BigSale(spMoiNhat,getContext());
        recycleViewAdapter_spBanChayNhat =  new RecycleViewAdapter_BigSale(spChay,getContext());


        loadRV(recyclerViewSale,recycleViewAdapter_bigSale);
        loadRV(recyclerViewMoiNhat,recycleViewAdapter_spMoiNhat);
        loadRV(recyclerViewBanChayNhat,recycleViewAdapter_spBanChayNhat);

        recycleViewAdapter_bigSale.setOnItemClickListener(new RecycleViewAdapter_BigSale.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ChiTietSanPham.class);
                db_SanPham db_sanPham = spBigSale.get(position);
                intent.putExtra("sanpham", db_sanPham);
                startActivity(intent);
            }
        });
        recycleViewAdapter_spMoiNhat.setOnItemClickListener(new RecycleViewAdapter_BigSale.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ChiTietSanPham.class);
                db_SanPham db_sanPham = spMoiNhat.get(position);
                intent.putExtra("sanpham", db_sanPham);
                startActivity(intent);
            }
        });
        recycleViewAdapter_spBanChayNhat.setOnItemClickListener(new RecycleViewAdapter_BigSale.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ChiTietSanPham.class);
                db_SanPham db_sanPham = spChay.get(position);
                intent.putExtra("sanpham", db_sanPham);
                startActivity(intent);
            }
        });

    return view;
    }

    public void getData(){
        for(db_SanPham sp : db.sanpham){
            if(sp.getMuc().equals("sale")){
                spBigSale.add(sp);
        } else if(sp.getMuc().equals("mới")){
            spMoiNhat.add(sp);
        }else {
            spChay.add(sp);
        }
        }
    }

    //chuyển dữ liệu từ fragment Danh mục sang activity chi tiết sản phẩm
    public void bundle(ArrayList<db_SanPham> list, int i){
        Intent intent = new Intent(getContext(), ChiTietSanPham.class);
        db_SanPham db_sanPham = list.get(i);
        intent.putExtra("parcelable", db_sanPham);
        startActivity(intent);
    }

    public void loadRV(RecyclerView recyclerView,RecycleViewAdapter_BigSale adapter){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

}
