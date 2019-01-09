package com.example.imola.project;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import static android.content.Context.CONTEXT_INCLUDE_CODE;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;
    String  imageStorageUrl;
    StorageReference mStorageRef;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView=itemView;
    }
    public void setDetails(Context Name, String ImageUrl, String Title, String Short_Description, String Long_Description, String Phone_Number, String Location_Text) {
        TextView m_aTitle = itemView.findViewById( R.id.AdvertTitle );
        TextView m_details = itemView.findViewById( R.id.SomeDetail );
        TextView m_checkNumber = itemView.findViewById( R.id.checkNumber );
        ImageView m_aImage = itemView.findViewById( R.id.imageAdver );

        ImageView m_profileImage = itemView.findViewById( R.id.imageUser );
        m_aTitle.setText( Title );
        m_details.setText( Short_Description );
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        m_checkNumber.setText( Phone_Number );
        /*mStorageRef.child( ImageUrl).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                  Picasso.with( itemView.getContext() ).load( uri ).into( (Target) m_aImage );

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });*/
//        Picasso.with( itemView.getContext() ).load( ImageUrl ).into( (Target) m_aImage );




    }
}
