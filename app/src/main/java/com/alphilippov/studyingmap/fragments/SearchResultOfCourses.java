package com.alphilippov.studyingmap.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.alphilippov.studyingmap.R;
import com.alphilippov.studyingmap.network.NetworkService;
import com.alphilippov.studyingmap.network.dto.UserModelDto;
import com.alphilippov.studyingmap.ui.DataAdapter;
import com.alphilippov.studyingmap.ui.RecyclerViewClickListener;
import com.alphilippov.studyingmap.ui.RecyclerViewTouchListener;
import com.alphilippov.studyingmap.utils.AppConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultOfCourses extends Fragment {
    private static final String SEARCH_RESULT = "result";
    private static final String HIGH_INT_KEY = "high";
    private static final String MIDDLE_INT_KEY = "middle";
    private static final String LOW_INT_KEY = "low";
    private static final String TAG = SearchResultOfCourses.class.getName();
    private ArrayList<UserModelDto.Result> moreUserModel = new ArrayList<>();
    public List<String> intellectualList = new ArrayList<>();
    public List<String> realistList = new ArrayList<>();
    public List<String> socialList = new ArrayList<>();
    public List<String> officeList = new ArrayList<>();
    public List<String> entrepreneuriaList = new ArrayList<>();
    public List<String> artistictList = new ArrayList<>();
    public List<List<String>> intGroup = new ArrayList<>();
    public List<String> finish = new ArrayList<>();
    private HashMap<String, List<String>> mListHashMap;
    private HashMap<String,List<UserModelDto.Result>> mHash ;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
    private boolean isScrolling;
    private int page = 1;
    private int indexInterest = 0;
    private int lastVisiblePosition;
    private Bundle savedState = null;
    private Parcelable mListState;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataList();
        Log.i(TAG, "OnCreate");
        Bundle bundle = new Bundle(getArguments());
        mListHashMap = (HashMap<String, List<String>>) bundle.getSerializable(SEARCH_RESULT);
        compareGroup(mListHashMap);
        //TODO : Проверить соотвествие страниц и индекса интересов
        NetworkService.restUdemy().getResult(page,
                AppConfig.PropertiesRequest.PAGE_SIZE,
                finish.get(indexInterest),
                AppConfig.PropertiesRequest.PRICE,
                AppConfig.PropertiesRequest.AFFILIATE,
                "en",
                AppConfig.PropertiesRequest.LEVEL_COURSES,
                AppConfig.PropertiesRequest.ORDERING,
                AppConfig.PropertiesRequest.RATINGS).enqueue(new Callback<UserModelDto>() {
            @Override
            public void onResponse(Call<UserModelDto> call, Response<UserModelDto> response) {
                moreUserModel.addAll(response.body().getResults());
                generateContent(moreUserModel);
            }

            @Override
            public void onFailure(Call<UserModelDto> call, Throwable t) {

            }


        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "OnCreateView");
        View qView = inflater.inflate(R.layout.search_result_of_courses, container, false);
        mRecyclerView = qView.findViewById(R.id.list);

        if (savedInstanceState != null && savedState == null) {
            //Берем из бандла , если фрагмент был уничтожен при повороте
            savedState = savedInstanceState.getBundle("save");
        }
        if (savedState != null) {
        //Иначе мы берем из ранее сохранненого состояния экземпляра onDestroyView
            mListState = savedState().getParcelable("saveState");
            mRecyclerView.getLayoutManager().onRestoreInstanceState(mListState);


        }
        return qView;
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, "onDestroyView");
        super.onDestroyView();
        savedState = savedState();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i(TAG, "SaveState");
        outState.putBundle("save", (savedState != null) ? savedState : savedState());
        super.onSaveInstanceState(outState);
    }

    private Bundle savedState() {
        Bundle state = new Bundle();
        mListState = mRecyclerView.getLayoutManager().onSaveInstanceState();
        state.putParcelable("saveState", mListState);
        return state;
    }

    @Override
    public void onPause() {
        Log.i(TAG, "OnPause");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.i(TAG, "OnResume");

        super.onResume();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "OnStop");
        super.onStop();
    }

    RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true;
            }
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int currentItems = mLinearLayoutManager.getChildCount();
            int totalItems = mLinearLayoutManager.getItemCount();
            int scrollItems = mLinearLayoutManager.findFirstVisibleItemPosition();
            if (isScrolling) {
                if ((currentItems + scrollItems) == totalItems
                        && scrollItems >= 0) {
                    isScrolling = false;
                    lastVisiblePosition = mLinearLayoutManager.findLastVisibleItemPosition();
                    loadMoreInformation(page, indexInterest);
                    paginationPage(finish);


                }
            }
        }

    };


    private void compareGroup(HashMap hashMap) {
        String[] mNameGroupInterest = {"realist", "intellectual", "social", "office", "entrepreneurial", "artistic"};
        String[] keyArray = {HIGH_INT_KEY, MIDDLE_INT_KEY, LOW_INT_KEY};

        for (int j = 0; j <= keyArray.length - 1; j++) {
            for (int i = 0; i <= mNameGroupInterest.length - 1; i++) {
                if (getListIntHshMap(hashMap, keyArray[j]).contains(mNameGroupInterest[i])
                        && getListIntHshMap(hashMap, keyArray[j]).size() > 0)
                    getPositionList(i, intGroup);

            }

        }
    }

    public void paginationPage(List<String> finish) {
        if (finish.size() != 0) {
            page++;
        } else {
            indexInterest++;
        }
    }

    private void initDataList() {
        intellectualList.add("web-development");
        intellectualList.add("mobile-apps");
        intellectualList.add("programming-languages");
        intellectualList.add("databases");
        intellectualList.add("software-testing");
        intellectualList.add("game-development");
        realistList.add("interior-design");
        realistList.add("home-improvement");
        realistList.add("architectural-design");
        realistList.add("yoga");
        realistList.add("massage");
        realistList.add("acupressure");
        realistList.add("aromatherapy");
        realistList.add("life-coaching");
        realistList.add("reflexology");
        socialList.add("psychology-fundamentals");
        socialList.add("social-psychology");
        socialList.add("accounting");
        socialList.add("counseling");
        socialList.add("digital-marketing");
        socialList.add("advertising");
        socialList.add("public-relations");
        socialList.add("marketing-fundamentals");
        socialList.add("branding");
        socialList.add("social-media-marketing");
        officeList.add("accounting");
        officeList.add("digital-marketing");
        officeList.add("economics");
        officeList.add("management");
        entrepreneuriaList.add("business-law");
        entrepreneuriaList.add("home-business");
        entrepreneuriaList.add("leadership");
        entrepreneuriaList.add("human-resources");
        entrepreneuriaList.add("finance");
        entrepreneuriaList.add("entrepreneurship");
        entrepreneuriaList.add("communications");
        entrepreneuriaList.add("management");
        artistictList.add("design-thinking");
        artistictList.add("web-design");
        artistictList.add("mobile-app-design");
        artistictList.add("user-experience-design");
        artistictList.add("photography-fundamentals");
        artistictList.add("portraits");
        intGroup.add(intellectualList);
        intGroup.add(realistList);
        intGroup.add(socialList);
        intGroup.add(officeList);
        intGroup.add(entrepreneuriaList);
        intGroup.add(artistictList);

    }

    private List<String> getListIntHshMap(HashMap hashMap, String key) {
        return (List<String>) hashMap.get(key);

    }

    private void getPositionList(int i, List<List<String>> mList) {
        finish.addAll(mList.get(i));

    }


    private void generateContent(ArrayList<UserModelDto.Result> results) {
        Log.i(TAG, String.valueOf(lastVisiblePosition));
        DataAdapter mData = new DataAdapter(getContext(), results);
        mRecyclerView.setAdapter(mData);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.getLayoutManager().scrollToPosition(lastVisiblePosition);
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);
        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getContext(),
                mRecyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getContext(), results.get(position).getTitle()
                        + " is clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(), "will be added to favorites", Toast.LENGTH_SHORT).show();

            }
        }));
    }

    private void loadMoreInformation(int page, int indexInterest) {

        NetworkService.restUdemy().getResult(page,
                AppConfig.PropertiesRequest.PAGE_SIZE,
                finish.get(indexInterest),
                AppConfig.PropertiesRequest.PRICE,
                AppConfig.PropertiesRequest.AFFILIATE,
                "en",
                AppConfig.PropertiesRequest.LEVEL_COURSES,
                AppConfig.PropertiesRequest.ORDERING,
                AppConfig.PropertiesRequest.RATINGS).enqueue(new Callback<UserModelDto>() {
            @Override
            public void onResponse(Call<UserModelDto> call, Response<UserModelDto> response) {
                moreUserModel.addAll(response.body().getResults());
                generateContent(moreUserModel);
            }

            @Override
            public void onFailure(Call<UserModelDto> call, Throwable t) {

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
