package com.example.xipproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdminRecyclerAdaptor extends RecyclerView.Adapter<AdminRecyclerAdaptor.MyViewHolder> {

    public List<Asset> Asset =new ArrayList<>();
    public AdminRecyclerAdaptor(List<Asset> Asset)
    {
        this.Asset = Asset;
    }


    @NonNull
    @Override
    public AdminRecyclerAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminRecyclerAdaptor.MyViewHolder holder , int position) {

           holder.assetText.setText(Asset.get(position).getAsset_name());
            holder.assetImage.setImageResource(Asset.get(position).getAsset_image_id());



    }

    @Override
    public int getItemCount() {
        return Asset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        ImageView assetImage;
        TextView assetText;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            assetImage = itemView.findViewById(R.id.image_album);
            assetText = itemView.findViewById(R.id.Brand_name);
            cardView = itemView.findViewById(R.id.mCardView);
            cardView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu , View v , ContextMenu.ContextMenuInfo menuInfo) {

            menu.setHeaderTitle("Select any option");
            menu.add(this.getAdapterPosition(), 121, 0, "Delete the item");
            menu.add(this.getAdapterPosition(), 122, 1, "add to Under maintenance list");
        }
    }


    public void removeItem(int position)
    {
        Asset.remove(position);
        notifyDataSetChanged();


    }
}