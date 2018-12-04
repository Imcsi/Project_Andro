package com.example.imola.project;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class NewAdvertFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST= 1;
    private static final int RESULT_OK= -1;
    private Button mAddPhoto;
    private Button mbuttonSaveAdvert;
    private ImageView mImageView;
    private Uri mImageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_new_advert, container, false);

        mAddPhoto = view.findViewById(R.id.add_photo);
        mbuttonSaveAdvert=view.findViewById(R.id.buttonSaveAdvert);
        mImageView= view.findViewById(R.id.imageView);

        mAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });


        mbuttonSaveAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;


    }

    private void  openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null &&
                data.getData() != null){
            mImageUri = data.getData();

            Picasso.with(getActivity()).load(mImageUri).into(mImageView);

        }
    }
}

