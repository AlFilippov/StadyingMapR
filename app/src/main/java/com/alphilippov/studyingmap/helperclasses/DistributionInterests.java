package com.alphilippov.studyingmap.helperclasses;

import com.alphilippov.studyingmap.utils.AppConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DistributionInterests {
    private static final String HIGH_INT_KEY = "high";
    private static final String MIDDLE_INT_KEY = "middle";
    private static final String LOW_INT_KEY = "low";
    private List<ProfessionalDefinition> mProfessionalDefinitions;
    private HashMap<String, List<String>> mHashInterest = new HashMap<>();
    private List<String> mHighInterest = new ArrayList<>();
    private List<String> mMiddleInterest = new ArrayList<>();
    private List<String> mLowInterest = new ArrayList<>();
    private List<String> mHighIntGroup = new ArrayList<>();
    private List<String> mMiddleIntGroup = new ArrayList<>();
    private List<String> mLowIntGroup = new ArrayList<>();
    private List<String> mHighInterestBefore = new ArrayList<>();
    private List<String> mMiddleInterestBefore = new ArrayList<>();
    private List<String> mLowInterestBefore = new ArrayList<>();

    public DistributionInterests(List<ProfessionalDefinition> professionalDefinitions) {
        this.mProfessionalDefinitions = professionalDefinitions;
    }

    public HashMap<String, List<String>> collectInteresGroups() {
        int realist = (int) mProfessionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.REALIST).count();
        int intellectual = (int) mProfessionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.INTELLECTUAL).count();
        int social = (int) mProfessionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.SOCIAL).count();
        int office = (int)mProfessionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.OFFICE).count();
        int entrepreneurial = (int) mProfessionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.ENTREPRENEURIAL).count();
        int artistic = (int) mProfessionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.ARTISTIC).count();
        HumanInterest mHigh = new HumanInterest(8, 10, "High");
        HumanInterest mMiddle = new HumanInterest(5, 7, "Middle");
        HumanInterest mLow = new HumanInterest(0, 4, "Low");
        int[] arr = {realist, intellectual, social, office, entrepreneurial, artistic};

        String[] mNameGroupInteres = {"realist", "intellectual", "social", "office", "entrepreneurial", "artistic"};
        int[] arrays = {1, 2, 3, 4, 5, 6};

        for (int i = 0; i <= arr.length - 1; i++) {
            if (inGroup(mHigh.getLowInterest(), mHigh.getHighInterest(), arr[i])) {
                collectInterestHigh(arrays[i], mHighInterestBefore);
                mHighIntGroup.add(mNameGroupInteres[i]);
            } else if (inGroup(mMiddle.getLowInterest(), mMiddle.getHighInterest(), arr[i])) {
                collectInterestMiddle(arrays[i], mMiddleInterestBefore);
                mMiddleIntGroup.add(mNameGroupInteres[i]);
            } else if (inGroup(mLow.getLowInterest(), mLow.getHighInterest(), arr[i])) {
                collectInterestLow(arrays[i], mLowInterestBefore);
                mLowIntGroup.add(mNameGroupInteres[i]);
            }
        }
        createHashMapIntGroup(HIGH_INT_KEY, mHighIntGroup);
        createHashMapIntGroup(MIDDLE_INT_KEY, mMiddleIntGroup);
        createHashMapIntGroup(LOW_INT_KEY, mLowIntGroup);
        return mHashInterest;
    }

    private void collectInterestHigh(int group, List<String> name) {
        mHighInterestBefore = mProfessionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == group).
                map(ProfessionalDefinition::getProfession).collect(Collectors.toList());
        if (mHighInterestBefore.size() != 0)
            mHighInterest.addAll(mHighInterestBefore);

    }

    private void createHashMapIntGroup(String key, List<String> name) {
        mHashInterest.put(key, name);
    }

    private void collectInterestMiddle(int group, List<String> name) {
       mMiddleInterestBefore = mProfessionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == group).
                map(ProfessionalDefinition::getProfession).collect(Collectors.toList());
        if (mMiddleInterestBefore.size() != 0)
            mMiddleInterest.addAll(mMiddleInterestBefore);

    }

    private void collectInterestLow(int group, List<String> name) {
        mLowInterestBefore = mProfessionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == group).
                map(ProfessionalDefinition::getProfession).collect(Collectors.toList());
        if (mLowInterestBefore.size() != 0)
            mLowInterest.addAll(mLowInterestBefore);

    }


    boolean inGroup(int low, int hi, int value) {
        return (low <= value && value <= hi);
    }
}
