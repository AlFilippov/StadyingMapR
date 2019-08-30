package com.alphilippov.studyingmap.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.alphilippov.studyingmap.presenter.ProfessionDefinitionPresenter;
import com.alphilippov.studyingmap.utils.AppConfig;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class ProfessionDefinition extends Fragment {
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
    private Bundle savedState = null;
    private Parcelable mListState;

    @InjectPresenter
    ProfessionDefinitionPresenter professionDefinitionPresenter;
    @ProvidePresenter
    ProfessionDefinitionPresenter provideRepoPresenter(){
        // return context
    };
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeObjectProfession();
        loadDataAboutProfession(0);
        String pathdatabase = getContext().getDatabasePath("professiondata").getAbsolutePath();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

//    @Override
//    public void onResume() {
//        if(savedState!=null)
//        super.onResume();
//    }


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


//        if (savedInstanceState != null && savedState == null) {
//            savedState = savedInstanceState.getBundle("save");
//        }
//        if (savedState != null) {
//            //Иначе мы берем из ранее сохранненого состояния экземпляра onDestroyView
//           mCountQue.setText(savedState.getCharSequence("save"));
//
//
//        }

        mOnePartButton.setOnClickListener(view -> {

            if (QuestionCount == 1) {
                collectList(ProfessionOnePart, 0);
            }

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

            collectList(ProfessionTwoPart, QuestionCount);
            mOnePartButton.setText(setItemListOnePart(ProfessionOnePart, QuestionCount));
            mTwoPartButton.setText(setItemListTwoPart(ProfessionTwoPart, QuestionCount));
            QuestionCount++;

            countView(QuestionCount);
        });

        return binding.getRoot();
    }

//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        outState.putBundle("save", (savedState != null) ? savedState : savedState());
//        super.onSaveInstanceState(outState);
//    }
//    private Bundle savedState() {
//        Bundle state = new Bundle();
//        state.putCharSequence("save",mCountQue.getText());
//        return state;
//    }
//    @Override
//    public void onDestroyView() {
//        savedState = savedState();
//        super.onDestroyView();
//    }

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


    public List<ProfessionalDefinition> getProfessionThreePart() {
        return ProfessionThreePart;
    }

    public void collectList(List<ProfessionalDefinition> onePart, int i) {
        ProfessionThreePart.add(onePart.get(i));
        if (getProfessionThreePart().size() >= ProfessionOnePart.size()) {
            collectInteresGroups();
            replaceFragment();
        }

    }

    private void replaceFragment() {
        mSentDataFragment.onSentData(AppConfig.ChangeFragment.SEARCH_RESULT_OF_COURSES, mHashInterest);

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


        ProfessionOnePart.add(new ProfessionalDefinition(3, 30, "Physiotherapist"));
        ProfessionOnePart.add(new ProfessionalDefinition(5, 31, "Logistics specialist"));
        ProfessionOnePart.add(new ProfessionalDefinition(6, 32, "Cameraman"));
        ProfessionOnePart.add(new ProfessionalDefinition(3, 33, "Cashier"));
        ProfessionOnePart.add(new ProfessionalDefinition(5, 34, "Auto Sales Manager"));
        ProfessionOnePart.add(new ProfessionalDefinition(6, 35, "Web designer"));
        ProfessionOnePart.add(new ProfessionalDefinition(3, 36, "Ecologist"));
        ProfessionOnePart.add(new ProfessionalDefinition(5, 37, "Farmer"));
        ProfessionOnePart.add(new ProfessionalDefinition(6, 38, "SEO specialist "));
        ProfessionOnePart.add(new ProfessionalDefinition(3, 39, "Sanitary doctor"));
        ProfessionOnePart.add(new ProfessionalDefinition(5, 40, "Agricultural Product Provider"));
        ProfessionOnePart.add(new ProfessionalDefinition(6, 41, "Landscape designer"));
        ProfessionOnePart.add(new ProfessionalDefinition(3, 42, "Tutor"));
        ProfessionOnePart.add(new ProfessionalDefinition(5, 43, "Entrepreneur"));
        ProfessionOnePart.add(new ProfessionalDefinition(6, 44, "Artist-animator"));
        ProfessionOnePart.add(new ProfessionalDefinition(3, 45, "Doctor"));
        ProfessionOnePart.add(new ProfessionalDefinition(5, 46, "Trading agent"));
        ProfessionOnePart.add(new ProfessionalDefinition(6, 47, "Choreographer"));
        ProfessionOnePart.add(new ProfessionalDefinition(3, 48, "Journalist"));
        ProfessionOnePart.add(new ProfessionalDefinition(5, 49, "Producer"));
        ProfessionOnePart.add(new ProfessionalDefinition(6, 50, "Musician"));
        ProfessionOnePart.add(new ProfessionalDefinition(3, 51, "Guide"));
        ProfessionOnePart.add(new ProfessionalDefinition(5, 52, "Art Director"));
        ProfessionOnePart.add(new ProfessionalDefinition(6, 53, "Theater and film actor"));
        ProfessionOnePart.add(new ProfessionalDefinition(3, 54, "Guide-translator"));
        ProfessionOnePart.add(new ProfessionalDefinition(5, 55, "Crisis Manager"));
        ProfessionOnePart.add(new ProfessionalDefinition(6, 56, "Art editor"));
        ProfessionOnePart.add(new ProfessionalDefinition(3, 57, "Legal Counsel"));
        ProfessionOnePart.add(new ProfessionalDefinition(5, 58, "Broker"));
        ProfessionOnePart.add(new ProfessionalDefinition(6, 59, "Literary translator"));

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


    public void saveFile(JSONObject jsonObject, String nameFile) {

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Objects.requireNonNull(getContext()).
                    openFileOutput(nameFile, Context.MODE_PRIVATE));
            outputStreamWriter.write(jsonObject.toString());
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
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


