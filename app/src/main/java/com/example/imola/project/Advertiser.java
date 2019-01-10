package com.example.imola.project;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Advertiser {
 String aTitle,details;
 Integer checkNumber;
 String aImage,profileImage;


    public String getaImage() {
        return aImage;
    }

    public void setaImage(String aImage) {
        this.aImage = aImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getProfileImage() {
        return profileImage;

    }

    public Advertiser(String aTitle, String details, Integer checkNumber, String aImage, String profileImage) {
        this.aTitle = aTitle;
        this.details = details;
        this.checkNumber = checkNumber;
        this.aImage = aImage;
        this.profileImage = profileImage;
    }

    public String getaTitle() {
        return aTitle;

    }

    public void setaTitle(String aTitle) {
        this.aTitle = aTitle;
    }



    public void setDetails(String details) {
        this.details = details;
    }


    public void setCheckNumber(Integer checkNumber) {
        this.checkNumber = checkNumber;
    }


    public String getDetails() {
        return details;
    }


    public Integer getCheckNumber() {
        return checkNumber;
    }


}
