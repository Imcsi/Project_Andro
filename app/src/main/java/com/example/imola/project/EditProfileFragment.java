package com.example.imola.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditProfileFragment extends Fragment {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText phoneNumber;
    private EditText adress;



    private View view;
    private Button mSaveProfil;

    //lekerem az aktualis usert
    FirebaseUser currentUser=FirebaseAuth.getInstance().getCurrentUser();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_edit_profile, container, false);



        view.findViewById(R.id.SaveProfil).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                update();
                Toast.makeText(getContext(), "Modify Successfull", Toast.LENGTH_LONG).show();
            }
        });

        view.findViewById(R.id.signOutButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(currentUser!=null){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getContext(),SplashActivity.class ));
                    getActivity().finish();
                }
            }
        });





        view.findViewById(R.id.buttonEditProfile).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                firstName.setEnabled(true);
                lastName.setEnabled(true);
                phoneNumber.setEnabled(true);
                adress.setEnabled(true);
                email.setEnabled(true);
                //mSaveProfil.setVisibility(View.VISIBLE);

            }
        });


        // mSaveProfil=  view.findViewById(R.id.SaveProfil);
        //FirebaseAuth.getInstance().getCurrentUser().getDisplayName();










       firstName= view.findViewById(R.id.userFirstName);
       lastName= view.findViewById(R.id.userLastNameP);
       phoneNumber= view.findViewById(R.id.phoneNumber);
       adress=view.findViewById(R.id.UserAdress);
       email=view.findViewById(R.id.userEmail);
//       mSaveProfil.setVisibility(View.VISIBLE);
       //mChooseImage.setVisibility(View.VISIBLE);




        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference databaseref = database.getReference("user");

        if(currentUser == null)
        {
            Toast.makeText(getContext(), "Please, sign in!", Toast.LENGTH_SHORT).show();
        }
        else {
            databaseref.child(currentUser.getPhoneNumber()).addValueEventListener(new ValueEventListener() {


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    User user =dataSnapshot.getValue(User.class);
                    System.out.println(user+"AAAAAAAA");
                    phoneNumber.setText(user.getUphoneNumber());
                    Log.d("user", user.getUphoneNumber().toString());
                    lastName.setText(user.getUlastName());
                    Log.d("user", user.getUlastName().toString());
                    firstName.setText(user.getUfirstName());
                    Log.d("user", user.getUfirstName());
                    //email.setText(user.getUemail());
                   // adress.setText(user.getUadress());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.w("","Failed to read value", databaseError.toException());
                }
            });
        }


        // Inflate the layout for this fragment
        return view;
    }


    private void update(){
        String firName = firstName.getText().toString();
        String lstName= lastName.getText().toString();
        String eml=email.getText().toString();
        String phnNumber = phoneNumber.getText().toString();
        String adr = adress.getText().toString();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseref = database.getReference("user").child(phnNumber);



        if (currentUser == null) {
            Toast.makeText(getContext(), "Kerem jelentkezzen be", Toast.LENGTH_SHORT).show();
        } else {

             User user=new User(firName, lstName, eml, phnNumber, adr);

            databaseref.setValue(user);
        }


    }



}
