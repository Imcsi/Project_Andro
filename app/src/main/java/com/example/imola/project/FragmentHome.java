package com.example.imola.project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment {

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

}
