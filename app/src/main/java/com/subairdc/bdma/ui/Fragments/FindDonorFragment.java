package com.subairdc.bdma.ui.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
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


public class FindDonorFragment extends Fragment implements View.OnClickListener {

    DatabaseReference reference;
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    SearchView searchView;

    Button apbutton,anbutton,bpbutton,bnbutton,abpbutton,abnbutton,opbutton,onbutton,a1bpbutton,a1bnbutton,a2pbutton,a2nbutton;
    Button allbutton,bambaybutton;

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
        apbutton.setOnClickListener(this);
        anbutton = (Button)view.findViewById(R.id.anegative);
        anbutton.setOnClickListener(this);

        bpbutton = (Button)view.findViewById(R.id.bpositive);
        bpbutton.setOnClickListener(this);
        bnbutton = (Button)view.findViewById(R.id.bnegative);
        bnbutton.setOnClickListener(this);

        abpbutton = (Button)view.findViewById(R.id.abpositive);
        abpbutton.setOnClickListener(this);
        abnbutton = (Button)view.findViewById(R.id.abnegative);
        abnbutton.setOnClickListener(this);

        opbutton = (Button)view.findViewById(R.id.opositive);
        opbutton.setOnClickListener(this);
        onbutton = (Button)view.findViewById(R.id.onegative);
        onbutton.setOnClickListener(this);

        a1bpbutton = (Button)view.findViewById(R.id.a1bpositive);
        a1bpbutton.setOnClickListener(this);
        a1bnbutton = (Button)view.findViewById(R.id.a1bnegative);
        a1bnbutton.setOnClickListener(this);

        a2pbutton = (Button)view.findViewById(R.id.a2positive);
        a2pbutton.setOnClickListener(this);
        a2nbutton = (Button)view.findViewById(R.id.a2negative);
        a2nbutton.setOnClickListener(this);

        bambaybutton = (Button)view.findViewById(R.id.bambay);
        bambaybutton.setOnClickListener(this);
        allbutton = (Button)view.findViewById(R.id.all);
        allbutton.setOnClickListener(this);


        reference = FirebaseDatabase.getInstance().getReference("Donors");
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Donors> options =
                new FirebaseRecyclerOptions.Builder<Donors>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Donors"), Donors.class)
                        .build();

        myAdapter = new MyAdapter(options);
        recyclerView.setAdapter(myAdapter);

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
            case R.id.apositive:
                String str = "A+";
                bloodGrpSearch(str);
                break;
            case R.id.anegative:
                bloodGrpSearch("A-");
                break;

            case R.id.bpositive:
                bloodGrpSearch("B+");
                break;
            case R.id.bnegative:
                bloodGrpSearch("B-");
                break;

            case R.id.abpositive:
                bloodGrpSearch("AB+");
                break;
            case R.id.abnegative:
                bloodGrpSearch("AB-");
                break;

            case R.id.opositive:
                bloodGrpSearch("O+");
                break;
            case R.id.onegative:
                bloodGrpSearch("O-");
                break;

            case R.id.a1bpositive:
                bloodGrpSearch("A1B+");
                break;
            case R.id.a1bnegative:
                bloodGrpSearch("A1B-");
                break;

            case R.id.a2positive:
                bloodGrpSearch("A2+");
                break;
            case R.id.a2negative:
                bloodGrpSearch("A2-");
                break;

            case R.id.bambay:
                bloodGrpSearch("Bambay");
                break;
            case R.id.all:
                bloodGrpSearchAll();
                break;
        }
    }

    private void bloodGrpSearchAll() {
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
    }
}