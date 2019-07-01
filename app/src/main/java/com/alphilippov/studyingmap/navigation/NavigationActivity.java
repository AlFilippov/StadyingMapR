package com.alphilippov.studyingmap.navigation;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.alphilippov.studyingmap.R;
import com.alphilippov.studyingmap.fragments.FormCoursesKeyWords;
import com.alphilippov.studyingmap.fragments.ResultsAfterSearchKeyWords;
import com.alphilippov.studyingmap.fragments.VariableLearningCourses;
import com.alphilippov.studyingmap.fragments.ProfessionDefinition;
import com.alphilippov.studyingmap.fragments.SearchResultOfCourses;

import java.util.HashMap;

public class NavigationActivity extends AppCompatActivity implements VariableLearningCourses.OnChangedFragment, ProfessionDefinition.sentDataFragment, FormCoursesKeyWords.replaceFragment {
    private static final String YES_DECIDED = "ydecided";
    private static final String WANT_DEFENITION = "wdecided";
    private static final String YES = "YES";
    private static final String SEARCH_RESULT = "result";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                return true;
            case R.id.navigation_dashboard:
                return true;
            case R.id.navigation_notifications:
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
        VariableLearningCourses askMe = new VariableLearningCourses();
        ft.add(R.id.container, askMe);
        ft.commit();
    }

    @Override
    public void ChangedFragment(String s) {
        if (s.equals(YES_DECIDED)) {
            FormCoursesKeyWords formCoursesKeyWords = new FormCoursesKeyWords();
            getBackStackandReplace(formCoursesKeyWords);
        } else if (s.equals(WANT_DEFENITION)) {
            ProfessionDefinition professionDefinition = new ProfessionDefinition();
            getBackStackandReplace(professionDefinition);
        }
    }


    @Override
    public void onSentData(String d, HashMap hashMap) {
        if (d.equals(YES)) {
            Bundle bundle = new Bundle();
            SearchResultOfCourses searchResultOfCourses = new SearchResultOfCourses();
            getBackStackandReplace(searchResultOfCourses);
            bundle.putSerializable(SEARCH_RESULT, hashMap);
            searchResultOfCourses.setArguments(bundle);

        }
    }


    @Override
    public void sentDataInSearchInUdemy(String keyChangeFragment, String keyWords) {
        if (keyChangeFragment.equals("ok")) {
            ResultsAfterSearchKeyWords resultsAfterSearchKeyWords = new ResultsAfterSearchKeyWords();
            Bundle bundle = new Bundle();
            bundle.putString("ok", keyWords);
            resultsAfterSearchKeyWords.setArguments(bundle);
            getBackStackandReplace(resultsAfterSearchKeyWords);
        }
    }

    private void getBackStackandReplace(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
