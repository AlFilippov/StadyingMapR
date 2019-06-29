package com.alphilippov.studyingmap.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alphilippov.studyingmap.R;
import com.alphilippov.studyingmap.databind.ProfessionBinding;
import com.alphilippov.studyingmap.databinding.ProfessionDefinitionBinding;
import com.alphilippov.studyingmap.helperclasses.HumanInterest;
import com.alphilippov.studyingmap.helperclasses.ProfessionalDefinition;
import com.alphilippov.studyingmap.network.RestService;
import com.alphilippov.studyingmap.network.dto.GetProfessionDTO;
import com.alphilippov.studyingmap.network.dto.ProfessionDataListDTO;
import com.alphilippov.studyingmap.network.dto.ProfessionOnePartDTO;
import com.alphilippov.studyingmap.network.dto.ProfessionTwoPartDTO;
import com.alphilippov.studyingmap.utils.AppConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class ProfessionDefinition extends Fragment {
    private static final String TAG = ProfessionDefinition.class.getName();
    private static final String HIGH_INT_KEY = "high";
    private static final String MIDDLE_INT_KEY = "middle";
    private static final String LOW_INT_KEY = "low";
    private List<ProfessionalDefinition> ProfessionOnePart = new ArrayList<>();
    private List<ProfessionalDefinition> ProfessionTwoPart = new ArrayList<>();
    private List<String> HighInterestBefore = new ArrayList<>();
    private List<String> MiddleInterestBefore = new ArrayList<>();
    private List<String> LowInterestBefore = new ArrayList<>();
    private List<ProfessionDataListDTO> professionDataLists = new ArrayList<>();
    private TextView mCountQue;
    protected HashMap<String, List<String>> mHashInterest = new HashMap<>();
    public List<ProfessionalDefinition> ProfessionThreePart = new ArrayList<>();
    public List<String> HighInterest = new ArrayList<>();
    public List<String> MiddleInterest = new ArrayList<>();
    public List<String> LowInterest = new ArrayList<>();
    public List<String> HighIntGroup = new ArrayList<>();
    public List<String> MiddleIntGroup = new ArrayList<>();
    public List<String> LowIntGroup = new ArrayList<>();
    public int QuestionCount = 1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeObjectProfession();
        loadDataAboutProfession(0);

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
        Button mOnePartButton = binding.OnePartButton;
        Button mTwoPartButton = binding.TwoPartButton;


        mOnePartButton.setOnClickListener(view -> {

            if (QuestionCount == 1) {
                collectList(ProfessionOnePart, 0);
            }
            //TODO:Реализовать загрузку данных
            collectList(ProfessionOnePart, QuestionCount);
            mOnePartButton.setText(setItemListOnePart(ProfessionOnePart, QuestionCount));
            mTwoPartButton.setText(setItemListTwoPart(ProfessionTwoPart, QuestionCount));
            QuestionCount++;
            countView(QuestionCount);

        });


        mTwoPartButton.setOnClickListener(view -> {
            if (QuestionCount == 1) {
                collectList(ProfessionTwoPart, 0);
            }
            //TODO:Поставить подгрузку данных

            collectList(ProfessionTwoPart, QuestionCount);
            mOnePartButton.setText(setItemListOnePart(ProfessionOnePart, QuestionCount));
            mTwoPartButton.setText(setItemListTwoPart(ProfessionTwoPart, QuestionCount));
            QuestionCount++;

            countView(QuestionCount);
        });

        return binding.getRoot();
    }


    public void loadDataAboutProfession(int index) {

        configurationRetrofit().getAllProfession(index).enqueue(new Callback<GetProfessionDTO>() {
            @Override
            public void onResponse(Call<GetProfessionDTO> call, Response<GetProfessionDTO> response) {
                professionDataLists.addAll(response.body().getProfessionDataLists());
            }

            @Override
            public void onFailure(Call<GetProfessionDTO> call, Throwable t) {

            }
        });
    }


    private void addPositionProfessionDefenition(int index, List<ProfessionOnePartDTO> onePartDTOList, List<ProfessionTwoPartDTO> twoPartDTOList) {

        ProfessionOnePart.add(new ProfessionalDefinition(onePartDTOList.get(index).getIdDefinition(),
                onePartDTOList.get(index).getIndexDefenition(),
                onePartDTOList.get(index).getProfession()));
        ProfessionTwoPart.add(new ProfessionalDefinition(twoPartDTOList.get(index).getIdDefinition(),
                twoPartDTOList.get(index).getIndexDefenition(),
                twoPartDTOList.get(index).getProfession()));

    }

    public List<ProfessionalDefinition> getProfessionThreePart() {
        return ProfessionThreePart;
    }

    public void collectList(List<ProfessionalDefinition> onePart, int i) {
        ProfessionThreePart.add(onePart.get(i));
        // Log.i(TAG, String.valueOf(ProfessionThreePart.size()));
//        for (ProfessionalDefinition professionalDefinition : ProfessionThreePart) {
//            System.out.println(professionalDefinition.getProfession());
//        }
        if (getProfessionThreePart().size() >= ProfessionOnePart.size()) {
            collectInteresGroups();
            replaceFragment();
        }

    }

    private void replaceFragment() {
        mSentDataFragment.onSentData("YES", mHashInterest);

    }

    private RestService configurationRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL_TWO)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return mRetrofit.create(RestService.class);
    }


    private void initializeObjectProfession() {
        ProfessionOnePart.add(new ProfessionalDefinition(1, 0, "Mechanic"));
        ProfessionOnePart.add(new ProfessionalDefinition(2, 1, "Information security specialist"));
        ProfessionOnePart.add(new ProfessionalDefinition(4, 2, "Call center operator"));
        ProfessionOnePart.add(new ProfessionalDefinition(1, 3, "Driver"));
        ProfessionOnePart.add(new ProfessionalDefinition(2, 4, "Design Engineer"));
        ProfessionOnePart.add(new ProfessionalDefinition(4, 5, "Air traffic controller"));
        ProfessionOnePart.add(new ProfessionalDefinition(1, 6, "Veterinarian"));
        ProfessionOnePart.add(new ProfessionalDefinition(2, 7, "Game developer "));
        ProfessionOnePart.add(new ProfessionalDefinition(4, 8, "Laboratory assistant"));
        ProfessionOnePart.add(new ProfessionalDefinition(1, 9, "Agronomist"));
        ProfessionOnePart.add(new ProfessionalDefinition(2, 10, "Breeder"));
        ProfessionOnePart.add(new ProfessionalDefinition(4, 11, "Marketer"));
        ProfessionOnePart.add(new ProfessionalDefinition(1, 12, "Masseur"));
        ProfessionOnePart.add(new ProfessionalDefinition(2, 13, "Teacher"));
        ProfessionOnePart.add(new ProfessionalDefinition(4, 14, "Facility manager"));
        ProfessionOnePart.add(new ProfessionalDefinition(1, 15, "Waiter"));
        ProfessionOnePart.add(new ProfessionalDefinition(2, 16, "Psychologist"));
        ProfessionOnePart.add(new ProfessionalDefinition(4, 17, "Insurance agent"));
        ProfessionOnePart.add(new ProfessionalDefinition(1, 18, "Jeweler"));
        ProfessionOnePart.add(new ProfessionalDefinition(2, 19, "Art Critic"));
        ProfessionOnePart.add(new ProfessionalDefinition(4, 20, "Editor"));
        ProfessionOnePart.add(new ProfessionalDefinition(1, 21, "Interior designer"));
        ProfessionOnePart.add(new ProfessionalDefinition(2, 22, "Software tester"));
        ProfessionOnePart.add(new ProfessionalDefinition(4, 23, "Copywriter"));
        ProfessionOnePart.add(new ProfessionalDefinition(2, 24, "System Administrator"));
        ProfessionOnePart.add(new ProfessionalDefinition(1, 25, "Carpenter"));
        ProfessionOnePart.add(new ProfessionalDefinition(4, 26, "Corrector"));
        ProfessionOnePart.add(new ProfessionalDefinition(1, 27, "Typewriter"));
        ProfessionOnePart.add(new ProfessionalDefinition(2, 28, "Programmer"));
        ProfessionOnePart.add(new ProfessionalDefinition(4, 29, "Accountant"));
        ProfessionOnePart.add(new ProfessionalDefinition(7, 29, "Accountant"));

        ProfessionTwoPart.add(new ProfessionalDefinition(3, 0, "Physiotherapist"));
        ProfessionTwoPart.add(new ProfessionalDefinition(5, 1, "Logistics specialist"));
        ProfessionTwoPart.add(new ProfessionalDefinition(6, 2, "Cameraman"));
        ProfessionTwoPart.add(new ProfessionalDefinition(3, 3, "Cashier"));
        ProfessionTwoPart.add(new ProfessionalDefinition(5, 4, "Auto Sales Manager"));
        ProfessionTwoPart.add(new ProfessionalDefinition(6, 5, "Web designer"));
        ProfessionTwoPart.add(new ProfessionalDefinition(3, 6, "Ecologist"));
        ProfessionTwoPart.add(new ProfessionalDefinition(5, 7, "Farmer"));
        ProfessionTwoPart.add(new ProfessionalDefinition(6, 8, "SEO specialist "));
        ProfessionTwoPart.add(new ProfessionalDefinition(3, 9, "Sanitary doctor"));
        ProfessionTwoPart.add(new ProfessionalDefinition(5, 10, "Agricultural Product Provider"));
        ProfessionTwoPart.add(new ProfessionalDefinition(6, 11, "Landscape designer"));
        ProfessionTwoPart.add(new ProfessionalDefinition(3, 12, "Tutor"));
        ProfessionTwoPart.add(new ProfessionalDefinition(5, 13, "Entrepreneur"));
        ProfessionTwoPart.add(new ProfessionalDefinition(6, 14, "Artist-animator"));
        ProfessionTwoPart.add(new ProfessionalDefinition(3, 15, "Doctor"));
        ProfessionTwoPart.add(new ProfessionalDefinition(5, 16, "Trading agent"));
        ProfessionTwoPart.add(new ProfessionalDefinition(6, 17, "Choreographer"));
        ProfessionTwoPart.add(new ProfessionalDefinition(3, 18, "Journalist"));
        ProfessionTwoPart.add(new ProfessionalDefinition(5, 19, "Producer"));
        ProfessionTwoPart.add(new ProfessionalDefinition(6, 20, "Musician"));
        ProfessionTwoPart.add(new ProfessionalDefinition(3, 21, "Guide"));
        ProfessionTwoPart.add(new ProfessionalDefinition(5, 22, "Art Director"));
        ProfessionTwoPart.add(new ProfessionalDefinition(6, 23, "Theater and film actor"));
        ProfessionTwoPart.add(new ProfessionalDefinition(3, 24, "Guide-translator"));
        ProfessionTwoPart.add(new ProfessionalDefinition(5, 25, "Crisis Manager"));
        ProfessionTwoPart.add(new ProfessionalDefinition(6, 26, "Art editor"));
        ProfessionTwoPart.add(new ProfessionalDefinition(3, 27, "Legal Counsel"));
        ProfessionTwoPart.add(new ProfessionalDefinition(5, 28, "Broker"));
        ProfessionTwoPart.add(new ProfessionalDefinition(6, 29, "Literary translator"));
        ProfessionTwoPart.add(new ProfessionalDefinition(7, 29, "Literary translator"));
    }

    private String setItemListOnePart(List<ProfessionalDefinition> professionOnePart, int questionCount) {

        return professionOnePart.get(questionCount).getProfession();

    }

    private String setItemListTwoPart(List<ProfessionalDefinition> professionTwoPart, int questionCount) {
        return professionTwoPart.get(questionCount).getProfession();

    }

    private void countView(int name) {
        String s = String.valueOf(name);
        mCountQue.setText(s);
    }

    private void collectInteresGroups() {
        int realist = (int) ProfessionThreePart.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.REALIST).count();
        int intellectual = (int) ProfessionThreePart.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.INTELLECTUAL).count();
        int social = (int) ProfessionThreePart.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.SOCIAL).count();
        int office = (int) ProfessionThreePart.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.OFFICE).count();
        int entrepreneurial = (int) ProfessionThreePart.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.ENTREPRENEURIAL).count();
        int artistic = (int) ProfessionThreePart.stream().filter((p) -> p.getIdDefiniton() == AppConfig.Group.ARTISTIC).count();
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
    }

    public void collectInterestHigh(int group, List<String> name) {
        HighInterestBefore = ProfessionThreePart.stream().filter((p) -> p.getIdDefiniton() == group).
                map(ProfessionalDefinition::getProfession).collect(Collectors.toList());
        if (HighInterestBefore.size() != 0)
            HighInterest.addAll(HighInterestBefore);

    }

    private void createHashMapIntGroup(String key, List<String> name) {
        mHashInterest.put(key, name);
    }

    public void collectInterestMiddle(int group, List<String> name) {
        MiddleInterestBefore = ProfessionThreePart.stream().filter((p) -> p.getIdDefiniton() == group).
                map(ProfessionalDefinition::getProfession).collect(Collectors.toList());
        if (MiddleInterestBefore.size() != 0)
            MiddleInterest.addAll(MiddleInterestBefore);

    }

    public void collectInterestLow(int group, List<String> name) {
        LowInterestBefore = ProfessionThreePart.stream().filter((p) -> p.getIdDefiniton() == group).
                map(ProfessionalDefinition::getProfession).collect(Collectors.toList());
        if (LowInterestBefore.size() != 0)
            LowInterest.addAll(LowInterestBefore);

    }


    boolean inGroup(int low, int hi, int value) {
        return (low <= value && value <= hi);
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


