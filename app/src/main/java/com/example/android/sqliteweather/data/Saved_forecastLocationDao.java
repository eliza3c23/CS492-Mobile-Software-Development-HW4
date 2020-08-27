package com.example.android.sqliteweather.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Saved_forecastLocationDao {
    @Insert
    void insert(Saved_forecastLocation location);
    @Delete
    void delete(Saved_forecastLocation location);
    @Query("SELECT * FROM locations")
    LiveData<List<Saved_forecastLocation>> getAllLocations();
    @Query("SELECT * FROM locations WHERE name = :location LIMIT 1")
    Saved_forecastLocation getSavedLocationsByName(String location);

}
