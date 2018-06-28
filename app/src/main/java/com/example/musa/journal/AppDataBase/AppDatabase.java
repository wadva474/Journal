package com.example.musa.journal.AppDataBase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
@Database(entities = {MoodEntity.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;
    private Object LOCK =new Object();

    public static AppDatabase getsInstance(Context context) {
        if (sInstance==null){
            sInstance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"MOOD").build();
        }
        return sInstance;
    }
    public abstract MoodDao moodDao();
}
