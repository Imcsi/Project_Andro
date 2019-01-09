package com.example.imola.project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class FragmentAdvertising extends Fragment {
    private ConstraintLayout click;
    private Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        /*View view = inflater.inflate(R.layout.fragment_advertising, container, false);
        click= view.findViewById(R.id.frameLayout7);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = getSupportFragmentManager().findFragmentByTag( "FragmentHome" );
                if (fragment == null) {
                    fragment = new FragmentHome();
                }
                loadFragment( fragment );
            }
        });*/


        return inflater.inflate(R.layout.fragment_advertising, container, false);
    }
}
