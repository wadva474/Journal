package com.example.musa.journal.AppDataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
@Dao
public interface MoodDao {
    @Query("SELECT * FROM Diary ORDER BY mMood")
    LiveData<List<MoodEntity>> loadMoods();

    @Insert
    void insertmood(MoodEntity moodEntity);

    @Query("SELECT * FROM Diary WHERE id= :id")
    MoodEntity loadbyId(int id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(MoodEntity moodEntity);

    @Delete
    void deleteTask(MoodEntity moodEntity);
}
