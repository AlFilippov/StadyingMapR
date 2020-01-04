package com.alphilippov.studyingmap.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.alphilippov.studyingmap.R;
import com.alphilippov.studyingmap.network.RestService;
import com.alphilippov.studyingmap.network.dto.UserModelDto;
import com.alphilippov.studyingmap.ui.DataAdapter;
import com.alphilippov.studyingmap.ui.RecyclerViewClickListener;
import com.alphilippov.studyingmap.ui.RecyclerViewTouchListener;
import com.alphilippov.studyingmap.utils.AppConfig;
import com.google.common.io.BaseEncoding;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static androidx.constraintlayout.Constraints.TAG;

public class ResultsAfterSearchKeyWords extends Fragment {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
    private ArrayList<UserModelDto.Result> moreUserModel = new ArrayList<>();
    private ArrayList<UserModelDto.Result> userModeltoInfAboutCourse = new ArrayList<>();
    private String keyWords;
    private boolean isScrolling;
    private int lastVisiblePosition;
    private int page = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View qView = inflater.inflate(R.layout.result_search_key_words, container, false);
        mRecyclerView = qView.findViewById(R.id.result_search_key_words_list);

        return qView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = new Bundle(getArguments());
        keyWords = bundle.getString("ok");
        initRequest(page);
        bundle.remove("ok");
        super.onCreate(savedInstanceState);
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
                    loadMoreInformation(page);
                    page++;


                }
            }
        }
    };

    public void initRequest(int page) {
        configurationRetrofitForUdemyApi().getResult(page,
                AppConfig.PropertiesRequest.PAGE_SIZE,
                keyWords,
                AppConfig.PropertiesRequest.PRICE,
                AppConfig.PropertiesRequest.AFFILIATE,
                AppConfig.PropertiesRequest.LANGUAGE,
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
                Log.d(TAG, t.toString());
            }
        });
    }

    public RestService configurationRetrofitForUdemyApi() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", "Basic " + getBasicAuthenticator());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }).addInterceptor(httpLoggingInterceptor).build();


        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return mRetrofit.create(RestService.class);
    }

    private String getBasicAuthenticator() {
        String authStr = AppConfig.Authorization.CLIENT_ID + ":" + AppConfig.Authorization.CLIENT_SECRET;
        return BaseEncoding.base64().encode(authStr.getBytes());

    }

    private void generateContent(ArrayList<UserModelDto.Result> results) {
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
                userModeltoInfAboutCourse.add(results.get(position));
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(), "will be added to favorites", Toast.LENGTH_SHORT).show();

            }
        }));
    }

    private void loadMoreInformation(int page) {

        configurationRetrofitForUdemyApi().getResult(page,
                AppConfig.PropertiesRequest.PAGE_SIZE,
                keyWords,
                AppConfig.PropertiesRequest.PRICE,
                false,
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
                Log.d(TAG, t.toString());
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}

