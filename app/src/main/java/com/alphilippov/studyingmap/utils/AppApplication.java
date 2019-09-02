package com.alphilippov.studyingmap.utils;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.alphilippov.studyingmap.database.AppDataBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.support.constraint.Constraints.TAG;

public class AppApplication extends Application {

    public static AppApplication instance;

    private AppDataBase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        database = Room.databaseBuilder(this, AppDataBase.class, "professiondata")
                .build();
    }

    public static AppApplication getInstance() {
        return instance;
    }

    public AppDataBase getDatabase() {
        return database;
    }

    public void copyAttachedDatabase(Context context, String databaseName) {

        final File dbPath = context.getDatabasePath(databaseName);


        if (dbPath.exists()) {
            return;
        }
        try {
            final InputStream inputStream = context.getAssets().open(databaseName);
            final OutputStream output = new FileOutputStream(dbPath);

            byte[] buffer = new byte[8192];
            int length;
            while ((length = inputStream.read(buffer, 0, 8192)) > 0) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            inputStream.close();
        } catch (IOException e) {
            Log.d(TAG, "Failed to open file", e);
            e.printStackTrace();
        }
    }
}
