package com.alphilippov.studyingmap.view;

import com.alphilippov.studyingmap.network.dto.UserModelDto;


import java.util.List;

import moxy.MvpView;

public interface ResultsAfterSearchKeyWordsView extends MvpView {
    void showLoadBar();
    void loadDataAdapter(List<UserModelDto> userModelDtoList);
    void checkConnectionInternet();

}
