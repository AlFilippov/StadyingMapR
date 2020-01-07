package com.alphilippov.studyingmapnew.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.alphilippov.studyingmapnew.R;

public class FormCoursesKeyWords extends Fragment {
    public String keyWords;
    public EditText searchWords;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View searchView = inflater.inflate(R.layout.form_request_for_search, container, false);
        searchWords = searchView.findViewById(R.id.content_edit_text_request);
        searchWords.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                keyWords = s.toString();
            }
        });
/*
        Button buttonSearch = searchView.findViewById(R.id.content_button_request);
        buttonSearch.setOnClickListener(v -> {
            if (keyWords != null) {
                mReplaceFragment.sentDataInSearchInUdemy("ok", keyWords);
            } else {
                Toast.makeText(getContext(), "Enter value", Toast.LENGTH_SHORT).show();
            }
        });

 */
        return searchView;
    }


    public interface replaceFragment {

        void sentDataInSearchInUdemy(String keyChangeFragment, String keyWords);
    }

    replaceFragment mReplaceFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mReplaceFragment = (replaceFragment) context;
    }

}
