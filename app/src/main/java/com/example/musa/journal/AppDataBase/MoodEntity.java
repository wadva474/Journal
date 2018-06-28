package com.example.musa.journal.AppDataBase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
@Entity(tableName = "Diary")
public class MoodEntity {
    public String mMood;
    public String mDescription;
    public Date mDate;
    @PrimaryKey(autoGenerate = true)
    public int ID;

    @Ignore
    public MoodEntity(String mMood, String mDescription, Date mDate) {
        this.mMood = mMood;
        this.mDescription = mDescription;
        this.mDate = mDate;
    }

    public MoodEntity(String mMood, String mDescription, Date mDate, int ID) {
        this.mMood = mMood;
        this.mDescription = mDescription;
        this.mDate = mDate;
        this.ID = ID;
    }

    public String getmMood() {
        return mMood;
    }

    public void setmMood(String mMood) {
        this.mMood = mMood;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
