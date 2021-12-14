package com.subairdc.bdma.ui.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import com.subairdc.bdma.MainActivity;
import com.subairdc.bdma.R;
import com.subairdc.bdma.databinding.FragmentAddDonorBinding;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;


public class AddDonorFragment extends Fragment {

    FragmentAddDonorBinding binding;

    String gender;
    DatePickerDialog.OnDateSetListener setListener;

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

        binding.ivdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(), android.R.style.Theme_DeviceDefault_Dialog,setListener,year,month,day);
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                binding.dob.setText(date);
            }
        };

        String[] listitems =getResources().getStringArray(R.array.bloodgrp);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line,listitems);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.bloodgrpselect.setAdapter(arrayAdapter);


        binding.addDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.name.getText().toString().trim();
                int id = binding.rgGender.getCheckedRadioButtonId();
                String dob = binding.dob.getText().toString().trim();
                String phoneNo = binding.phoneNo.getText().toString().trim();
                String email = binding.email.getText().toString().trim();

                if (name.isEmpty()) {
                    binding.name.setError("Full Name is required");
                    binding.name.requestFocus();
                    return;
                } else if (name.length() >= 20) {
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

                if (phoneNo.isEmpty()) {
                    binding.phoneNo.setError("Phone Number is required");
                    binding.phoneNo.requestFocus();
                    return;
                }

                if (phoneNo.length() < 10) {
                    binding.phoneNo.setError("Please provide valid Phone Number");
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

                binding.progressBar.setVisibility(View.VISIBLE);
            }

            });
    }
}