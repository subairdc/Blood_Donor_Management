package com.subairdc.bdma.Activities;

public class BloodBank {
    String name,phoneNo,city,district;

    public BloodBank() {
    }

    public BloodBank(String name, String phoneNo, String city, String district) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.city = city;
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
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
}
