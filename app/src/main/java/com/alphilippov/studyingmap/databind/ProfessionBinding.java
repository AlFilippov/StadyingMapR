package com.alphilippov.studyingmap.databind;

public class ProfessionBinding {
    private String mTextChoiceQuestion;
    private String mTextChangeQuestionOnePart;
    private String mButtonOnePart;
    private String mButtonTwoPart;
private String mChangeQuestionTwoPart;
private String mChangeQuestionThreePart;
private String mChangeQuestionFourPart;

    public ProfessionBinding(String textChoiceQuestion, String textChangeQuestionOnePart, String buttonOnePart, String buttonTwoPart, String changeQuestionTwoPart, String changeQuestionThreePart, String changeQuestionFourPart) {
        mTextChoiceQuestion = textChoiceQuestion;
        mTextChangeQuestionOnePart = textChangeQuestionOnePart;
        mButtonOnePart = buttonOnePart;
        mButtonTwoPart = buttonTwoPart;
        mChangeQuestionTwoPart = changeQuestionTwoPart;
        mChangeQuestionThreePart = changeQuestionThreePart;
        mChangeQuestionFourPart = changeQuestionFourPart;
    }

    public String getTextChoiceQuestion() {
        return mTextChoiceQuestion;
    }

    public void setTextChoiceQuestion(String textChoiceQuestion) {
        mTextChoiceQuestion = textChoiceQuestion;
    }

    public String getTextChangeQuestionOnePart() {
        return mTextChangeQuestionOnePart;
    }

    public void setTextChangeQuestionOnePart(String textChangeQuestionOnePart) {
        mTextChangeQuestionOnePart = textChangeQuestionOnePart;
    }

    public String getButtonOnePart() {
        return mButtonOnePart;
    }

    public void setButtonOnePart(String buttonOnePart) {
        mButtonOnePart = buttonOnePart;
    }

    public String getButtonTwoPart() {
        return mButtonTwoPart;
    }

    public void setButtonTwoPart(String buttonTwoPart) {
        mButtonTwoPart = buttonTwoPart;
    }

    public String getChangeQuestionTwoPart() {
        return mChangeQuestionTwoPart;
    }

    public void setChangeQuestionTwoPart(String changeQuestionTwoPart) {
        mChangeQuestionTwoPart = changeQuestionTwoPart;
    }

    public String getChangeQuestionThreePart() {
        return mChangeQuestionThreePart;
    }

    public void setChangeQuestionThreePart(String changeQuestionThreePart) {
        mChangeQuestionThreePart = changeQuestionThreePart;
    }

    public String getChangeQuestionFourPart() {
        return mChangeQuestionFourPart;
    }

    public void setChangeQuestionFourPart(String changeQuestionFourPart) {
        mChangeQuestionFourPart = changeQuestionFourPart;
    }
}
