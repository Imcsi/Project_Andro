package com.example.imola.project;

public class Upload {
    private String Name;
    private String ImageUrl;

    private String Title;
    private String Short_Description;
    private String Long_Description;
    private String Phone_Number;
    private String Location_Text;
    private String Profile_Picture;



    public Upload(){

    }

    public Upload(String imageUrl, String title, String short_Description) {
        ImageUrl = imageUrl;
        Title = title;
        Short_Description = short_Description;
    }

    public Upload(String name, String imageUrl, String title, String short_Description, String long_Description, String phone_Number, String location_Text, String profile_Picture) {
        Name = name;
        ImageUrl = imageUrl;
        Title = title;
        Short_Description = short_Description;
        Long_Description = long_Description;
        Phone_Number = phone_Number;
        Location_Text = location_Text;
        Profile_Picture = profile_Picture;
    }

    public Upload(String imageUrl, String mTitle, String mShort_Description, String mLong_Description, String mPhone_Number, String mLocation_Text) {
        this.ImageUrl=imageUrl;
        this.Title = mTitle;
        this.Short_Description = mShort_Description;
        this.Long_Description = mLong_Description;
        this.Phone_Number = mPhone_Number;
        this.Location_Text = mLocation_Text;
    }
    public String getProfile_Picture() {
        return Profile_Picture;
    }

    public void setProfile_Picture(String profile_Picture) {
        Profile_Picture = profile_Picture;
    }

    public String getmName() {
        return Name;
    }

    public String getmImageUrl() {
        return ImageUrl;
    }

    public void setmName(String mName) {
        this.Name = mName;
    }

    public void setmImageUrl(String mImageUrl) {
        this.ImageUrl = mImageUrl;
    }

    public String getmTitle() {
        return Title;
    }

    public String getmShort_Description() {
        return Short_Description;
    }

    public String getmLong_Description() {
        return Long_Description;
    }

    public String getmPhone_Number() {
        return Phone_Number;
    }

    public String getmLocation_Text() {
        return Location_Text;
    }

    public void setmTitle(String mTitle) {
        this.Title = mTitle;
    }

    public void setmShort_Description(String mShort_Description) {
        this.Short_Description = mShort_Description;
    }

    public void setmLong_Description(String mLong_Description) {
        this.Long_Description = mLong_Description;
    }

    public void setmPhone_Number(String mPhone_Number) {
        this.Phone_Number = mPhone_Number;
    }

    public void setmLocation_Text(String mLocation_Text) {
        this.Location_Text = mLocation_Text;
    }



}
