package com.example.thesameskincare.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thesameskincare.R;
import com.example.thesameskincare.activity.Contain_All;
import com.example.thesameskincare.activity.GioHang;
import com.example.thesameskincare.db.db_DanhMuc;
import com.example.thesameskincare.db.db_GioHang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GioHang_Adapter extends BaseAdapter {

    Context context;
    int  layout;
    ArrayList<db_GioHang> gioHangArrayList;

    public GioHang_Adapter(Context context, int layout, ArrayList<db_GioHang> gioHangArrayList) {
        this.context = context;
        this.layout = layout;
        this.gioHangArrayList = gioHangArrayList;
    }

    @Override
    public int getCount() {
        return gioHangArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return gioHangArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holderDM;
        if(convertView == null){
            holderDM = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holderDM.imageView = (ImageView) convertView.findViewById(R.id.giohang_img);
            holderDM.checkBox = (CheckBox) convertView.findViewById(R.id.giohang_checkbox1);
            holderDM.tvTenSP = (TextView) convertView.findViewById(R.id.giohang_item_tensp);
            holderDM.tvGiaSP = (TextView) convertView.findViewById(R.id.giohang_item_giasp);
            holderDM.btnPlus = (Button) convertView.findViewById(R.id.giohang_item_tru);
            holderDM.btbMinius = (Button) convertView.findViewById(R.id.giohang_item_tru);
            holderDM.btnsua = (Button) convertView.findViewById(R.id.giohang_item_sua);
            holderDM.sl = (EditText) convertView.findViewById(R.id.giohang_item_edtSoLuong);
            convertView.setTag(holderDM);
        }else holderDM = (ViewHolder) convertView.getTag();

        db_GioHang dm = gioHangArrayList.get(position);

        holderDM.tvTenSP.setText(dm.getTensanpham());
        holderDM.tvGiaSP.setText(dm.getGia()+"");
        holderDM.sl.setText(dm.getSoluong()+"");
        //holderDM.hinh.setImageResource(getIdAvatar(dm.getAnhsp()));
        Picasso.get()
                .load(getIdAvatar(dm.getImage()))
                .placeholder(R.drawable.loading)
                .error(R.drawable.loi)
                .fit()
                .into(holderDM.imageView);
        holderDM.btbMinius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Contain_All.gioHangs.get(position).getSoluong();
                sl--;
                Contain_All.gioHangs.get(position).setSoluong(sl);
                holderDM.sl.setText(sl + "");
                GioHang.checkGioHang();
                if (sl < 10) {
                    holderDM.btnPlus.setVisibility(View.VISIBLE);
                    holderDM.btbMinius.setVisibility(View.VISIBLE);
                }

            }
        });
        holderDM.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Contain_All.gioHangs.get(position).getSoluong();
                sl++;
                Contain_All.gioHangs.get(position).setSoluong(sl);
                holderDM.sl.setText(sl + "");
                GioHang.checkGioHang();
                if (sl > 9) {
                    holderDM.btnPlus.setVisibility(View.INVISIBLE);
                    holderDM.btbMinius.setVisibility(View.VISIBLE);
                } else {
                    holderDM.btnPlus.setVisibility(View.VISIBLE);
                    holderDM.btbMinius.setVisibility(View.VISIBLE);
                }
            }
        });
        return convertView;
    }
    static class ViewHolder{
        CheckBox checkBox;
        ImageView imageView;
        TextView tvTenSP, tvGiaSP;
        Button btnPlus,btbMinius,btnsua;
        EditText sl;
    }

    public int getIdAvatar(String name){
        String pkgName = context.getPackageName();
        // Trả về 0 nếu không tìm thấy
        int resID = context.getResources().getIdentifier(name.toLowerCase() , "drawable", pkgName);
        Log.i("CustomListView", "Res Name: "+ name+"==> Res ID = "+ resID);
        return resID;
    }
}
