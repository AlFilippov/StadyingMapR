package com.alphilippov.studyingmap.view;

import com.alphilippov.studyingmap.network.dto.UserModelDto;
import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface ResultsAfterSearchKeyWordsView extends MvpView {
    void showLoadBar();
    void loadDataAdapter(List<UserModelDto> userModelDtoList);
    void checkConnectionInternet();

}
