package com.subairdc.bdma.ui.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.subairdc.bdma.R;
import com.subairdc.bdma.databinding.FragmentProfileBinding;
import com.subairdc.bdma.databinding.FragmentViewDonorDetailsBinding;


public class ViewDonorDetailsFragment extends Fragment {

    private FragmentViewDonorDetailsBinding binding;

    String name,gender,dob,age,bloodGrp,status,phoneNo,email,noofdonate,lastDonoateDate,city,district,pincode,state;

    public ViewDonorDetailsFragment() {

    }

    public ViewDonorDetailsFragment(String name, String gender, String dob, String age, String bloodGrp, String status, String phoneNo, String email, String noofdonate, String lastDonoateDate, String city, String district, String pincode, String state) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.age = age;
        this.bloodGrp = bloodGrp;
        this.status = status;
        this.phoneNo = phoneNo;
        this.email = email;
        this.noofdonate = noofdonate;
        this.lastDonoateDate = lastDonoateDate;
        this.city = city;
        this.district = district;
        this.pincode = pincode;
        this.state = state;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentViewDonorDetailsBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        binding.name.setText(name);
        binding.tvGender.setText(gender);
        binding.dob.setText(dob);
        binding.bloodGrp.setText(bloodGrp);
        binding.status.setText(status);
        binding.phoneNo.setText(phoneNo);
        binding.email.setText(email);
        binding.noofdonate.setText(noofdonate);
        binding.lastDonate.setText(lastDonoateDate);
        binding.city.setText(city);
        binding.district.setText(district);
        binding.pincode.setText(pincode);
        binding.state.setText(state);
        return root;
    }

   /* public void onBackPressed(){

        AppCompatActivity activity = (AppCompatActivity).getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,new UpdateDonorFragment()).addToBackStack(null).commit();
    }*/

}