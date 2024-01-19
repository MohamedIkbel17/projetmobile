package com.example.project.chahine;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.project.R;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder>{

    List<LocationEntity> list;

    public LocationAdapter(List<LocationEntity> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_location,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tn.setText(String.valueOf(list.get(position).getName()));
        holder.tt.setText(String.valueOf(list.get(position).getType()));
        if (String.valueOf(list.get(position).getType()).compareTo("Cabinet")==0){
            holder.im.setImageResource(R.drawable.medical_team);
        }else {
            holder.im.setImageResource(R.drawable.hospital__3_);
        }
        holder.dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationDataBase locationDataBase = Room.databaseBuilder(holder.tn.getContext(), LocationDataBase.class, "Locations")
                        .allowMainThreadQueries().build();
                LocationDao locationDao = locationDataBase.locationDao();
                locationDao.delete(list.get(position).getLid());
                list.remove(position);

                notifyDataSetChanged();
            }
        });

        holder.im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double latitude = list.get(position).getLatitude();
                double longitude = list.get(position).getLongitude();

                if (v.getContext() instanceof MainChahine) {
                    ((MainChahine) v.getContext()).openMapFragment(latitude, longitude);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tn,tt;
        ImageView im;
        ImageButton dl;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tn = itemView.findViewById(R.id.loc_name);
            tt = itemView.findViewById(R.id.loc_type);
            im = itemView.findViewById(R.id.img);
            dl = itemView.findViewById(R.id.del);

        }
    }


}
