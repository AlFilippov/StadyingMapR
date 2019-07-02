package com.alphilippov.studyingmap.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
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
import com.alphilippov.studyingmap.databind.VariantLearningBinding;
import com.alphilippov.studyingmap.databinding.VariableLearningCoursesBinding;

public class VariantLearningCourses extends Fragment {

    private static final String YES_DECIDED = "ydecided";
    private static final String WANT_DEFENITION = "wdecided";
    private VariableLearningCoursesBinding mBinding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.variable_learning_courses, container, false);
        mBinding.setVariantLearningDB(new VariantLearningBinding(
                "Do you know what you want to know ?",
                "No,I want to decide",
                "I know"));
        mBinding.setOnClick(this::onClick);
        return mBinding.getRoot();

    }


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
