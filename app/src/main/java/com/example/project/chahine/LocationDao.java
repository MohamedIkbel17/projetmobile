package com.example.project.chahine;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {
    @Query("SELECT * FROM location")
    List<LocationEntity> getAll();

    @Query("SELECT * FROM location WHERE lid IN (:userIds)")
    List<LocationEntity> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM location WHERE Name LIKE :first AND " +
            "type LIKE :type LIMIT 1")
    LocationEntity findByName(String first, String type);

    @Insert
    void insert(LocationEntity location);

    @Insert
    void insertAll(LocationEntity... users);

    @Query("DELETE FROM location WHERE lid = :id")
    void delete(int id);
}
