package com.alphilippov.studyingmap.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.alphilippov.studyingmap.database.dao.ProfessionDao;
import com.alphilippov.studyingmap.database.entity.ProfessionEntity;

@Database(entities = {ProfessionEntity.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract ProfessionDao partOneProfessionDao();
}
