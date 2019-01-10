package com.example.imola.project;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG= "RecyclerViewAdapter";
    private ArrayList<Advertiser>advertisers;
    private Advertiser advertiser;

    public RecyclerViewAdapter(ArrayList<Advertiser> advertisers) {
        this.advertisers = advertisers;

    }


    @NonNull

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        advertiser = advertisers.get(i);
        viewHolder.m_aTitle.setText(advertiser.getaTitle());
        viewHolder.m_details.setText(advertiser.getDetails());
        //viewHolder.m_checkNumber.setText(advertiser.getCheckNumber().toString());
        // viewHolder.m_aImage.(advertiser.getaImage());


    }

    @Override
    public int getItemCount() {
        return advertisers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView m_aTitle,m_details,m_checkNumber;
        ImageView m_aImage,m_profileImage;
        private RecyclerViewAdapter context= RecyclerViewAdapter.this;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            m_aTitle= itemView.findViewById(R.id.AdvertTitle);
            m_details= itemView.findViewById(R.id.SomeDetail);
            m_checkNumber=itemView.findViewById(R.id.checkNumber);
            m_aImage=itemView.findViewById(R.id.imageAdver);
            m_profileImage=itemView.findViewById(R.id.imageUser);
        }

       /* loadImageByResourceId();
        private void loadImageByResourceId() {
            int resourceId=R.mipmap.ic_launcher;
            Glide
                    .with(context)
                    .load(resourceId)
                    .into(m_aImage);
        }*/

    }




}