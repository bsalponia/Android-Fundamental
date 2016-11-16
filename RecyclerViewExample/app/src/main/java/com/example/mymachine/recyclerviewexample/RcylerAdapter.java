package com.example.mymachine.recyclerviewexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by myMachine on 11/16/2016.
 */

public class RcylerAdapter extends RecyclerView.Adapter<RcylerAdapter.RcylerViewHolder> {

    List<ApptDetails> detailsList;

    public RcylerAdapter(List<ApptDetails> detailsList){
        this.detailsList= detailsList;
    }


    public static class RcylerViewHolder extends RecyclerView.ViewHolder{

        public TextView apptSalonName, getApptSalonAddress, apptDate, apptTime;

        public RcylerViewHolder(View itemView) {
            super(itemView);
            apptSalonName= (TextView)itemView.findViewById(R.id.apptSalonName);
            getApptSalonAddress= (TextView)itemView.findViewById(R.id.apptSalonAddress);
            apptDate= (TextView)itemView.findViewById(R.id.apptDate);
            apptTime= (TextView)itemView.findViewById(R.id.apptTime);
        }
    }



    @Override
    public RcylerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.appointments_cardview, parent, false);

        return new RcylerViewHolder(view);


    }

    @Override
    public void onBindViewHolder(RcylerViewHolder holder, int position) {

        ApptDetails apptDetails= detailsList.get(position);
        holder.apptSalonName.setText(apptDetails.getApptSalonName());
        holder.getApptSalonAddress.setText(apptDetails.getApptSalonAddress());
        holder.apptDate.setText(apptDetails.getApptDate());
        holder.apptTime.setText(apptDetails.getApptTime());

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return detailsList.size();
    }
}
