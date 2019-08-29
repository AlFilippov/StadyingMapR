package com.alphilippov.studyingmap.utils;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.alphilippov.studyingmap.database.AppDataBase;

public class AppApplication extends Application {

    public static AppApplication instance;

    private AppDataBase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDataBase.class, "database")
                .build();
    }

    public static AppApplication getInstance() {
        return instance;
    }

    public AppDataBase getDatabase() {
        return database;
    }
}
