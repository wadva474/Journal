package com.example.musa.journal.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.musa.journal.AppDataBase.AppDatabase;
import com.example.musa.journal.AppDataBase.MoodEntity;

import java.util.List;

public class MainViewModel extends AndroidViewModel {


    LiveData<List<MoodEntity>> moodentity;

    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase=AppDatabase.getsInstance(getApplication());
        moodentity=appDatabase.moodDao().loadMoods();
    }

    public LiveData<List<MoodEntity>> getMoodentity() {
        return moodentity;
    }
}