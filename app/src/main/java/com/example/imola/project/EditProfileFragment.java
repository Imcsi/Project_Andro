package com.example.imola.project;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class EditProfileFragment extends Fragment {

    private View view;
    private Button mSaveProfil,mChooseImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_edit_profile, container, false);
        Button mEditProfile=view.findViewById(R.id.buttonEditProfile) ;
        mSaveProfil=  view.findViewById(R.id.SaveProfil);
        mChooseImage=view.findViewById(R.id.chooseProfImageButton);
        //FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        mEditProfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                view.findViewById(R.id.userFirstName).setEnabled(true);
                view.findViewById(R.id.userLastNameP).setEnabled(true);
                view.findViewById(R.id.phoneNumber).setEnabled(true);
                view.findViewById(R.id.UserAdress).setEnabled(true);
                view.findViewById(R.id.userEmail).setEnabled(true);
                mSaveProfil.setVisibility(View.VISIBLE);
                mChooseImage.setVisibility(View.VISIBLE);

            }
        });


        // Inflate the layout for this fragment
        return view;
    }


}
