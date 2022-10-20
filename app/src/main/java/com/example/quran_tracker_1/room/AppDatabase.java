package com.example.quran_tracker_1.room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
        entities = {Recitation.class, Chapter.class, Page.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RecitationDao recitationDao();
    public abstract ChapterDao chapterDao();
    public abstract PageDao pageDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context){
        if( INSTANCE==null ){
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "app_database").build(); //.allowMainThreadQueries()
        }
        return INSTANCE;
    }
}