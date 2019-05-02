package com.example.xipproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class recyclerAdaptor extends RecyclerView.Adapter<recyclerAdaptor.Myclass>
{
    Context c;
    ArrayList<GetterSetter> al;

    public recyclerAdaptor(Context c, ArrayList<GetterSetter> al) {
        this.c = c;
        this.al = al;
    }
    @NonNull
    @Override
    public Myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new Myclass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Myclass holder, int position)
    {
        GetterSetter gl=al.get(position);


        holder.name.setText(gl.getName());
        holder.assetName.setText(gl.getAssetName());
        holder.date.setText(gl.getDate());
        holder.startatime.setText(gl.getStartTime());
        holder.endTime.setText(gl.getEndTime());
        holder.id.setText(gl.getId());

    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class Myclass extends RecyclerView.ViewHolder
    {

        TextView name,assetName,date,startatime,endTime,id;

        public Myclass(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id_id);
            name=itemView.findViewById(R.id.name_id);
            assetName=itemView.findViewById(R.id.assetName_id);
            date=itemView.findViewById(R.id.date_id);
            startatime=itemView.findViewById(R.id.startTime_id);
            endTime=itemView.findViewById(R.id.endTime_id);
        }
    }

}
