package com.example.quran_tracker_1.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Page {

    public Page(int number, boolean isCompleted, int chapterID) {
        this.number = number;
        this.isCompleted = isCompleted;
        this.chapterID = chapterID;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "number")
    public int number;

    @ColumnInfo(name = "isCompleted")
    public boolean isCompleted;

    @ColumnInfo(name = "chapterID")
    public int chapterID;
}