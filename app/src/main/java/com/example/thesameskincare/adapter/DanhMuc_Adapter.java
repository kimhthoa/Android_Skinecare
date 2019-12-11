package com.example.thesameskincare.adapter;

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

public class DanhMuc_Adapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<db_DanhMuc> listdm;

    public DanhMuc_Adapter(Context context, int layout, ArrayList<db_DanhMuc> listdm) {
        this.context = context;
        this.layout = layout;
        this.listdm = listdm;
    }

    @Override
    public int getCount() {
        return listdm.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolderDM{
        TextView ten;
        TextView ma;
        ImageView hinh;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderDM holderDM;
        if(convertView == null){
            holderDM = new ViewHolderDM();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holderDM.hinh = (ImageView) convertView.findViewById(R.id.danhmuc_item_img);
            holderDM.ten = (TextView) convertView.findViewById(R.id.danhmuc_item_txtTen);
            convertView.setTag(holderDM);
        }else holderDM = (ViewHolderDM) convertView.getTag();

        db_DanhMuc dm = listdm.get(position);

         //holderDM.hinh.setImageResource(getIdAvatar(dm.getAnhsp()));
         holderDM.ten.setText(dm.getTenLoai());
        Picasso.get()
                .load(getIdAvatar(dm.getAnhsp()))
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
