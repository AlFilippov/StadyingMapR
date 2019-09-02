package com.alphilippov.studyingmap.presenter;

import com.alphilippov.studyingmap.helperclasses.DistributionInterests;
import com.alphilippov.studyingmap.helperclasses.HumanInterest;
import com.alphilippov.studyingmap.helperclasses.ProfessionalDefinition;
import com.alphilippov.studyingmap.model.DataProfessionModel;
import com.alphilippov.studyingmap.network.UdemyApi;
import com.alphilippov.studyingmap.utils.AppConfig;
import com.alphilippov.studyingmap.view.ProfessionDefinitionView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class ProfessionDefinitionPresenter extends MvpPresenter<ProfessionDefinitionView> {
    private UdemyApi mUdemyApi;
    private int mCount = 1;
    private List<ProfessionalDefinition> mOnePartProfession = new ArrayList<>();
    private List<ProfessionalDefinition> mTwoPartProfession = new ArrayList<>();
    private List<ProfessionalDefinition> mSumResponseUsers = new ArrayList<>();
    private HashMap<String, List<String>> mInterest = new HashMap<>();
    private DistributionInterests mDistributionInterests;
    private DataProfessionModel mDataProfessionModel;

    public ProfessionDefinitionPresenter(UdemyApi udemyApi) {
        this.mUdemyApi = udemyApi;
    }


    public void isClickedGeneral(boolean click) {
        if (click && mCount <= mOnePartProfession.size()) {
            setTextOnePartButton();
            setTextTwoPartButton();

            mDataProfessionModel.insertProfession();


            getViewState().setCountTextQuestion(String.valueOf(mCount));
            mCount++;
        } else {
            /*
            Тут могу словить ексепшн
             */
            loadChoiceinData(mSumResponseUsers);
            mInterest.putAll(mDistributionInterests.collectInteresGroups());
            getViewState().showToastQuestionEnded();
        }
    }


    public void isClickedButtons(boolean clickButton) {
        if (clickButton) {
            fixChoiceQuestion(mOnePartProfession, mCount);
        } else {
            fixChoiceQuestion(mTwoPartProfession, mCount);
        }
    }


    private void setTextOnePartButton() {
        getViewState().setTextOnePartButton(setTextProfession(mOnePartProfession, mCount));
    }

    private void setTextTwoPartButton() {
        getViewState().setTextTwoPartButton(setTextProfession(mTwoPartProfession, mCount));

    }

    private String setTextProfession(List<ProfessionalDefinition> profession, int i) {
        return profession.get(i).getProfession();
    }

    private List<ProfessionalDefinition> fixChoiceQuestion(List<ProfessionalDefinition> results, int i) {
       mSumResponseUsers.add(results.get(i));
        return mSumResponseUsers;
    }

    private void loadChoiceinData(List<ProfessionalDefinition> dataProfession) {
        mDistributionInterests = new DistributionInterests(dataProfession);

    }
/*
    public void initializeObjectProfession() {
        onePartProfession.add(new ProfessionalDefinition(1, 0, "Mechanic"));
        onePartProfession.add(new ProfessionalDefinition(2, 1, "Information security specialist"));
        onePartProfession.add(new ProfessionalDefinition(4, 2, "Call center operator"));
        onePartProfession.add(new ProfessionalDefinition(1, 3, "Driver"));
        onePartProfession.add(new ProfessionalDefinition(2, 4, "Design Engineer"));
        onePartProfession.add(new ProfessionalDefinition(4, 5, "Air traffic controller"));
        onePartProfession.add(new ProfessionalDefinition(1, 6, "Veterinarian"));
        onePartProfession.add(new ProfessionalDefinition(2, 7, "Game developer "));
        onePartProfession.add(new ProfessionalDefinition(4, 8, "Laboratory assistant"));
        onePartProfession.add(new ProfessionalDefinition(1, 9, "Agronomist"));
        onePartProfession.add(new ProfessionalDefinition(2, 10, "Breeder"));
        onePartProfession.add(new ProfessionalDefinition(4, 11, "Marketer"));
        onePartProfession.add(new ProfessionalDefinition(1, 12, "Masseur"));
        onePartProfession.add(new ProfessionalDefinition(2, 13, "Teacher"));
        onePartProfession.add(new ProfessionalDefinition(4, 14, "Facility manager"));
        onePartProfession.add(new ProfessionalDefinition(1, 15, "Waiter"));
        onePartProfession.add(new ProfessionalDefinition(2, 16, "Psychologist"));
        onePartProfession.add(new ProfessionalDefinition(4, 17, "Insurance agent"));
        onePartProfession.add(new ProfessionalDefinition(1, 18, "Jeweler"));
        onePartProfession.add(new ProfessionalDefinition(2, 19, "Art Critic"));
        onePartProfession.add(new ProfessionalDefinition(4, 20, "Editor"));
        onePartProfession.add(new ProfessionalDefinition(1, 21, "Interior designer"));
        onePartProfession.add(new ProfessionalDefinition(2, 22, "Software tester"));
        onePartProfession.add(new ProfessionalDefinition(4, 23, "Copywriter"));
        onePartProfession.add(new ProfessionalDefinition(2, 24, "System Administrator"));
        onePartProfession.add(new ProfessionalDefinition(1, 25, "Carpenter"));
        onePartProfession.add(new ProfessionalDefinition(4, 26, "Corrector"));
        onePartProfession.add(new ProfessionalDefinition(1, 27, "Typewriter"));
        onePartProfession.add(new ProfessionalDefinition(2, 28, "Programmer"));
        onePartProfession.add(new ProfessionalDefinition(4, 29, "Accountant"));
        onePartProfession.add(new ProfessionalDefinition(7, 29, "Accountant"));


        twoPartProfession.add(new ProfessionalDefinition(3, 0, "Physiotherapist"));
        twoPartProfession.add(new ProfessionalDefinition(5, 1, "Logistics specialist"));
        twoPartProfession.add(new ProfessionalDefinition(6, 2, "Cameraman"));
        twoPartProfession.add(new ProfessionalDefinition(3, 3, "Cashier"));
        twoPartProfession.add(new ProfessionalDefinition(5, 4, "Auto Sales Manager"));
        twoPartProfession.add(new ProfessionalDefinition(6, 5, "Web designer"));
        twoPartProfession.add(new ProfessionalDefinition(3, 6, "Ecologist"));
        twoPartProfession.add(new ProfessionalDefinition(5, 7, "Farmer"));
        twoPartProfession.add(new ProfessionalDefinition(6, 8, "SEO specialist "));
        twoPartProfession.add(new ProfessionalDefinition(3, 9, "Sanitary doctor"));
        twoPartProfession.add(new ProfessionalDefinition(5, 10, "Agricultural Product Provider"));
        twoPartProfession.add(new ProfessionalDefinition(6, 11, "Landscape designer"));
        twoPartProfession.add(new ProfessionalDefinition(3, 12, "Tutor"));
        twoPartProfession.add(new ProfessionalDefinition(5, 13, "Entrepreneur"));
        twoPartProfession.add(new ProfessionalDefinition(6, 14, "Artist-animator"));
        twoPartProfession.add(new ProfessionalDefinition(3, 15, "Doctor"));
        twoPartProfession.add(new ProfessionalDefinition(5, 16, "Trading agent"));
        twoPartProfession.add(new ProfessionalDefinition(6, 17, "Choreographer"));
        twoPartProfession.add(new ProfessionalDefinition(3, 18, "Journalist"));
        twoPartProfession.add(new ProfessionalDefinition(5, 19, "Producer"));
        twoPartProfession.add(new ProfessionalDefinition(6, 20, "Musician"));
        twoPartProfession.add(new ProfessionalDefinition(3, 21, "Guide"));
        twoPartProfession.add(new ProfessionalDefinition(5, 22, "Art Director"));
        twoPartProfession.add(new ProfessionalDefinition(6, 23, "Theater and film actor"));
        twoPartProfession.add(new ProfessionalDefinition(3, 24, "Guide-translator"));
        twoPartProfession.add(new ProfessionalDefinition(5, 25, "Crisis Manager"));
        twoPartProfession.add(new ProfessionalDefinition(6, 26, "Art editor"));
        twoPartProfession.add(new ProfessionalDefinition(3, 27, "Legal Counsel"));
        twoPartProfession.add(new ProfessionalDefinition(5, 28, "Broker"));
        twoPartProfession.add(new ProfessionalDefinition(6, 29, "Literary translator"));
        twoPartProfession.add(new ProfessionalDefinition(7, 29, "Literary translator"));
    }
*/

}
