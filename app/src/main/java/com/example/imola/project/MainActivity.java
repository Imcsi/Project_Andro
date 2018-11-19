package com.example.imola.project;


import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment;

        fragment = getSupportFragmentManager().findFragmentByTag("FragmentHome");
        if (fragment == null) {
            fragment = new FragmentHome();
        }
        loadFragment(fragment);

    }



    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_place, fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }

}
