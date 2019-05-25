package com.example.android_cilodong_latihan05_room;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.CardViewHolder> {
    private List<Siswa> siswas;
    private Context context;

    public RvAdapter(Context context) {
        this.context = context;
    }

    public List<Siswa> getListSiswa() {
        return siswas;
    }

    public void setListSiswa(List siswa) {
        this.siswas = siswa;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false);
        CardViewHolder viewHolder = new CardViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, final int position) {
        final String
                nama = getListSiswa().get(position).getNama(),
                kelas = getListSiswa().get(position).getKelas();

        final int id = getListSiswa().get(position).getId();

        holder.tvNama.setText(nama);
        holder.tvKelas.setText(kelas);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putInt("b_id", id);
                b.putString("b_nama", nama);
                b.putString("b_kelas", kelas);

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtras(b);

                //context.startActivity(intent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ((Activity) context).startActivityForResult(intent, 1, b);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return siswas.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvKelas;

        public CardViewHolder(View view) {
            super(view);
            tvNama = (TextView) itemView.findViewById(R.id.tv_nama);
            tvKelas = (TextView) itemView.findViewById(R.id.tv_kelas);
        }
    }


}
