package com.alphilippov.studyingmap.presenter;

import com.alphilippov.studyingmap.fragments.ProfessionDefinition;
import com.alphilippov.studyingmap.helperclasses.ProfessionalDefinition;
import com.alphilippov.studyingmap.network.UdemyApi;
import com.alphilippov.studyingmap.network.dto.UserModelDto;
import com.alphilippov.studyingmap.view.ProfessionDefinitionView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class ProfessionDefenitionPresenter extends MvpPresenter<ProfessionDefinitionView> {
    private UdemyApi udemyApi;
    private int count = 1;
    private List<ProfessionalDefinition> onePartProfession = new ArrayList<>();
    private List<ProfessionalDefinition> twoPartProfession = new ArrayList<>();

    public ProfessionDefenitionPresenter(UdemyApi udemyApi) {
        this.udemyApi = udemyApi;
    }

    public void isClickedGeneral(boolean click) {
        if (click) {
            setTextOnePartButton();
            setTextTwoPartButton();
            getViewState().setwCountTextQuestion(String.valueOf(count));
            count++;
        } else {
            getViewState().showToastQuestionEnded();
        }
    }

    public void isClickedButtons(boolean clickButton) {
        if (clickButton) {
            loadChoiceinData(fixChoiceQuestion(onePartProfession, count));
        } else {
            loadChoiceinData(fixChoiceQuestion(twoPartProfession, count));
        }

    }

    public void setTextOnePartButton() {
        getViewState().setTextOnePartButton("SomeData");
    }

    public void setTextTwoPartButton() {
        getViewState().setTextTwoPartButton("SomeData");

    }

    public List<ProfessionalDefinition> fixChoiceQuestion(List<ProfessionalDefinition> results, int i) {
        List<ProfessionalDefinition> resultsChoice = new ArrayList<>();
        resultsChoice.add(results.get(i));
        return resultsChoice;
    }

    public void loadChoiceinData(List<ProfessionalDefinition> dataProfession) {

    }
}
