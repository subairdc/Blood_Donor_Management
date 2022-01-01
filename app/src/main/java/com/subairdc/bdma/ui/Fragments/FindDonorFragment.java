package com.subairdc.bdma.ui.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.subairdc.bdma.Activities.Donors;
import com.subairdc.bdma.MyAdapter;
import com.subairdc.bdma.R;
import com.subairdc.bdma.databinding.FragmentFindDonorBinding;


public class FindDonorFragment extends Fragment {

    DatabaseReference reference;
    MyAdapter myAdapter;
    RecyclerView recyclerView;

    Button apbutton;
    Button anbutton;
    Button bpbutton;
    Button bnbutton;
    Button abpbutton;
    Button abnbutton;
    Button opbutton;
    Button onbutton;
    Button othersbutton;
    Button allbutton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find_donor, container, false);

        apbutton = (Button)view.findViewById(R.id.apositive);
       /* button.setOnClickListener(this);*/
        anbutton = (Button)view.findViewById(R.id.anegative);

        bpbutton = (Button)view.findViewById(R.id.bpositive);
        bnbutton = (Button)view.findViewById(R.id.bnegative);

        abpbutton = (Button)view.findViewById(R.id.abpositive);
        abnbutton = (Button)view.findViewById(R.id.abnegative);

        opbutton = (Button)view.findViewById(R.id.opositive);
        onbutton = (Button)view.findViewById(R.id.onegative);

        othersbutton = (Button)view.findViewById(R.id.others);
        allbutton = (Button)view.findViewById(R.id.all);


        reference = FirebaseDatabase.getInstance().getReference("Donors");
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Donors> options =
                new FirebaseRecyclerOptions.Builder<Donors>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Donors"), Donors.class)
                        .build();

        myAdapter = new MyAdapter(options);
        recyclerView.setAdapter(myAdapter);
        apbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseRecyclerOptions<Donors> options =
                        new FirebaseRecyclerOptions.Builder<Donors>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Donors").orderByChild("bloodGrp").equalTo("A+"), Donors.class)//Case sensitive
                                .build();
                myAdapter =new MyAdapter(options);
                myAdapter.startListening();
                recyclerView.setAdapter(myAdapter);
            }
        });

        anbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseRecyclerOptions<Donors> options =
                        new FirebaseRecyclerOptions.Builder<Donors>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Donors").orderByChild("bloodGrp").equalTo("A-"), Donors.class)//Case sensitive
                                .build();
                myAdapter =new MyAdapter(options);
                myAdapter.startListening();
                recyclerView.setAdapter(myAdapter);
            }
        });

        bpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseRecyclerOptions<Donors> options =
                        new FirebaseRecyclerOptions.Builder<Donors>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Donors").orderByChild("bloodGrp").equalTo("B+"), Donors.class)//Case sensitive
                                .build();
                myAdapter =new MyAdapter(options);
                myAdapter.startListening();
                recyclerView.setAdapter(myAdapter);
            }
        });

        bnbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseRecyclerOptions<Donors> options =
                        new FirebaseRecyclerOptions.Builder<Donors>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Donors").orderByChild("bloodGrp").equalTo("B-"), Donors.class)//Case sensitive
                                .build();
                myAdapter =new MyAdapter(options);
                myAdapter.startListening();
                recyclerView.setAdapter(myAdapter);
            }
        });

        opbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseRecyclerOptions<Donors> options =
                        new FirebaseRecyclerOptions.Builder<Donors>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Donors").orderByChild("bloodGrp").equalTo("O+"), Donors.class)//Case sensitive
                                .build();
                myAdapter =new MyAdapter(options);
                myAdapter.startListening();
                recyclerView.setAdapter(myAdapter);
            }
        });

        onbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseRecyclerOptions<Donors> options =
                        new FirebaseRecyclerOptions.Builder<Donors>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Donors").orderByChild("bloodGrp").equalTo("O-"), Donors.class)//Case sensitive
                                .build();
                myAdapter =new MyAdapter(options);
                myAdapter.startListening();
                recyclerView.setAdapter(myAdapter);
            }
        });

        allbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseRecyclerOptions<Donors> options =
                        new FirebaseRecyclerOptions.Builder<Donors>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Donors").orderByChild("bloodGrp"), Donors.class)//Case sensitive
                                .build();
                myAdapter =new MyAdapter(options);
                myAdapter.startListening();
                recyclerView.setAdapter(myAdapter);
            }
        });

        othersbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseRecyclerOptions<Donors> options =
                        new FirebaseRecyclerOptions.Builder<Donors>()
                                .setQuery(FirebaseDatabase.getInstance().getReference().child("Donors").orderByChild("bloodGrp"), Donors.class)//Case sensitive
                                .build();
                myAdapter =new MyAdapter(options);
                myAdapter.startListening();
                recyclerView.setAdapter(myAdapter);
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
/*
    private void bloodGrpSearch(String str){
        FirebaseRecyclerOptions<Donors> options =
                new FirebaseRecyclerOptions.Builder<Donors>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Donors").orderByChild("bloodGrp").equalTo(str), Donors.class)//Case sensitive
                        .build();
        myAdapter =new MyAdapter(options);
        myAdapter.startListening();
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();

        switch (id){
            case R.id.opositive:
                String str = "O+";
                bloodGrpSearch(str);
                break;
            case R.id.bpositive:
                bloodGrpSearch("B+");
                break;
        }
    }*/
}