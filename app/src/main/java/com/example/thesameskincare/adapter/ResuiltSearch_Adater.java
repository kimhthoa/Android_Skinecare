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

public class ResuiltSearch_Adater extends BaseAdapter {
    ArrayList<db_SanPham> listsp;
    int layout;
    Context context;

    public ResuiltSearch_Adater(ArrayList<db_SanPham> listsp, int layout, Context context) {
        this.listsp = listsp;
        this.layout = layout;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listsp.size();
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
        TextView ten;
        ImageView hinh;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderDM holderDM;
        if(convertView == null){
            holderDM = new ViewHolderDM();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holderDM.hinh = (ImageView) convertView.findViewById(R.id.imganhsp);
            holderDM.ten = (TextView) convertView.findViewById(R.id.edtTensp);
            convertView.setTag(holderDM);
        }else holderDM = (ViewHolderDM) convertView.getTag();

        db_SanPham dm = listsp.get(position);

        //holderDM.hinh.setImageResource(getIdAvatar(dm.getAnhsp()));
        holderDM.ten.setText(dm.getAnh());
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
        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(name.toLowerCase() , "drawable", pkgName);
        Log.i("CustomListView", "Res Name: "+ name+"==> Res ID = "+ resID);
        return resID;
    }
}
