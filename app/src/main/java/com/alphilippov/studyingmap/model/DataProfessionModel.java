package com.alphilippov.studyingmap.model;

import android.content.Context;
import android.util.Log;

import com.alphilippov.studyingmap.database.AppDataBase;
import com.alphilippov.studyingmap.database.dao.ProfessionDao;
import com.alphilippov.studyingmap.database.entity.ProfessionEntity;
import com.alphilippov.studyingmap.repository.Repo;
import com.alphilippov.studyingmap.utils.AppApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class DataProfessionModel implements Repo {

    @Override
    public void loadProfession() {

    }

    @Override
    public void updateProfession() {

    }

    @Override
    public void deleteProfession() {

    }

    @Override
    public void insertProfession() {

    }

    private void loadData() {
        AppDataBase db = AppApplication.getInstance().getDatabase();
        ProfessionDao professionDao = db.partOneProfessionDao();
        List<ProfessionEntity> professionEntities = professionDao.getAll();
    }
//TODO:Скопировать 3 файла в директорию room при первой инициализации
    private void copyAttachedDatabase(Context context, String databaseName) {
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


