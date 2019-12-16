package com.example.thesameskincare.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thesameskincare.R;
import com.example.thesameskincare.db.db_DanhMuc;
import com.example.thesameskincare.db.db_SanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhMucCon_Adapter extends BaseAdapter {
    ArrayList<db_SanPham> listspCon;
    int layout;
    Context context;

    public DanhMucCon_Adapter(ArrayList<db_SanPham> listspCon, int layout, Context context) {
        this.listspCon = listspCon;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listspCon.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolderDM{
        TextView tensp, gia, hang;
        ImageView hinh;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderDM holderDM;
        if(convertView == null){
            holderDM = new ViewHolderDM();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holderDM.hinh = (ImageView) convertView.findViewById(R.id.item_danhmuc_img);
            holderDM.tensp = (TextView) convertView.findViewById(R.id.item_danhmuc_txtTensp);
            holderDM.gia = (TextView) convertView.findViewById(R.id.item_danhmuc_gia);
            holderDM.hang = (TextView) convertView.findViewById(R.id.item_danhmuc_hang);
            convertView.setTag(holderDM);
        }else holderDM = (ViewHolderDM) convertView.getTag();


        db_SanPham dm = listspCon.get(position);
        holderDM.hang.setText("Hãng: "+dm.getThuonghieu());
        holderDM.gia.setText(dm.getDongia()+"đ");
        holderDM.tensp.setText(dm.getTenSanPham());
        Picasso.get()
                .load(getIdAvatar(dm.getAnh()))
                .placeholder(R.drawable.loading)
                .error(R.drawable.loi)
                .fit()
                .into(holderDM.hinh);
        return convertView;
    }
    public int getIdAvatar(String name){
        String pkgName = context.getPackageName();
        // Trả về 0 nếu không tìm thấy
        int resID = context.getResources().getIdentifier(name.toLowerCase() , "drawable", pkgName);
        Log.i("CustomListView", "Res Name: "+ name+"==> Res ID = "+ resID);
        return resID;
    }
}
