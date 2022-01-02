package com.subairdc.bdma.ui.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.subairdc.bdma.Activities.BloodBank;
import com.subairdc.bdma.Activities.Donors;
import com.subairdc.bdma.BloodBankAdapter;
import com.subairdc.bdma.MyAdapter;
import com.subairdc.bdma.R;

public class BloodBankListFragment extends Fragment {

    DatabaseReference reference;
    BloodBankAdapter bloodbankAdapter;
    RecyclerView recyclerView;
    SearchView searchView;
    ImageButton button;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blood_bank_list, container, false);

        reference = FirebaseDatabase.getInstance().getReference("Blood Banks");
        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<BloodBank> options =
                new FirebaseRecyclerOptions.Builder<BloodBank>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Blood Banks"), BloodBank.class)
                        .build();

        bloodbankAdapter = new BloodBankAdapter(options);
        recyclerView.setAdapter(bloodbankAdapter);

        button = (ImageButton)view.findViewById(R.id.addbloodbankbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AddBloodBankFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



        //Search View

        searchView = (SearchView)view.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                textSearch(query);
                return false;
            }
        });


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        bloodbankAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        bloodbankAdapter.stopListening();
    }

    private void textSearch(String str){
        FirebaseRecyclerOptions<BloodBank> options =
                new FirebaseRecyclerOptions.Builder<BloodBank>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Blood Banks").orderByChild("name").startAt(str).endAt(str+"~"), BloodBank.class)//Case sensitive
                        .build();
        bloodbankAdapter =new BloodBankAdapter(options);
        bloodbankAdapter.startListening();
        recyclerView.setAdapter(bloodbankAdapter);
    }

}