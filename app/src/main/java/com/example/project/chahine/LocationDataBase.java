package com.example.project.chahine;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LocationEntity.class}, version = 1)
public abstract class LocationDataBase extends RoomDatabase {
    private static final String dbName = "Locations";
    private static LocationDataBase locationDataBase;
    public static synchronized LocationDataBase getLocationDataBase(Context context){
        if (locationDataBase == null){
            locationDataBase = Room.databaseBuilder(context,LocationDataBase.class,dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return locationDataBase;
    }

    public abstract LocationDao locationDao();
}
