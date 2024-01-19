package com.example.project.chahine;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class BlankFragment3 extends Fragment {

    private SwitchMaterial getLocation;
    private LocationManager locationManager;
    private double latitude=95,longitude=185;
    TextView textView ;
    EditText editText;
    RadioGroup Rg;
    Button button;
    Location GpsLocation;

    ActivityResultLauncher<String[]> locationPermissionRequest =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                        Boolean fineLocationGranted = result.getOrDefault(
                                Manifest.permission.ACCESS_FINE_LOCATION, false);
                        Boolean coarseLocationGranted = result.getOrDefault(
                                Manifest.permission.ACCESS_COARSE_LOCATION,false);
                        if (fineLocationGranted != null && fineLocationGranted) {
                            // Precise location access granted.
                            Toast.makeText(getActivity(), "Precise location access granted", Toast.LENGTH_SHORT).show();
                        } else if (coarseLocationGranted != null && coarseLocationGranted) {
                            // Only approximate location access granted.
                            Toast.makeText(getActivity(), "Approximate location access granted", Toast.LENGTH_SHORT).show();
                        } else {
                            // No location access granted.
                            Toast.makeText(getActivity(), "No location access granted", Toast.LENGTH_SHORT).show();
                        }
                    }
            );

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.switch_loc);
        getLocation = view.findViewById(R.id.Getlocation);
        editText = view.findViewById(R.id.LocName);
        Rg = view.findViewById(R.id.TypeRg);
        button = view.findViewById(R.id.savebtn);

        getLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    CheckedLocationPermission();
                }
                else {
                    textView.setText("");
                    latitude=95;
                    longitude=185;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = Rg.getCheckedRadioButtonId();
                if (selectedId != -1) { // Check if a radio button is selected
                    RadioButton Rb = view.findViewById(selectedId); // Retrieve the selected radio button

                    LocationEntity locationEntity = new LocationEntity();
                    locationEntity.setName(editText.getText().toString());
                    String type = Rb.getTag().toString(); // Retrieve the tag of the selected radio button
                    if (type != null) { // Check if the type is not null
                        locationEntity.setType(type); // Set the location type in the entity
                    } else { // If type is null, display a toast message
                        Toast.makeText(getActivity(), "Please select a location type", Toast.LENGTH_SHORT).show();
                    }

                    // Additional input validation
                    if (!validInput(locationEntity)) {
                        Toast.makeText(getActivity(), "Fill all fields", Toast.LENGTH_SHORT).show();
                    } else if (!validLocation()) {
                        Toast.makeText(getActivity(), "Verify Location", Toast.LENGTH_SHORT).show();
                    } else {
                        // Additional processing and storage of location entity
                        locationEntity.setLatitude(latitude);
                        locationEntity.setLongitude(longitude);
                        LocationDataBase locationDataBase = LocationDataBase.getLocationDataBase(getContext());
                        LocationDao locationDao = locationDataBase.locationDao();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                locationDao.insertAll(locationEntity);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "Location Added", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).start();

                        // Fragment transaction to navigate to the new fragment
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment, new BlankFragment2());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                } else { // If no radio button is selected, display a toast message
                    Toast.makeText(getActivity(), "Please select a location type", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank3, container, false);
    }

    private void CheckedLocationPermission() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            locationManager= (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        }
        if (!locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)){
            OnGPS();
        }
        else {
            getcurrentLocation();
        }
    }
    private void getcurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            locationPermissionRequest.launch(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION});

        }
        else {
            GpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            /*locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    GpsLocation = location;
                }
            });*/
        }
        if (GpsLocation!=null){
            latitude = GpsLocation.getLatitude();
            longitude = GpsLocation.getLongitude();

            String lat = String.valueOf(latitude);
            String log = String.valueOf(longitude);

            String locaat = lat+" , "+log;
            textView.setText(locaat);

        }
    }
    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Enable GPS!").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent((Settings.ACTION_LOCATION_SOURCE_SETTINGS)));
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        final AlertDialog dialog = builder.create();
        dialog.show();
    }
    private boolean validInput(LocationEntity locationEntity){
        return !locationEntity.getName().isEmpty() && !locationEntity.getType().isEmpty();
    }
    private boolean validLocation(){
        return !(latitude < -90 || latitude > 90 || longitude < -180 || longitude > 180 || latitude == 0 || longitude == 0);
    }
}