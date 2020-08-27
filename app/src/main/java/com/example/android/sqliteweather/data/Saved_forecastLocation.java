package com.example.android.sqliteweather.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "locations")

public class Saved_forecastLocation {
    @NonNull
    @PrimaryKey
    public String name;
}
