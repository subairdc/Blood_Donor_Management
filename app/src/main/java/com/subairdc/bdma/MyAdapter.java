package com.subairdc.bdma;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;



import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.subairdc.bdma.Activities.Donor;
import com.subairdc.bdma.Activities.Donors;
import com.subairdc.bdma.ui.Fragments.ViewDonorDetailsFragment;

import java.util.ArrayList;


public class MyAdapter extends FirebaseRecyclerAdapter<Donors,MyAdapter.MyViewHolder> {

    public MyAdapter(@NonNull FirebaseRecyclerOptions<Donors> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Donors model) {

        holder.name.setText(model.getName());
        holder.bloodGrp.setText(model.getBloodGrp());
        holder.city.setText(model.getCity());
        holder.phoneNo.setText(model.getPhoneNo());
        holder.status.setText(model.getStatus());

        //navigate to view details fragment
        holder.bloodGrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,new ViewDonorDetailsFragment(model.getName(),model.getGender(),model.getDob(),model.getAge(),model.getBloodGrp(),model.getStatus(),model.getPhoneNo(),model.getEmail(),model.getNoofdonate(),model.getLastDonoateDate(),model.getCity(),model.getDistrict(),model.getPincode(),model.getState())).addToBackStack(null).commit();
            }
        });
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donorlist_layout,parent,false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, bloodGrp,city, phoneNo,status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            bloodGrp = (TextView)itemView.findViewById(R.id.bloodGrp);
            city = (TextView)itemView.findViewById(R.id.city);
            phoneNo = (TextView)itemView.findViewById(R.id.phoneNo);
            status = (TextView) itemView.findViewById(R.id.status);
        }
    }

    public interface ItemClickListner{
        public void onItemCLick();
    }
}

