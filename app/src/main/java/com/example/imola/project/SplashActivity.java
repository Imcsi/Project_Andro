package com.example.imola.project;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

    private Context context = SplashActivity.this;
    private ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);

        imageView1 = findViewById(R.id.imageStart);
       // loadImageByResourceId();




    }
    /*
    private void loadImageByResourceId() {
        int resourceId=R.mipmap.ic_launcher;

        Glide
                .with(context)
                .load(resourceId)
                .into(imageView1);
    }*/

}
