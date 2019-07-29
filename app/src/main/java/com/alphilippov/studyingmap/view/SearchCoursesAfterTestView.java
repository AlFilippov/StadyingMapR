package com.alphilippov.studyingmap.view;

import com.alphilippov.studyingmap.network.dto.UserModelDto;


import java.util.List;

import moxy.MvpView;

public interface SearchCoursesAfterTestView extends MvpView {
    void loadProgressDownloading();
    void loadDataAdapter(List<UserModelDto>userModelDtoList);
    void checkConnectionInternet();

}
