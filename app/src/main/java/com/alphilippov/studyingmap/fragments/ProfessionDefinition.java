package com.alphilippov.studyingmap.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alphilippov.studyingmap.R;
import com.alphilippov.studyingmap.databind.ProfessionBinding;
import com.alphilippov.studyingmap.databinding.ProfessionDefinitionBinding;
import com.alphilippov.studyingmap.network.UdemyApi;
import com.alphilippov.studyingmap.presenter.ProfessionDefinitionPresenter;
import com.alphilippov.studyingmap.view.ProfessionDefinitionView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;


public class ProfessionDefinition extends MvpAppCompatActivity implements ProfessionDefinitionView, View.OnClickListener {

    private TextView mCountQue;
    private UdemyApi udemyApi;
    @InjectPresenter
    ProfessionDefinitionPresenter professionDefinitionPresenter;

    @ProvidePresenter
    ProfessionDefinitionPresenter provideRepositoryPresenter() {
        return new ProfessionDefinitionPresenter(udemyApi);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProfessionDefinitionBinding binding = DataBindingUtil.setContentView(this, R.layout.profession_definition);
        binding.setProfessionalDefinitionDB(new ProfessionBinding(
                "Choose one of the two",
                "Question",
                "Mechanic",
                "Physiotherapist",
                " 1 ",
                " of ",
                " 31 "));
        mCountQue = binding.countQuestionTwoPart;
        Button mOnePartButton = binding.OnePartButton;
        Button mTwoPartButton = binding.TwoPartButton;
        mOnePartButton.setOnClickListener(this);
        mTwoPartButton.setOnClickListener(this);

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
        Toast.makeText(this, "Questions is end", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}


