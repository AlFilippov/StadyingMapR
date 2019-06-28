package com.alphilippov.studyingmap.navigation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alphilippov.studyingmap.R;
import com.alphilippov.studyingmap.fragments.AskMe;
import com.alphilippov.studyingmap.fragments.ChoiceOfProfession;
import com.alphilippov.studyingmap.fragments.ProfessionDefinition;
import com.alphilippov.studyingmap.fragments.SearchResultOfCourses;

import java.util.HashMap;

public class NavigationDrawer extends MainActivity
        implements NavigationView.OnNavigationItemSelectedListener, AskMe.OnChangedFragment , ProfessionDefinition.sentDataFragment {
    private static final String YES_DECIDED = "ydecided";
    private static final String WANT_DEFENITION = "wdecided";
    private static final String YES = "YES";
    private static final String SEARCH_RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        AskMe askMe = new AskMe();
        ft.add(R.id.container, askMe);
        ft.commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//TODO:Значок конверта - переделать в Избранное
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Тут будут ваши курсы", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
//TODO:Инициадизируется общий экран - подумать,что там может находится
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }
//Передача данных из Фрагментов
    @Override
    public void ChangedFragment(String s) {
        if (s.equals(YES_DECIDED)) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
           // ChoiceOfProfession ch = new ChoiceOfProfession();
            SearchResultOfCourses sr=new SearchResultOfCourses();
            ft.replace(R.id.container, sr);
            ft.addToBackStack(null);
            ft.commit();
        } else if (s.equals(WANT_DEFENITION)) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
           ProfessionDefinition professionDefinition = new ProfessionDefinition();
            ft.replace(R.id.container,professionDefinition);
            ft.addToBackStack(null);
            ft.commit();
        }
    }



    @Override
    public void onSentData(String d,HashMap hashMap) {
        if(d.equals(YES)){
            Bundle bundle = new Bundle();
FragmentManager fm = getSupportFragmentManager();
FragmentTransaction ft = fm.beginTransaction();
           SearchResultOfCourses sR = new SearchResultOfCourses();
           bundle.putSerializable(SEARCH_RESULT,hashMap);
           sR.setArguments(bundle);
            ft.replace(R.id.container,sR);
            ft.commit();
        }
    }



    //Проверяется открыта ли шторка , при нажатии кнопки назад

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // раздувает меню; это добавляет элементы в панель действий, если она присутствует.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Обрабатывает щелчки элементов панели действий здесь.

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //Обрабатывает навигацию по меню

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_news) {
        } else if (id == R.id.nav_lessons) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            AskMe askMe = new AskMe();
            ft.add(R.id.container, askMe);
            ft.commit();
        } else if (id == R.id.nav_tasks) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        item.setChecked(true);
        setTitle(item.getTitle());
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
