package com.alphilippov.studyingmap.view;

import com.alphilippov.studyingmap.network.dto.UserModelDto;
import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface SearchResultOfCoursesView extends MvpView {
    void loadProgressDownloading();
    void loadDataAdapter(List<UserModelDto>userModelDtoList);
    void checkConnectionInternet();
}
