package com.example.musa.journal.AppExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutor {
    private static AppExecutor sInstance;
    private static final Object LOCK =new Object();
    private Executor DataIO;

    public AppExecutor(Executor dataIO) {
        DataIO = dataIO;
    }

    public static AppExecutor getsInstance() {
        if (sInstance==null){
            synchronized (LOCK){
                sInstance=new AppExecutor(Executors.newSingleThreadExecutor());
            }
        }
        return sInstance;
    }

    public Executor getDataIO() {
        return DataIO;
    }
}
