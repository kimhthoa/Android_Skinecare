package com.example.thesameskincare.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thesameskincare.R;
import com.example.thesameskincare.db.db_SanPham;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapter_sale extends RecyclerView.Adapter<RecycleViewAdapter_sale.ViewHolder_BigSale>{
    ArrayList<db_SanPham> listSale;
    int layout;
    Context context;
    private RecycleViewAdapter_sanpham.onItemClickListener mListener;

    @NonNull
    @Override
    public ViewHolder_BigSale onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(layout, parent, false);
        return new ViewHolder_BigSale(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_BigSale holder, int position) {
        holder.imgBigsale.setImageResource(getIdAvatar(listSale.get(position).getAnh()));
        holder.txtmota.setText(listSale.get(position).getTenSanPham());
        holder.txtgia.setText(listSale.get(position).getDongia() + "đ");
        holder.txtsale.setText("-" + listSale.get(position).getGiamgia()+"%");
    }

    @Override
    public int getItemCount() {
        return listSale.size();
    }

    public interface onItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(RecycleViewAdapter_sanpham.onItemClickListener listener){
        mListener = listener;
    }

    public RecycleViewAdapter_sale(ArrayList<db_SanPham> listSale, int layout, Context context) {
        this.listSale = listSale;
        this.layout = layout;
        this.context = context;
    }

    public static class ViewHolder_BigSale extends RecyclerView.ViewHolder{
        ImageView imgBigsale;
        TextView txtmota, txtgia, txtsale;
        public ViewHolder_BigSale(@NonNull View itemView, final RecycleViewAdapter_sanpham.onItemClickListener listener) {
            super(itemView);
            imgBigsale = (ImageView) itemView.findViewById(R.id.bigsale_item_img);
            txtmota = (TextView) itemView.findViewById(R.id.bigsale_txtMota);
            txtgia = (TextView) itemView.findViewById(R.id.bigsale_txtgia);
            txtsale = (TextView) itemView.findViewById(R.id.sale_item_sale);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
    public int getIdAvatar(String name){
        String pkgName = context.getPackageName();
        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(name.toLowerCase() , "drawable", pkgName);
        Log.i("CustomListView", "Res Name: "+ name+"==> Res ID = "+ resID);
        return resID;
    }
}
