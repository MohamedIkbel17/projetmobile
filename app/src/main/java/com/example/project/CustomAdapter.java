package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList patient_id, patient_nom, patient_prenom, patient_maladie;

    CustomAdapter(Activity activity,Context context, ArrayList patient_id, ArrayList patient_nom, ArrayList patient_prenom, ArrayList patient_maladie) {
        this.activity = activity;
        this.context = context;
        this.patient_id = patient_id;
        this.patient_nom = patient_nom;
        this.patient_prenom = patient_prenom;
        this.patient_maladie = patient_maladie;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.patient_id_txt.setText(String.valueOf(patient_id.get(position)));
        holder.patient_nom_txt.setText(String.valueOf(patient_nom.get(position)));
        holder.patient_prenom_txt.setText(String.valueOf(patient_prenom.get(position)));
        holder.patient_maladie_txt.setText(String.valueOf(patient_maladie.get(position)));


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdatePatient.class);
                intent.putExtra("id", String.valueOf(patient_id.get(position)));
                intent.putExtra("nom", String.valueOf(patient_nom.get(position)));
                intent.putExtra("prenom", String.valueOf(patient_prenom.get(position)));
                intent.putExtra("maladie", String.valueOf(patient_maladie.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return patient_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView patient_id_txt,patient_nom_txt,patient_prenom_txt,patient_maladie_txt;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            patient_id_txt = itemView.findViewById(R.id.patient_id);
            patient_nom_txt = itemView.findViewById(R.id.patient_nom);
            patient_prenom_txt = itemView.findViewById(R.id.patient_prenom);
            patient_maladie_txt = itemView.findViewById(R.id.patient_maladie);
            linearLayout = itemView.findViewById(R.id.minl);
        }
    }
}