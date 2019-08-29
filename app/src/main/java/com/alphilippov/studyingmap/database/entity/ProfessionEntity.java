package com.alphilippov.studyingmap.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ProfessionEntity {
    private int mIdDefenition;
    @PrimaryKey
    private int mIndexDefenition;
    private String mProfession ;

    public int getmIdDefenition() {
        return mIdDefenition;
    }

    public void setmIdDefenition(int mIdDefenition) {
        this.mIdDefenition = mIdDefenition;
    }

    public int getmIndexDefenition() {
        return mIndexDefenition;
    }

    public void setmIndexDefenition(int mIndexDefenition) {
        this.mIndexDefenition = mIndexDefenition;
    }

    public String getmProfession() {
        return mProfession;
    }

    public void setmProfession(String mProfession) {
        this.mProfession = mProfession;
    }
}
