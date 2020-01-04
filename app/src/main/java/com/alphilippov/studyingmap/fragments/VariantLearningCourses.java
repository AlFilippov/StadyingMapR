package com.alphilippov.studyingmap.fragments;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphilippov.studyingmap.R;
import com.alphilippov.studyingmap.databind.VariantLearningBinding;
import com.alphilippov.studyingmap.databinding.VariableLearningCoursesBinding;
import com.alphilippov.studyingmap.utils.AppConfig;

public class VariantLearningCourses extends Fragment {

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
                mChangedFragment.ChangedFragment(AppConfig.ChangeFragment.FORM_COURSES_KEY_WORDS);
                break;
            case R.id.wdefinition:
                mChangedFragment.ChangedFragment(AppConfig.ChangeFragment.PROFESSION_DEFINITION);
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
