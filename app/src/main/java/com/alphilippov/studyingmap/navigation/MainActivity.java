package com.alphilippov.studyingmap.navigation;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alphilippov.studyingmap.R;
import com.alphilippov.studyingmap.utils.AppConfig;

import static com.alphilippov.studyingmap.utils.AppConfig.SharedPrefernce.APP_PREFERENCES_LOGIN;
import static com.alphilippov.studyingmap.utils.AppConfig.SharedPrefernce.APP_PREFERENCES_PASSWORD;


public class MainActivity extends AppCompatActivity {
    SharedPreferences mSettings;

    public static String sLoginUser;
    public static String sPasswordUser;

    //TODO:Накинуть на кнопку слушателя и отправить в БД
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText Login = findViewById(R.id.nameBox);
        EditText Password = findViewById(R.id.passBox);
        Button mRegistrationUser = findViewById(R.id.saveButton);


        mRegistrationUser.setOnClickListener(view -> {

//            String url = "http://192.168.1.39:5000/profession";
//            Intent i = new Intent();
//            i.setPackage("com.android.chrome");
//            i.setAction(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(url));
//            startActivity(i);

            Intent intent = new Intent(MainActivity.this, NavigationDrawer.class);
            startActivity(intent);
            Toast toast = Toast.makeText(this, "Успешно", Toast.LENGTH_SHORT);
            toast.show();
        });

    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }


}
