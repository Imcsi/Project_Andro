package com.example.imola.project;


import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static final String TAG =" " ;
    private Fragment fragment;

    //private ArrayList<String> mNames= new ArrayList<>();
    //private ArrayList<String> mImageUrls= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bAdd, bHome,bProfile;


       /* fragment = getSupportFragmentManager().findFragmentByTag("FragmentHome");
        if (fragment == null) {
            fragment = new FragmentHome();
        }
        loadFragment(fragment);
*/

        bAdd=findViewById(R.id.buttonAdd);
        bAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fragment=getSupportFragmentManager().findFragmentByTag("EditAdvertFragment");
                if (fragment == null) {
                    fragment = new EditAdvertFragment();
                }
                loadFragment(fragment);
            }
        });
        bProfile=findViewById(R.id.buttonProfile);
        bProfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fragment=getSupportFragmentManager().findFragmentByTag("FragmentProfile");
                if (fragment == null) {
                    fragment = new FragmentProfile();
                }
                loadFragment(fragment);
            }
        });
        bHome=findViewById(R.id.buttonHome);
        bHome.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fragment=getSupportFragmentManager().findFragmentByTag("FragmentHome");
                if (fragment == null) {
                    fragment = new FragmentHome();
                }
                loadFragment(fragment);
            }
        });
        //initImageBitmaps();


    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_place, fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }

}
