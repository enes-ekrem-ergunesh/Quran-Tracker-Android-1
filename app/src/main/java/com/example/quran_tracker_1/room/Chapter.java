package com.example.quran_tracker_1.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity
public class Chapter {

    public Chapter(String name, int number, boolean isCompleted) {
        this.name = name;
        this.number = number;
        this.isCompleted = isCompleted;
        this.recitationID = -1;
        this.dateModified = Calendar.getInstance().getTimeInMillis();
    }

    @Ignore
    public Chapter(int number, boolean isCompleted, int recitationID) {
        this.name = null;
        this.number = number;
        this.isCompleted = isCompleted;
        this.recitationID = recitationID;
        this.dateModified = -1;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "number")
    public int number;

    @ColumnInfo(name = "isCompleted")
    public boolean isCompleted;

    @ColumnInfo(name = "recitationID")
    public int recitationID;

    @ColumnInfo(name = "dateModified")
    public long dateModified;
}
