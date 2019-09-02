package com.alphilippov.studyingmap.view;


import moxy.MvpView;
//TODO:Указать стратегию viewstate
public interface ProfessionDefinitionView extends MvpView {
    void setTextOnePartButton(String onePartButton);
    void setTextTwoPartButton(String twoPartButton);
    void setCountTextQuestion(String value);
    void showToastQuestionEnded();

}
