package com.subairdc.bdma.ui.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.subairdc.bdma.Activities.Donors;
import com.subairdc.bdma.Activities.RegisterUser;
import com.subairdc.bdma.MainActivity;
import com.subairdc.bdma.R;
import com.subairdc.bdma.databinding.FragmentAddDonorBinding;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;


public class AddDonorFragment extends Fragment {

    FragmentAddDonorBinding binding;
    String gender;
    Dialog dialog;
    DatePickerDialog.OnDateSetListener dobsetListener;
    DatePickerDialog.OnDateSetListener datesetListener;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentAddDonorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAddDonorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        binding.dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), android.R.style.Theme_DeviceDefault_Dialog,dobsetListener,year,month,day);
                datePickerDialog.show();
            }
        });

        dobsetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                binding.dob.setText(date);
            }
        };


        binding.lastDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), android.R.style.Theme_DeviceDefault_Dialog,datesetListener,year,month,day);
                datePickerDialog.show();
            }
        });

        datesetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                binding.lastDonate.setText(date);
            }
        };

        String[] bloodgrplistitems =getResources().getStringArray(R.array.bloodgrp);
        ArrayAdapter bloodgrparrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line,bloodgrplistitems);
        bloodgrparrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.bloodgrpselect.setAdapter(bloodgrparrayAdapter);

        String[] districtlistitems = getResources().getStringArray(R.array.district);
        ArrayAdapter districtarrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line,districtlistitems);
        districtarrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.district.setAdapter(districtarrayAdapter);


        binding.addDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.name.getText().toString().trim();
                int id = binding.rgGender.getCheckedRadioButtonId();
                String dob = binding.dob.getText().toString().trim();
                String bloodGrp = binding.bloodgrpselect.getText().toString().trim();
                String phoneNo = binding.phoneNo.getText().toString().trim();
                String email = binding.email.getText().toString().trim();
                String city = binding.city.getText().toString().trim();
                String district = binding.district.getText().toString().trim();
                String pincode = binding.pincode.getText().toString().trim();
                String state = binding.state.getText().toString().trim();
                String noofdonate = binding.noofdonate.getText().toString().trim();
                String lastDonatedDate = binding.lastDonate.getText().toString().trim();

                if (name.isEmpty()) {
                    binding.name.setError("Full Name is required");
                    binding.name.requestFocus();
                    return;
                } else if (name.length() >= 20 || name.length()<=4) {
                    binding.name.setError("Name length is only 15 ");
                    binding.name.requestFocus();
                    return;
                }
                switch (id) {
                    case R.id.rbMale:
                        gender = "Male";
                        break;

                    case R.id.rbFemale:
                        gender = "Female";
                        break;

                    case R.id.rbOther:
                        gender = "Other";
                        break;
                }

                if (dob.isEmpty()) {
                    binding.dob.setError("Date of Birth is required");
                    binding.dob.requestFocus();
                    return;
                }
                if (bloodGrp.isEmpty()) {
                    binding.bloodgrpselect.setError("Date of Birth is required");
                    binding.bloodgrpselect.requestFocus();
                    return;
                }

                if (phoneNo.isEmpty()) {
                    binding.phoneNo.setError("Phone Number is required");
                    binding.phoneNo.requestFocus();
                    return;
                }else if(!Patterns.PHONE.matcher(phoneNo).matches() || phoneNo.length()<10){
                    binding.phoneNo.setError(" Valid Phone Number is required");
                    binding.phoneNo.requestFocus();
                    return;
                }

                if (email.isEmpty()) {
                    binding.email.setError("email is required");
                    binding.email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.email.setError("Please provide valid Email");
                    binding.email.requestFocus();
                    return;
                }

                if (city.isEmpty()){
                    binding.city.setError("city is required");
                    binding.city.requestFocus();
                    return;
                }else{
                    binding.district.setText("Tirunelveli");
                    binding.state.setText("Tamil Nadu");
                }
                binding.progressBar.setVisibility(View.VISIBLE);

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Donors");

                //get all the values already done

                Donors donors = new Donors(name, gender, dob, bloodGrp, phoneNo, email, city, district, pincode, state, noofdonate, lastDonatedDate);
                reference.setValue(donors);
            }

            });


    }
}