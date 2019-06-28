package com.alphilippov.studyingmap.helperclasses;

import com.alphilippov.studyingmap.fragments.ProfessionDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DistributionInterests {
public String[] mArrInteres ;
    ProfessionDefinition mProfessionDefinition = new ProfessionDefinition();
//TODO:Доделать алгоритм выдачи данных на запрос
    private String[] distributionInt(List<String> listHigh, List<String> listMiddle, List<String> listLow) {
        if (listHigh.size() != 0) {
mArrInteres=listHigh.stream().toArray(String[]::new);
        } else if (listMiddle.size()!=0) {
            mArrInteres=listHigh.stream().toArray(String[]::new);
        } else if(listLow.size()!=0){
            mArrInteres=listHigh.stream().toArray(String[]::new);
        }


        return mArrInteres;
    }

    private void sendInteresInNetwork() {

    }
}
