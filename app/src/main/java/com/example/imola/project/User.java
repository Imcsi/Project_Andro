package com.example.imola.project;

public class User {

    public String ufirstName;
    public String ulastName;
    public String uemail;
    public String uphoneNumber;
    public String uadress;
    public String uuserid;

    public User(String ufirstName, String ulastName, String uemail, String uphoneNumber, String uadress) {
        this.ufirstName = ufirstName;
        this.ulastName = ulastName;
        this.uemail = uemail;
        this.uphoneNumber = uphoneNumber;
        this.uadress = uadress;
    }

    public User() {
    }

    public User(String ufirstName, String ulastName, String uphoneNumber) {
        this.ufirstName = ufirstName;
        this.ulastName = ulastName;
        this.uphoneNumber = uphoneNumber;
    }

    public User(String ufirstName, String ulastName, String uemail, String uphoneNumber, String uadress, String uuserid) {
        this.ufirstName = ufirstName;
        this.ulastName = ulastName;
        this.uemail = uemail;
        this.uphoneNumber = uphoneNumber;
        this.uadress = uadress;
        this.uuserid = uuserid;
    }

    public String getUfirstName() {
        return ufirstName;
    }

    public String getUlastName() {
        return ulastName;
    }

    public String getUemail() {
        return uemail;
    }

    public String getUphoneNumber() {
        return uphoneNumber;
    }

    public String getUadress() {
        return uadress;
    }

    public String getUuserid() {
        return uuserid;
    }

    public void setUfirstName(String ufirstName) {
        this.ufirstName = ufirstName;
    }

    public void setUlastName(String ulastName) {
        this.ulastName = ulastName;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public void setUphoneNumber(String uphoneNumber) {
        this.uphoneNumber = uphoneNumber;
    }

    public void setUadress(String uadress) {
        this.uadress = uadress;
    }

    public void setUuserid(String uuserid) {
        this.uuserid = uuserid;
    }
}
