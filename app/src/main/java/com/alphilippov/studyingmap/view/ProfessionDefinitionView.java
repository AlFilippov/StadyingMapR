package com.alphilippov.studyingmap.view;


import moxy.MvpView;

public interface ProfessionDefinitionView extends MvpView {
    void setTextOnePartButton(String onePartButton);
    void setTextTwoPartButton(String twoPartButton);
    void setCountTextQuestion(String value);
    void showToastQuestionEnded();

}
