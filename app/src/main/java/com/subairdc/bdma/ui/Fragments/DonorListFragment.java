package com.subairdc.bdma.ui.Fragments;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.ims.ImsMmTelManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.subairdc.bdma.Activities.Donor;
import com.subairdc.bdma.Activities.Donors;
import com.subairdc.bdma.MyAdapter;
import com.subairdc.bdma.R;

import java.util.ArrayList;
import java.util.Locale;


public class DonorListFragment extends Fragment {

    DatabaseReference reference;
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    SearchView searchView;
    ImageButton imageButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donor_list, container, false);

        reference = FirebaseDatabase.getInstance().getReference("Donors");
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Donors> options =
                new FirebaseRecyclerOptions.Builder<Donors>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Donors"), Donors.class)
                        .build();

        myAdapter = new MyAdapter(options);
        recyclerView.setAdapter(myAdapter);

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

        imageButton = (ImageButton) view.findViewById(R.id.adddonorbtn);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AddDonorFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_main,fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }

    private void textSearch(String str){
        FirebaseRecyclerOptions<Donors> options =
                new FirebaseRecyclerOptions.Builder<Donors>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Donors").orderByChild("name").startAt(str).endAt(str+"~"), Donors.class)//Case sensitive
                        .build();
        myAdapter =new MyAdapter(options);
        myAdapter.startListening();
        recyclerView.setAdapter(myAdapter);
    }
}