package com.alphilippov.studyingmap.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alphilippov.studyingmap.R;

public class VariableLearningCourses extends Fragment implements Button.OnClickListener {

    private static final String YES_DECIDED = "ydecided";
    private static final String WANT_DEFENITION = "wdecided";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View askMe = inflater.inflate(R.layout.variable_learning_courses, container, false);
        TextView mAskMainQuestion = askMe.findViewById(R.id.askMe);
        Button mYDecided = askMe.findViewById(R.id.ydecided);
        Button mWDefenition = askMe.findViewById(R.id.wdefinition);
        mYDecided.setOnClickListener(this);
        mWDefenition.setOnClickListener(this);
        return askMe;

    }

    @Override
    public void onClick(View button) {
        switch (button.getId()) {
            case R.id.ydecided:
                mChangedFragment.ChangedFragment(YES_DECIDED);
                break;
            case R.id.wdefinition:
                mChangedFragment.ChangedFragment(WANT_DEFENITION);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public interface OnChangedFragment {
        void ChangedFragment(String s);

    }

    OnChangedFragment mChangedFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mChangedFragment = (OnChangedFragment) context;

    }


}
