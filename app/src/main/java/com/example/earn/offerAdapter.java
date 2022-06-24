package com.example.earn;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class offerAdapter extends RecyclerView.Adapter<offerAdapter.oViewHolder> {

    List<offers> list;

    public offerAdapter(List<offers> list){
        this.list = list;
    }

    @NonNull
    @Override
    public oViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item, parent,false);
        return new oViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull oViewHolder holder, int position) {

        holder.Bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class oViewHolder extends RecyclerView.ViewHolder{

        public oViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void Bind(offers offers) {
            ImageView imageView = itemView.findViewById(R.id.offer_image);
            imageView.setBackgroundResource(offers.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), detail_screen.class);
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }
}
