package com.alphilippov.studyingmap.presenter;

import com.alphilippov.studyingmap.view.SearchCoursesAfterTestView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class SearchCoursesAfterTestPresenter extends MvpPresenter<SearchCoursesAfterTestView> {
    public void setDataAdapter(){
        getViewState().loadDataAdapter();
    }
    public void progressLoading(boolean load){
        if(load)
        getViewState().loadProgressDownloading();
      

    }
    public void checkedInternetConnection(boolean check){
       if(check)
           getViewState().checkConnectionInternet();
    }
}
