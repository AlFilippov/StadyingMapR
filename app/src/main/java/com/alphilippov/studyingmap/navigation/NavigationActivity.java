package com.alphilippov.studyingmap.navigation;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import com.alphilippov.studyingmap.R;
import com.alphilippov.studyingmap.fragments.FormCoursesKeyWords;
import com.alphilippov.studyingmap.fragments.ResultsAfterSearchKeyWords;
import com.alphilippov.studyingmap.fragments.VariantLearningCourses;
import com.alphilippov.studyingmap.fragments.ProfessionDefinition;
import com.alphilippov.studyingmap.fragments.SearchResultOfCourses;
import com.alphilippov.studyingmap.utils.AppConfig;

import java.util.HashMap;

public class NavigationActivity extends AppCompatActivity implements VariantLearningCourses.OnChangedFragment, ProfessionDefinition.sentDataFragment, FormCoursesKeyWords.replaceFragment {
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:

                return true;
            case R.id.navigation_search:

                return true;
            case R.id.navigation_favorites:
                VariantLearningCourses variantLearningCourses = new VariantLearningCourses();
                getBackStackandReplace(variantLearningCourses);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        VariantLearningCourses askMe = new VariantLearningCourses();
        ft.add(R.id.container, askMe);
        ft.commit();
    }

    @Override
    public void ChangedFragment(String s) {
        if (s.equals(AppConfig.ChangeFragment.FORM_COURSES_KEY_WORDS)) {
            FormCoursesKeyWords formCoursesKeyWords = new FormCoursesKeyWords();
            getBackStackandReplace(formCoursesKeyWords);
        } else if (s.equals(AppConfig.ChangeFragment.PROFESSION_DEFINITION)) {
            ProfessionDefinition professionDefinition = new ProfessionDefinition();
            getBackStackandReplace(professionDefinition);
        }
    }


    @Override
    public void onSentData(String d, HashMap hashMap) {
        if (d.equals(AppConfig.ChangeFragment.SEARCH_RESULT_OF_COURSES)) {
            Bundle bundle = new Bundle();
            SearchResultOfCourses searchResultOfCourses = new SearchResultOfCourses();
            bundle.putSerializable("result", hashMap);
            searchResultOfCourses.setArguments(bundle);
            getBackStackandReplace(searchResultOfCourses);

        }
    }


    @Override
    public void sentDataInSearchInUdemy(String keyChangeFragment, String keyWords) {
        if (keyChangeFragment.equals(AppConfig.ChangeFragment.RESULT_AFTER_SEARCH_KEY_WORDS)) {
            ResultsAfterSearchKeyWords resultsAfterSearchKeyWords = new ResultsAfterSearchKeyWords();
            Bundle bundle = new Bundle();
            bundle.putString("ok", keyWords);
            resultsAfterSearchKeyWords.setArguments(bundle);
            getBackStackandReplace(resultsAfterSearchKeyWords);
        }
    }

    private void getBackStackandReplace(Fragment fragmentAfter) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragmentAfter);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();

        super.onBackPressed();
    }
}
