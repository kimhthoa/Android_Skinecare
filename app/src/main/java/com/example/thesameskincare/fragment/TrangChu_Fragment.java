package com.example.thesameskincare.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.thesameskincare.activity.ChiTietSanPham;
import com.example.thesameskincare.R;
import com.example.thesameskincare.adapter.RecycleViewAdapter_sale;
import com.example.thesameskincare.adapter.RecycleViewAdapter_sanpham;
import com.example.thesameskincare.db.db_SanPham;
import com.example.thesameskincare.ultil.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TrangChu_Fragment extends Fragment {

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
    int giamgia = 0;
    public static ArrayList<db_SanPham> sanpham = new ArrayList<>();

    int[] arrayImg = {R.drawable.bia1, R.drawable.bia2, R.drawable.bia3, R.drawable.bia4};
    private RecycleViewAdapter_sanpham  recycleViewAdapter_spBanChayNhat, recycleViewAdapter_spMoiNhat;
    private RecycleViewAdapter_sale recycleViewAdapter_bigSale;
    RecyclerView recyclerViewSale,recyclerViewMoiNhat,recyclerViewBanChayNhat;
    static ArrayList<db_SanPham> spMoiNhat,spBigSale, spChay, lsKemCKD, lsKemNen, lsSonMoi, lsKeMat, lsMaHong, lsDuongAm, lsMatNa, lsKemDuongMat, lsTayTrang;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trangchu_fragment, container, false);

         recyclerViewSale = (RecyclerView) view.findViewById(R.id.danhmuc_fragment_rcvBigSale);
         recyclerViewMoiNhat = (RecyclerView) view.findViewById(R.id.danhmuc_fragment_rcvMoiNhat);
         recyclerViewBanChayNhat = (RecyclerView) view.findViewById(R.id.danhmuc_fragment_rcvBanChayNhat);

        ViewFlipper viewFlipper = (ViewFlipper) view.findViewById(R.id.trangchu_fragment_viewFlipper);

        //slideShow
        for (int i=0; i<arrayImg.length; i++)
        {
            ImageView img = new ImageView(getContext());
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setImageResource(arrayImg[i]);
            viewFlipper.addView(img);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        spBigSale = new ArrayList<db_SanPham>();
        spChay = new ArrayList<db_SanPham>();
        spMoiNhat = new ArrayList<db_SanPham>();

        recycleViewAdapter_bigSale =  new RecycleViewAdapter_sale(spBigSale, R.layout.sale_item, getContext());
        recycleViewAdapter_spMoiNhat =  new RecycleViewAdapter_sanpham(spMoiNhat, R.layout.sanpham, getContext());
        recycleViewAdapter_spBanChayNhat =  new RecycleViewAdapter_sanpham(spChay, R.layout.sanpham, getContext());
        getSanPham();
        getData();

        loadsale(recyclerViewSale,recycleViewAdapter_bigSale);
        loadRV(recyclerViewMoiNhat,recycleViewAdapter_spMoiNhat);
        loadRV(recyclerViewBanChayNhat,recycleViewAdapter_spBanChayNhat);

        recycleViewAdapter_bigSale.setOnItemClickListener(new RecycleViewAdapter_sanpham.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ChiTietSanPham.class);
                db_SanPham db_sanPham = spBigSale.get(position);
                intent.putExtra("sanpham", db_sanPham);
                startActivity(intent);
            }
        });
        recycleViewAdapter_spMoiNhat.setOnItemClickListener(new RecycleViewAdapter_sanpham.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), ChiTietSanPham.class);
                db_SanPham db_sanPham = spMoiNhat.get(position);
                intent.putExtra("sanpham", db_sanPham);
                startActivity(intent);
            }
        });
        recycleViewAdapter_spBanChayNhat.setOnItemClickListener(new RecycleViewAdapter_sanpham.onItemClickListener() {
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
        for(db_SanPham sp : sanpham){
            if(sp.getMuc().equals("sale")){
                spBigSale.add(sp);
        } else if(sp.getMuc().equals("mới")){
            spMoiNhat.add(sp);
        }else {
            spChay.add(sp);
        }
        }
    }

    public void loadRV(RecyclerView recyclerView, RecycleViewAdapter_sanpham adapter){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
    public void loadsale(RecyclerView recyclerView, RecycleViewAdapter_sale adapter){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public void getSanPham() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(server.DuongDanSanPham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                sanpham = new ArrayList<>();
                lsKemCKD = new ArrayList<>();
                lsKemNen = new ArrayList<>();
                lsSonMoi = new ArrayList<>();
                lsKeMat = new ArrayList<>();
                lsMaHong = new ArrayList<>();
                lsDuongAm = new ArrayList<>();
                lsMatNa = new ArrayList<>();
                lsKemDuongMat = new ArrayList<>();
                lsTayTrang = new ArrayList<>();
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
                            giamgia = jsonObject.getInt("giamgia");
                            sanpham.add(new db_SanPham(maSanPham, tenSanPham, thuonghieu, dungtich, mota, dongia, soluongcosan, phivanchuyen, muc, anh, maloai1, mashop, giamgia));
                            if (maloai1 == 1) {

                                lsKemCKD.add(sanpham.get(i));
                            }else if (maloai1 == 2){

                                lsKemNen.add(sanpham.get(i));
                            }

                            else if (maloai1 == 3){

                                lsSonMoi.add(sanpham.get(i));
                            }
                            else if (maloai1 == 4){

                                lsKeMat.add(sanpham.get(i));
                            }
                            else if (maloai1 == 5){

                                lsMaHong.add(sanpham.get(i));
                            }
                            else if (maloai1 == 6){

                                lsDuongAm.add(sanpham.get(i));
                            }
                            else if (maloai1 == 7){

                                lsMatNa.add(sanpham.get(i));
                            }
                            else if (maloai1 == 8){

                                lsKemDuongMat.add(sanpham.get(i));
                            }
                            else if (maloai1 == 9){

                                lsTayTrang.add(sanpham.get(i));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    recycleViewAdapter_bigSale.notifyDataSetChanged();
                    recycleViewAdapter_spBanChayNhat.notifyDataSetChanged();
                    recycleViewAdapter_spMoiNhat.notifyDataSetChanged();
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
