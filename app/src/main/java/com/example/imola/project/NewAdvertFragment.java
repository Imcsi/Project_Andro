package com.example.imola.project;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class NewAdvertFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int RESULT_OK = -1;
    private  String imageStorageUrl;
    private EditText mTitle;
    private EditText mShort_Description;
    private EditText mLong_Description;
    private EditText mPhone_Number;
    private EditText mLocation_Text;

    private Button mAddPhoto;
    private Button mbuttonSaveAdvert;
    private ImageView mImageView;
    private Upload upload;
    private Uri downloadUri ;

    private ProgressBar mProgressBar;  //??kell?

    private Uri mImageUri;

    private Integer advertNumber = 0;
    FirebaseUser currentUser=FirebaseAuth.getInstance().getCurrentUser();


    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {






        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_new_advert, container, false);

        mAddPhoto = view.findViewById(R.id.add_photo);
        mbuttonSaveAdvert = view.findViewById(R.id.buttonSaveAdvert);
        mImageView = view.findViewById(R.id.imageAdvert);
        mProgressBar = view.findViewById(R.id.progressBar);  //??


        mTitle = view.findViewById(R.id.title);
        mShort_Description = view.findViewById(R.id.short_description);
        mLong_Description = view.findViewById(R.id.long_description);
        mPhone_Number = view.findViewById(R.id.phone_number);
        mLocation_Text = view.findViewById(R.id.location_text);

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("adverts");


        mAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
                //imageUpload();
            }
        });



        mbuttonSaveAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadAdvert();
            }
        });


        return view;


    }





    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null &&
                data.getData() != null) {


            mImageUri = data.getData();

            Picasso.with(getActivity()).load(mImageUri).into(mImageView);

        }
    }


    private String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    private void uploadAdvert() {


        String imageStorageName;
        String advert_Title = mTitle.getText().toString();
        String advert_Short_Description = mShort_Description.getText().toString();
        String advert_Long_Description = mLong_Description.getText().toString();
        String advert_Phone_Number = mPhone_Number.getText().toString();
        String advert_Location_Text = mLocation_Text.getText().toString();

        if (!TextUtils.isEmpty(advert_Title) && !TextUtils.isEmpty(advert_Short_Description) && !TextUtils.isEmpty(advert_Phone_Number) &&
                !TextUtils.isEmpty(advert_Long_Description) && !TextUtils.isEmpty(advert_Location_Text) && mImageUri != null) {

            imageStorageName=System.currentTimeMillis() + "." + "jpg";
            final StorageReference fileReference = mStorageRef.child(imageStorageName);
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

                            Toast.makeText(getActivity(), "Upload Successful Image", Toast.LENGTH_LONG).show();

                            //Upload upload2 = new Upload();
                             //String uploadId = mDatabaseRef.push().getKey();
                            //mDatabaseRef.child().setValue(upload2);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Failed Image", Toast.LENGTH_LONG).show();;
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });

            fileReference.getDownloadUrl().addOnSuccessListener( new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    //progressDialog.dismiss();
                    //Toast.makeText(getContext(), "Uploaded " + filePath, Toast.LENGTH_SHORT).show();
                    //Log.d(TAG, "onSuccess: uri= "+ uri.toString());
                    downloadUri = uri;

                }
            } );



            upload = new Upload( imageStorageUrl, advert_Title, advert_Short_Description, advert_Long_Description, currentUser.getPhoneNumber().toString() , advert_Location_Text);
            final String key =  mDatabaseRef.push().getKey();
            mDatabaseRef.child(key).setValue(upload);



            Toast.makeText(getActivity(), "Upload Successful", Toast.LENGTH_LONG).show();


        }
    }
}


