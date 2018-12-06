package com.example.imola.project;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class NewAdvertFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST= 1;
    private static final int RESULT_OK= -1;
    private Button mAddPhoto;
    private Button mbuttonSaveAdvert;
    private ImageView mImageView;
    private Uri mImageUri;
    private Uri uri;
    private ProgressBar mProgressBar;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_new_advert, container, false);

        mAddPhoto = view.findViewById(R.id.add_photo);
        mbuttonSaveAdvert=view.findViewById(R.id.buttonSaveAdvert);
        mImageView= view.findViewById(R.id.imageAdvert);
        mProgressBar=view.findViewById(R.id.progressBar);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });


        mbuttonSaveAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
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


    private String getFileExtension(Uri uri){
            ContentResolver cR = getActivity().getContentResolver();
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        if (mImageUri != null)
        {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()+"."+ getFileExtension(mImageUri));
            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(0);
                        }
                    }, 500);

                    Toast.makeText(getActivity(), "Upload Successful", Toast.LENGTH_LONG).show();
                    Upload upload=new Upload();
                    String uploadId =  mDatabaseRef.push().getKey();
                    mDatabaseRef.child(uploadId).setValue(upload);

                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Toast.makeText(NewAdvertFragment.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            double progress = (100.0 * taskSnapshot.getBytesTransferred()/ taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
        }

        else {
            Toast.makeText(getActivity(),"No file selected", Toast.LENGTH_SHORT).show();
        }
    }

}
