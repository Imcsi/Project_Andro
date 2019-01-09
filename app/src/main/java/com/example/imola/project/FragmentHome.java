package com.example.imola.project;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {

    RecyclerView mrecyclerView;
     FirebaseDatabase mFirebaseDatatbase;
    DatabaseReference mRef;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        mrecyclerView=view.findViewById(R.id.recyclerV);
        mrecyclerView.setHasFixedSize( true );
        mContext=container.getContext();
        mrecyclerView.setLayoutManager( new LinearLayoutManager( mContext ) );
        mFirebaseDatatbase=FirebaseDatabase.getInstance();
        mRef=mFirebaseDatatbase.getReference("adverts");

        return view;

    }


    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Upload,ViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<Upload, ViewHolder>( Upload.class, R.layout.layout_listitem, ViewHolder.class, mRef ) {
            @Override

            protected void populateViewHolder(ViewHolder viewHolder, Upload model, int position) {
                viewHolder.setDetails(mContext, model.getmName(),model.getmTitle(),model.getmShort_Description(),model.getmLong_Description(),model.getmPhone_Number(),model.getmLocation_Text() );
            }
        };
        mrecyclerView.setAdapter( firebaseRecyclerAdapter );

    }


/*
    private RecyclerView recyclerView;
    private ArrayList<Advertiser> advertisers;
    private RecyclerViewAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);
                advertisers = new ArrayList<Advertiser>();

        adapter = new RecyclerViewAdapter(advertisers);
        recyclerView = view.findViewById(R.id.recyclerV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        uploadDatas();
        adapter.notifyDataSetChanged();
        return view;
    }


    private void uploadDatas()
    {

        for(int i = 0; i < 100;++i)
        {
            advertisers.add(new Advertiser("jhlekjf", "jfek",5,"hcikjs","jdow"));
        }

    }
*/
}
