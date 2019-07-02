package com.alphilippov.studyingmap.databind;

public class VariantLearningBinding {
    private String mTextViewVariantLearning;
    private String mOnePartButtonLearning;
    private String mTwoPartButtonLearning;

    public VariantLearningBinding(String textViewVariantLearning, String onePartButtonLearning, String twoPartButtonLearning) {
        mTextViewVariantLearning = textViewVariantLearning;
        mOnePartButtonLearning = onePartButtonLearning;
        mTwoPartButtonLearning = twoPartButtonLearning;
    }

    public String getTextViewVariantLearning() {
        return mTextViewVariantLearning;
    }

    public void setTextViewVariantLearning(String textViewVariantLearning) {
        mTextViewVariantLearning = textViewVariantLearning;
    }

    public String getOnePartButtonLearning() {
        return mOnePartButtonLearning;
    }

    public void setOnePartButtonLearning(String onePartButtonLearning) {
        mOnePartButtonLearning = onePartButtonLearning;
    }

    public String getTwoPartButtonLearning() {
        return mTwoPartButtonLearning;
    }

    public void setTwoPartButtonLearning(String twoPartButtonLearning) {
        mTwoPartButtonLearning = twoPartButtonLearning;
    }
}
