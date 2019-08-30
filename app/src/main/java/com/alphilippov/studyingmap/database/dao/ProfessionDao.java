package com.alphilippov.studyingmap.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.alphilippov.studyingmap.database.entity.ProfessionEntity;

import java.util.List;

@Dao
public interface ProfessionDao {
    @Query("SELECT * FROM ProfessionEntity")
    List<ProfessionEntity> getAll();

    @Insert
    void insert(ProfessionEntity professionEntity);

    @Update
    void update(ProfessionEntity professionEntity);

    @Delete
    void delete(ProfessionEntity professionEntity);
}
