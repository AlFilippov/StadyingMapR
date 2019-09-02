package com.alphilippov.studyingmap.presenter;

import com.alphilippov.studyingmap.fragments.SearchResultOfCoursesFragment;
import com.alphilippov.studyingmap.network.dto.UserModelDto;
import com.alphilippov.studyingmap.view.SearchCoursesAfterTestView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import moxy.InjectViewState;
import moxy.MvpPresenter;


@InjectViewState
public class SearchCoursesAfterTestPresenter extends MvpPresenter<SearchCoursesAfterTestView> {
    private static final String SEARCH_RESULT = "result";
    private static final String HIGH_INT_KEY = "high";
    private static final String MIDDLE_INT_KEY = "middle";
    private static final String LOW_INT_KEY = "low";
    private static final String TAG = SearchResultOfCoursesFragment.class.getName();
    private ArrayList<UserModelDto.Result> mLoadDataCategory = new ArrayList<>();
    public List<String> mIntellectualList = new ArrayList<>();
    public List<String> mRealistList = new ArrayList<>();
    public List<String> mSocialList = new ArrayList<>();
    public List<String> mOfficeList = new ArrayList<>();
    public List<String> mEntrepreneuriaList = new ArrayList<>();
    public List<String> mArtistictList = new ArrayList<>();
    public List<List<String>> mIntGroup = new ArrayList<>();
    public List<String> mFinallyUserChoice = new ArrayList<>();
    private HashMap<String, List<String>> mListHashMap;
    private HashMap<String,List<UserModelDto.Result>> mHash ;
    private int mPage = 1;
    private int mIndexInterest = 0;

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
    private void getPositionList(int i, List<List<String>> mList) {
        mFinallyUserChoice.addAll(mList.get(i));

    }
    public void paginationPage(List<String> finish) {
        if (finish.size() != 0) {
            mPage++;
        } else {
            mIndexInterest++;
        }
    }
    private void initDataList() {
        mIntellectualList.add("web-development");
        mIntellectualList.add("mobile-apps");
        mIntellectualList.add("programming-languages");
        mIntellectualList.add("databases");
        mIntellectualList.add("software-testing");
        mIntellectualList.add("game-development");
        mRealistList.add("interior-design");
        mRealistList.add("home-improvement");
        mRealistList.add("architectural-design");
        mRealistList.add("yoga");
        mRealistList.add("massage");
        mRealistList.add("acupressure");
        mRealistList.add("aromatherapy");
        mRealistList.add("life-coaching");
        mRealistList.add("reflexology");
        mSocialList.add("psychology-fundamentals");
        mSocialList.add("social-psychology");
        mSocialList.add("accounting");
        mSocialList.add("counseling");
        mSocialList.add("digital-marketing");
        mSocialList.add("advertising");
        mSocialList.add("public-relations");
        mSocialList.add("marketing-fundamentals");
        mSocialList.add("branding");
        mSocialList.add("social-media-marketing");
        mOfficeList.add("accounting");
        mOfficeList.add("digital-marketing");
        mOfficeList.add("economics");
        mOfficeList.add("management");
        mEntrepreneuriaList.add("business-law");
        mEntrepreneuriaList.add("home-business");
        mEntrepreneuriaList.add("leadership");
        mEntrepreneuriaList.add("human-resources");
        mEntrepreneuriaList.add("finance");
        mEntrepreneuriaList.add("entrepreneurship");
        mEntrepreneuriaList.add("communications");
        mEntrepreneuriaList.add("management");
        mArtistictList.add("design-thinking");
        mArtistictList.add("web-design");
        mArtistictList.add("mobile-app-design");
        mArtistictList.add("user-experience-design");
        mArtistictList.add("photography-fundamentals");
        mArtistictList.add("portraits");
        mIntGroup.add( mIntellectualList);
        mIntGroup.add(mRealistList);
        mIntGroup.add(mSocialList);
        mIntGroup.add(mOfficeList);
        mIntGroup.add(mEntrepreneuriaList);
        mIntGroup.add(mArtistictList);

    }

    private List<String> getListIntHshMap(HashMap hashMap, String key) {
        return (List<String>) hashMap.get(key);

    }
    private void compareGroup(HashMap hashMap) {
        String[] mNameGroupInterest = {"realist", "intellectual", "social", "office", "entrepreneurial", "artistic"};
        String[] keyArray = {HIGH_INT_KEY, MIDDLE_INT_KEY, LOW_INT_KEY};

        for (int j = 0; j <= keyArray.length - 1; j++) {
            for (int i = 0; i <= mNameGroupInterest.length - 1; i++) {
                if (getListIntHshMap(hashMap, keyArray[j]).contains(mNameGroupInterest[i])
                        && getListIntHshMap(hashMap, keyArray[j]).size() > 0)
                    getPositionList(i, mIntGroup);

            }

        }
    }

}
