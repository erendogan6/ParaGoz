package com.example.paragoz.adaptor;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paragoz.databinding.RowlayoutBinding;
import com.example.paragoz.model.liste;

public class adaptor extends RecyclerView.Adapter<adaptor.RowHolder> {
    private final com.example.paragoz.model.liste liste;
    public adaptor(liste liste) {
        this.liste = liste;
    }
    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowlayoutBinding rowlayoutBinding = RowlayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new RowHolder(rowlayoutBinding);
    }
    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.rowlayoutBinding.textName.setText(liste.getDataList().get(position).name);
        holder.rowlayoutBinding.textPrice.setText("Satış: "+liste.getDataList().get(position).selling+" ₺");
        holder.rowlayoutBinding.textPrice2.setText("Alış: "+liste.getDataList().get(position).buying+" ₺");
        if (position%2==0){
            holder.rowlayoutBinding.sutun.setBackgroundColor(Color.parseColor("#222831"));
        }
        else {
            holder.rowlayoutBinding.sutun.setBackgroundColor(Color.parseColor("#393E46"));
        }
    }
    @Override
    public int getItemCount() {
        return liste.getDataList().size();
    }
    public static class RowHolder extends RecyclerView.ViewHolder{
        final RowlayoutBinding rowlayoutBinding;
        public RowHolder(@NonNull RowlayoutBinding rowlayoutBinding) {
            super(rowlayoutBinding.getRoot());
            this.rowlayoutBinding = rowlayoutBinding;
        }
    }
}