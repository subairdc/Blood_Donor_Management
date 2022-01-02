package com.subairdc.bdma;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.subairdc.bdma.Activities.BloodBank;
import com.subairdc.bdma.ui.Fragments.ViewDonorDetailsFragment;


public class BloodBankAdapter extends FirebaseRecyclerAdapter<BloodBank,BloodBankAdapter.MyViewHolder> {

    public BloodBankAdapter(@NonNull FirebaseRecyclerOptions<BloodBank> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull BloodBankAdapter.MyViewHolder holder, int position, @NonNull BloodBank model) {

        holder.city.setText(model.getCity());
        holder.name.setText(model.getName());
        holder.phoneNo.setText(model.getPhoneNo());


        //navigate to view details fragment
       /* holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_content_main,new ViewDonorDetailsFragment(model.getName(),model.getGender(),model.getDob(),model.getAge(),model.getBloodGrp(),model.getStatus(),model.getPhoneNo(),model.getEmail(),model.getNoofdonate(),model.getLastDonoateDate(),model.getCity(),model.getDistrict(),model.getPincode(),model.getState())).addToBackStack(null).commit();
            }
        });*/
    }


    @NonNull
    @Override
    public BloodBankAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bloodbanklist_layout,parent,false);
        return new BloodBankAdapter.MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,city, phoneNo,district;
        ImageButton call;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            city = (TextView)itemView.findViewById(R.id.city);
            phoneNo = (TextView)itemView.findViewById(R.id.phoneNo);
            district = (TextView) itemView.findViewById(R.id.district);
            call = (ImageButton) itemView.findViewById(R.id.call);
        }
    }

    public interface ItemClickListner{
        public void onItemCLick();
    }

}
