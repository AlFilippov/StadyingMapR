package com.alphilippov.studyingmap.navigation;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
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
    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
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
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            FormCoursesKeyWords formCoursesKeyWords = new FormCoursesKeyWords();
            ft.replace(R.id.container, formCoursesKeyWords);
            ft.addToBackStack(null);
            ft.commit();
        } else if (s.equals(WANT_DEFENITION)) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ProfessionDefinition professionDefinition = new ProfessionDefinition();
            ft.replace(R.id.container, professionDefinition);
            ft.addToBackStack(null);
            ft.commit();
        }
    }


    @Override
    public void onSentData(String d, HashMap hashMap) {
        if (d.equals(YES)) {
            Bundle bundle = new Bundle();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            SearchResultOfCourses sR = new SearchResultOfCourses();
            bundle.putSerializable(SEARCH_RESULT, hashMap);
            sR.setArguments(bundle);
            ft.replace(R.id.container, sR);
            ft.addToBackStack(null);
            ft.commit();
        }
    }


    @Override
    public void sentDataInSearchInUdemy(String keyChangeFragment, String keyWords) {
        if (keyChangeFragment.equals("ok")) {
            Bundle bundle = new Bundle();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ResultsAfterSearchKeyWords resultsAfterSearchKeyWords = new ResultsAfterSearchKeyWords();
            bundle.putString("ok", keyWords);
            resultsAfterSearchKeyWords.setArguments(bundle);
            ft.replace(R.id.container, resultsAfterSearchKeyWords);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
