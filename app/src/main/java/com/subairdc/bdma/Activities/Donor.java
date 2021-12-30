package com.subairdc.bdma.Activities;

public class Donor {
    public String email,dob,gender,name,password,phoneNo;

    public Donor(){

    }

    public Donor(String dob, String email, String gender, String name, String password, String phoneNo) {
        this.dob = dob;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
