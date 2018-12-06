package com.example.imola.project;

public class Upload {
    private String mName;
    private String mImageUrl;

    public Upload(){

    }

    public Upload(String name, String imageUrl){
        if(name.trim().equals("")){
            name = "No name";
        }
        mName=name;
        mImageUrl=imageUrl;
    }

    public String getmName() {
        return mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
