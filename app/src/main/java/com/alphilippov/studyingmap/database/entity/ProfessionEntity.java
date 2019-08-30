package com.alphilippov.studyingmap.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ProfessionEntity {
    @ColumnInfo(name = "idDefinition")
    private int mIdDefinition;
    @PrimaryKey
    @ColumnInfo(name = "indexDefinition")
    private int mIndexDifenition;
    @ColumnInfo(name = "profession")
    private String mProfession ;
}
