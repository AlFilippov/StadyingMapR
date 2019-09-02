package com.alphilippov.studyingmap.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.alphilippov.studyingmap.R;
import com.alphilippov.studyingmap.network.dto.UserModelDto;
import com.alphilippov.studyingmap.ui.DataAdapter;
import com.alphilippov.studyingmap.ui.RecyclerViewClickListener;
import com.alphilippov.studyingmap.ui.RecyclerViewTouchListener;
import com.alphilippov.studyingmap.view.SearchCoursesAfterTestView;

import java.util.ArrayList;
import java.util.List;

import moxy.MvpFragment;

public class SearchResultOfCoursesFragment extends MvpFragment implements SearchCoursesAfterTestView {

    private static final String TAG = SearchResultOfCoursesFragment.class.getName();
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
    private boolean isScrolling;
    private int lastVisiblePosition;
    private Bundle savedState = null;
    private Parcelable mListState;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "OnCreate");
        Bundle bundle = new Bundle(getArguments());
        //TODO : initial infs , parse bundle ,down inf

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "OnCreateView");
        View qView = inflater.inflate(R.layout.search_result_of_courses, container, false);
        mRecyclerView = qView.findViewById(R.id.list);
        return qView;
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
                    //TODO:Download more infs  and increment pagination state


                }
            }
        }

    };


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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void loadProgressDownloading() {

    }

    @Override
    public void loadDataAdapter(List<UserModelDto> userModelDtoList) {

    }

    @Override
    public void checkConnectionInternet() {

    }
}
