package com.example.earn;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class pAdapter extends RecyclerView.Adapter<pAdapter.pViewHolder>{

    Context context;
    public pAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public pViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_tab2, parent, false);
        return new pViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), offer_detail.class);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class pViewHolder extends RecyclerView.ViewHolder{

        public pViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
