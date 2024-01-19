package com.example.project.chahine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.project.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainChahine extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chahine);
        bottomNavigationView = findViewById(R.id.bottom);
        frameLayout = findViewById(R.id.fragment);

        loadFrag(new BlankFragment2(), true);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.map) {
                loadFrag(new BlankFragment(), false);
            } else if (itemId == R.id.list) {
                loadFrag(new BlankFragment2(), false);
            } else if (itemId == R.id.add) {
                loadFrag(new BlankFragment3(), false);
            }

            return true;
        });
    }

    private void loadFrag(Fragment frag, boolean ins) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(ins)
            fragmentTransaction.add(R.id.fragment,frag);
        else
            fragmentTransaction.replace(R.id.fragment,frag);
        fragmentTransaction.commit();

    }
    public void openMapFragment(double latitude, double longitude) {
        BlankFragment mapFragment = new BlankFragment();

        Bundle args = new Bundle();
        args.putDouble("latitude", latitude);
        args.putDouble("longitude", longitude);
        mapFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, mapFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}