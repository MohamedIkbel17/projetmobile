package com.example.project.chahine;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

import java.util.List;

public class LocationAdapterPatient extends RecyclerView.Adapter<LocationAdapterPatient.ViewHolder>{

    List<LocationEntity> list;

    public LocationAdapterPatient(List<LocationEntity> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_location_patient,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        holder.tn.setText(String.valueOf(list.get(position).getName()));
        holder.tt.setText(String.valueOf(list.get(position).getType()));
        if (String.valueOf(list.get(position).getType()).compareTo("Cabinet")==0){
            holder.im.setImageResource(R.drawable.medical_team);
        }else {
            holder.im.setImageResource(R.drawable.hospital__3_);
        }
        holder.im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double latitude = list.get(position).getLatitude();
                double longitude = list.get(position).getLongitude();

                if (v.getContext() instanceof MainPatient) {
                    ((MainPatient) v.getContext()).openMapFrag(latitude, longitude);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tn,tt;
        ImageView im;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tn = itemView.findViewById(R.id.locc_name);
            tt = itemView.findViewById(R.id.locc_type);
            im = itemView.findViewById(R.id.imga);
        }
    }

}
