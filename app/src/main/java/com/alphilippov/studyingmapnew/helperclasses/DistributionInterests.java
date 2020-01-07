package com.alphilippov.studyingmapnew.helperclasses;


public class DistributionInterests {
    /*
    private static final String HIGH_INT_KEY = "high";
    private static final String MIDDLE_INT_KEY = "middle";
    private static final String LOW_INT_KEY = "low";
    private List<ProfessionalDefinition> professionalDefinitions;
    private HashMap<String, List<String>> mHashInterest = new HashMap<>();
    private List<String> HighInterest = new ArrayList<>();
    private List<String> MiddleInterest = new ArrayList<>();
    private List<String> LowInterest = new ArrayList<>();
    private List<String> HighIntGroup = new ArrayList<>();
    private List<String> MiddleIntGroup = new ArrayList<>();
    private List<String> LowIntGroup = new ArrayList<>();
    private List<String> HighInterestBefore = new ArrayList<>();
    private List<String> MiddleInterestBefore = new ArrayList<>();
    private List<String> LowInterestBefore = new ArrayList<>();

    public DistributionInterests(List<ProfessionalDefinition> professionalDefinitions) {
        this.professionalDefinitions = professionalDefinitions;
    }

    public HashMap<String, List<String>> collectInteresGroups() {

        int realist = (int) professionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.REALIST).count();
        int intellectual = (int) professionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.INTELLECTUAL).count();
        int social = (int) professionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.SOCIAL).count();
        int office = (int) professionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.OFFICE).count();
        int entrepreneurial = (int) professionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.ENTREPRENEURIAL).count();
        int artistic = (int) professionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.ARTISTIC).count();
        HumanInterest mHigh = new HumanInterest(8, 10, "High");
        HumanInterest mMiddle = new HumanInterest(5, 7, "Middle");
        HumanInterest mLow = new HumanInterest(0, 4, "Low");
        int[] arr = {realist, intellectual, social, office, entrepreneurial, artistic};

        String[] mNameGroupInteres = {"realist", "intellectual", "social", "office", "entrepreneurial", "artistic"};
        int[] arrays = {1, 2, 3, 4, 5, 6};

        for (int i = 0; i <= arr.length - 1; i++) {
            if (inGroup(mHigh.getLowInterest(), mHigh.getHighInterest(), arr[i])) {
                collectInterestHigh(arrays[i], HighInterestBefore);
                HighIntGroup.add(mNameGroupInteres[i]);
            } else if (inGroup(mMiddle.getLowInterest(), mMiddle.getHighInterest(), arr[i])) {
                collectInterestMiddle(arrays[i], MiddleInterestBefore);
                MiddleIntGroup.add(mNameGroupInteres[i]);
            } else if (inGroup(mLow.getLowInterest(), mLow.getHighInterest(), arr[i])) {
                collectInterestLow(arrays[i], LowInterestBefore);
                LowIntGroup.add(mNameGroupInteres[i]);
            }
        }
        createHashMapIntGroup(HIGH_INT_KEY, HighIntGroup);
        createHashMapIntGroup(MIDDLE_INT_KEY, MiddleIntGroup);
        createHashMapIntGroup(LOW_INT_KEY, LowIntGroup);
        return mHashInterest;
    }

    public void collectInterestHigh(int group, List<String> name) {
        HighInterestBefore = professionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == group).
                map(ProfessionalDefinition::getProfession).collect(Collectors.toList());
        if (HighInterestBefore.size() != 0)
            HighInterest.addAll(HighInterestBefore);

    }

    private void createHashMapIntGroup(String key, List<String> name) {
        mHashInterest.put(key, name);
    }

    public void collectInterestMiddle(int group, List<String> name) {
        MiddleInterestBefore = professionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == group).
                map(ProfessionalDefinition::getProfession).collect(Collectors.toList());
        if (MiddleInterestBefore.size() != 0)
            MiddleInterest.addAll(MiddleInterestBefore);

    }

    public void collectInterestLow(int group, List<String> name) {
        LowInterestBefore = professionalDefinitions.stream().filter((p) -> p.getIdDefiniton() == group).
                map(ProfessionalDefinition::getProfession).collect(Collectors.toList());
        if (LowInterestBefore.size() != 0)
            LowInterest.addAll(LowInterestBefore);

    }


    boolean inGroup(int low, int hi, int value) {
        return (low <= value && value <= hi);
    }

     */
}
