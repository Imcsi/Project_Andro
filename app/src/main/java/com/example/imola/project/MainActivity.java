package com.example.imola.project;


import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SpinnerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ChangeFragment(View view)
    {
        Fragment fragment;

        if (view == findViewById(R.id.button1))
        {
<<<<<<< Updated upstream
            fragment = new FragmentOne();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            ft.replace(R.id.fragment_place, fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.button2))
        {
            fragment = new FragmentTwo();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            ft.replace(R.id.fragment_place, fragment);
            ft.commit();
        }
=======
            @Override
            public void onClick(View v)
            {
                fragment=getSupportFragmentManager().findFragmentByTag("NewAdvertFragment");
                if (fragment == null) {
                    fragment = new NewAdvertFragment();
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
                fragment=getSupportFragmentManager().findFragmentByTag("EditProfileFragment");
                if (fragment == null) {
                    fragment = new EditProfileFragment();
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

    }





    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_place, fragment, fragment.getClass().getSimpleName());
        ft.commit();
>>>>>>> Stashed changes
    }


}
