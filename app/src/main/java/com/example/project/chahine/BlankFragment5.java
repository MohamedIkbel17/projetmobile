package com.example.project.chahine;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project.R;

import java.util.List;

public class BlankFragment5 extends Fragment {

    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclevieww);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

        getroomdata();
    }

    private void getroomdata() {
        LocationDataBase locationDataBase = Room.databaseBuilder(getContext(),LocationDataBase.class,"Locations")
                .allowMainThreadQueries().build();
        LocationDao locationDao = locationDataBase.locationDao();
        List<LocationEntity> list = locationDao.getAll();

        LocationAdapterPatient locationAdapter = new LocationAdapterPatient(list);
        recyclerView.setAdapter(locationAdapter);

    }
}