package com.alphilippov.studyingmap.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alphilippov.studyingmap.R;
import com.alphilippov.studyingmap.databind.ProfessionBinding;
import com.alphilippov.studyingmap.databinding.ProfessionDefinitionBinding;
import com.alphilippov.studyingmap.presenter.ProfessionDefinitionPresenter;
import com.alphilippov.studyingmap.utils.AppConfig;
import com.alphilippov.studyingmap.view.ProfessionDefinitionView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import moxy.MvpFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class ProfessionDefinitionFragment extends MvpFragment implements ProfessionDefinitionView {

    protected HashMap<String, List<String>> mHashInterest = new HashMap<>();
    private TextView mCountQue;
    private Button mOnePartButton;
    private Button mTwoPartButton;
    @InjectPresenter
    ProfessionDefinitionPresenter professionDefinitionPresenter;

    @ProvidePresenter
    ProfessionDefinitionPresenter provideRepoPresenter() {
        //TODO:Прокинуь context

    }

    ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//TODO:Инициализировать загрузку БД
        String pathdatabase = getContext().getDatabasePath("professiondata").getAbsolutePath();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ProfessionDefinitionBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.profession_definition, container, false);
        binding.setProfessionalDefinitionDB(new ProfessionBinding(
                "Choose one of the two",
                "Question",
                "Mechanic",
                "Physiotherapist",
                " 1 ",
                " of ",
                " 31 "));
        mCountQue = binding.countQuestionTwoPart;
        mOnePartButton = binding.OnePartButton;
        mTwoPartButton = binding.TwoPartButton;


        mOnePartButton.setOnClickListener(view -> {
            professionDefinitionPresenter.isClickedButtons(true);
            professionDefinitionPresenter.isClickedGeneral(true);
        });
        mTwoPartButton.setOnClickListener(view -> {
            professionDefinitionPresenter.isClickedButtons(false);
            professionDefinitionPresenter.isClickedGeneral(true);
        });

        return binding.getRoot();
    }


    private void replaceFragment() {
        mSentDataFragment.onSentData(AppConfig.ChangeFragment.SEARCH_RESULT_OF_COURSES, mHashInterest);

    }

    public void saveFile(JSONObject jsonObject, String nameFile) {

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Objects.requireNonNull(getContext()).
                    openFileOutput(nameFile, Context.MODE_PRIVATE));
            outputStreamWriter.write(jsonObject.toString());
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    @Override
    public void setTextOnePartButton(String onePartButton) {
        mOnePartButton.setText(onePartButton);
    }

    @Override
    public void setTextTwoPartButton(String twoPartButton) {
        mTwoPartButton.setText(twoPartButton);
    }

    @Override
    public void setCountTextQuestion(String value) {
        mCountQue.setText(value);
    }

    @Override
    public void showToastQuestionEnded() {
        Toast.makeText(getContext(), "Great!", Toast.LENGTH_SHORT).show();
    }


    public interface sentDataFragment {
        void onSentData(String d, HashMap hashMap);
    }

    sentDataFragment mSentDataFragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mSentDataFragment = (sentDataFragment) context;
    }


}


