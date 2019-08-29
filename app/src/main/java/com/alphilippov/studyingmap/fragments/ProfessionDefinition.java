package com.alphilippov.studyingmap.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alphilippov.studyingmap.R;
import com.alphilippov.studyingmap.databind.ProfessionBinding;
import com.alphilippov.studyingmap.databinding.ProfessionDefinitionBinding;
import com.alphilippov.studyingmap.network.UdemyApi;
import com.alphilippov.studyingmap.presenter.ProfessionDefinitionPresenter;
import com.alphilippov.studyingmap.view.ProfessionDefinitionView;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class ProfessionDefinition extends MvpAppCompatFragment implements ProfessionDefinitionView, View.OnClickListener {

    private TextView mCountQue;
    private UdemyApi udemyApi;
    private ProfessionDefinitionBinding mProfessionDefBinding;
    @InjectPresenter
    ProfessionDefinitionPresenter professionDefinitionPresenter;

    @ProvidePresenter
    ProfessionDefinitionPresenter provideRepositoryPresenter() {
        return new ProfessionDefinitionPresenter(udemyApi);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mProfessionDefBinding = DataBindingUtil.inflate(inflater, R.layout.profession_definition, container, false);
        mProfessionDefBinding.setProfessionalDefinitionDB(new ProfessionBinding(
                "Choose one of the two",
                "Question",
                "Mechanic",
                "Physiotherapist",
                " 1 ",
                " of ",
                " 31 "));
        mCountQue = mProfessionDefBinding.countQuestionTwoPart;
        Button mOnePartButton = mProfessionDefBinding.OnePartButton;
        Button mTwoPartButton = mProfessionDefBinding.TwoPartButton;
        mOnePartButton.setOnClickListener(this);
        mTwoPartButton.setOnClickListener(this);
        return mProfessionDefBinding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        professionDefinitionPresenter.initializeObjectProfession();
    }

    @Override
    public void onClick(View view) {
        professionDefinitionPresenter.isClickedGeneral(true);
        switch (view.getId()) {
            case R.id.OnePartButton:
                professionDefinitionPresenter.isClickedButtons(true);
            case R.id.TwoPartButton:
                professionDefinitionPresenter.isClickedButtons(false);
        }

    }


    @Override
    public void setTextOnePartButton(String onePartButton) {

    }

    @Override
    public void setTextTwoPartButton(String twoPartButton) {

    }

    @Override
    public void setCountTextQuestion(String value) {
        mCountQue.setText(value);
    }

    @Override
    public void showToastQuestionEnded() {
        Toast.makeText(getContext(), "Thank you for  your patience", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}


