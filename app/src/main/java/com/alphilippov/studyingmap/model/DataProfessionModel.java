package com.alphilippov.studyingmap.model;

import com.alphilippov.studyingmap.database.AppDataBase;
import com.alphilippov.studyingmap.database.dao.ProfessionDao;
import com.alphilippov.studyingmap.database.entity.ProfessionEntity;
import com.alphilippov.studyingmap.repository.Repo;
import com.alphilippov.studyingmap.utils.AppApplication;

import java.util.List;

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
}
