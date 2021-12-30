package com.subairdc.bdma.Activities;

public class Donors {
    public Donors() {
    }

    String name,gender,dob,bloodGrp,phoneNo,email,city,district,pincode,state,noofdonate,lastDonoateDate;

    public Donors(String name, String gender, String dob, String bloodGrp, String phoneNo, String email, String city, String district, String pincode, String state, String noofdonate, String lastDonoateDate) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.bloodGrp = bloodGrp;
        this.phoneNo = phoneNo;
        this.email = email;
        this.city = city;
        this.district = district;
        this.pincode = pincode;
        this.state = state;
        this.noofdonate = noofdonate;
        this.lastDonoateDate = lastDonoateDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }



    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNoofdonate() {
        return noofdonate;
    }

    public void setNoofdonate(String noofdonate) {
        this.noofdonate = noofdonate;
    }

    public String getLastDonoateDate() {
        return lastDonoateDate;
    }

    public void setLastDonoateDate(String lastDonoateDate) {
        this.lastDonoateDate = lastDonoateDate;
    }
}
