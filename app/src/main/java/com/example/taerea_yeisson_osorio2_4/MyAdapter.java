package com.example.taerea_yeisson_osorio2_4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.taerea_yeisson_osorio2_4.Modelo.Firma;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Firma> data;

    public MyAdapter(List<Firma> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Firma currentItem = data.get(position);
        TextView descripcion = (TextView)holder.itemView.findViewById(R.id.textView);
        descripcion.setText(currentItem.getDescripcion());
        ImageView imageView =(ImageView) holder.itemView.findViewById(R.id.imageView);
        imageView.setImageBitmap(currentItem.getFot());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            // Inicializa tus elementos de CardView aquí
        }
    }
}