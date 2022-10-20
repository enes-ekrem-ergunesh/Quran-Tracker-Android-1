package com.example.quran_tracker_1.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity
public class Recitation {

    public Recitation(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.dateModified = Calendar.getInstance().getTimeInMillis();
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "isCompleted")
    public boolean isCompleted;

    @ColumnInfo(name = "dateModified")
    public long dateModified;
}