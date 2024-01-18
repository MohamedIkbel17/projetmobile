package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentListPatient extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton addbutton;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.fragment_list_patient,container,false);

        recyclerView = view.findViewById(R.id.listpatient);
        addbutton = view.findViewById(R.id.floatingActionButton);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),Fichier.class));
            }
        });
        return view;
    }
}
