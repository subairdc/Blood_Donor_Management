package com.subairdc.bdma.ui.Fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.subairdc.bdma.Activities.BloodBank;
import com.subairdc.bdma.Activities.Donors;
import com.subairdc.bdma.MainActivity;
import com.subairdc.bdma.R;
import com.subairdc.bdma.databinding.FragmentAddBloodBankBinding;
import com.subairdc.bdma.databinding.FragmentAddDonorBinding;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;


public class AddBloodBankFragment extends Fragment {

    FragmentAddBloodBankBinding binding;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentAddBloodBankBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddBloodBankBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Blood Banks");


        String[] districtlistitems = getResources().getStringArray(R.array.district);
        ArrayAdapter districtarrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line,districtlistitems);
        districtarrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.district.setAdapter(districtarrayAdapter);

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.name.getText().toString().trim();
                String phoneNo = binding.phoneNo.getText().toString().trim();
                String city = binding.city.getText().toString().trim();
                String district = binding.district.getText().toString().trim();

                if (name.isEmpty()) {
                    binding.name.setError("Please Enter Blood Bank Name");
                    binding.name.requestFocus();
                    return;
                }else if (name.length()<4){
                    binding.name.setError("Please Enter Blood Bank Full Name");
                    binding.name.requestFocus();
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

                if (city.isEmpty()){
                    binding.city.setError("city is required");
                    binding.city.requestFocus();
                    return;
                }else if (city.length()<4){
                    binding.city.setError("Enter full city name");
                    binding.city.requestFocus();
                    return;
                }else {
                    binding.district.setText("Tirunelveli");
                }
                binding.progressBar.setVisibility(View.VISIBLE);


                //get all the values already done

                BloodBank bloodbank = new BloodBank(name, phoneNo, city, district);
                reference.child(name).setValue(bloodbank).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(getContext(), "Add Blood Bank successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getContext(), MainActivity.class));
                            binding.progressBar.setVisibility(View.INVISIBLE);
                            //Redirect to login layout
                        }else {
                            Toast.makeText(getContext(), "Failed to Add Blood Bank! Pls Try Again", Toast.LENGTH_LONG).show();
                            binding.progressBar.setVisibility(View.INVISIBLE);
                        }

                    }
                });
            }

        });

    }


}