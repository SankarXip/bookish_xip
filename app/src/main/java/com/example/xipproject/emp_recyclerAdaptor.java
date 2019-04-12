package com.example.xipproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class emp_recyclerAdaptor extends RecyclerView.Adapter<emp_recyclerAdaptor.MyViewHolder> {

   // private final String CHANNEL_ID="Personal Notification";
    //public static final int NOTIFICATION_ID=001;


    public List <Asset> Asset = new ArrayList <>();

    public emp_recyclerAdaptor(List <Asset> Asset) {
        this.Asset = Asset;
    }

    @NonNull
    @Override
    public emp_recyclerAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.asset_list , parent , false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull emp_recyclerAdaptor.MyViewHolder holder , int position) {
        holder.assetText.setText(Asset.get(position).getAsset_name());
        holder.assetImage.setImageResource(Asset.get(position).getAsset_image_id());
        //holder.bookImage.setImageResource(Asset.get(position).getBook_image());

    }

    @Override
    public int getItemCount() {
        return Asset.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        ImageView assetImage;
        TextView assetText;
        CardView cardView;
       // ImageView bookImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            assetImage = itemView.findViewById(R.id.image_album);
            assetText = itemView.findViewById(R.id.Brand_name);
            //bookImage=itemView.findViewById(R.id.ibtn_book);
            cardView = itemView.findViewById(R.id.mCardViewEmp);
            cardView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu , View v , ContextMenu.ContextMenuInfo menuInfo)
        {


            menu.add(this.getAdapterPosition() , 121 , 0 , "Conform Booking");

        }

    }
    public void removedItem(int position)
    {

        Asset.remove(position);
        notifyDataSetChanged();


    }


}

