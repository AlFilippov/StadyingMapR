package com.alphilippov.studyingmap.helperclasses;

public class HumanInterest {
    protected int mLowInterest;
    protected int mHighInterest;
    protected String mInteres ;

    public HumanInterest(int lowInterest, int highInterest, String interes) {
        mLowInterest = lowInterest;
        mHighInterest = highInterest;
        mInteres = interes;
    }

    public int getLowInterest() {
        return mLowInterest;
    }

    public int getHighInterest() {
        return mHighInterest;
    }

    public String getInteres() {
        return mInteres;
    }
}