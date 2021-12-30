package com.subairdc.bdma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.subairdc.bdma.Activities.Donor;
import com.subairdc.bdma.Activities.Donors;
import com.subairdc.bdma.databinding.DonorlistLayoutBinding;

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
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.donorlist_layout,parent,false);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, bloodGrp,city, phoneNo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            bloodGrp = (TextView)itemView.findViewById(R.id.bloodGrp);
            city = (TextView)itemView.findViewById(R.id.city);
            phoneNo = (TextView)itemView.findViewById(R.id.phoneNo);
        }
    }
}

