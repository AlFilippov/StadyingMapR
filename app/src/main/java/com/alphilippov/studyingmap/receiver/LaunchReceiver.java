package com.alphilippov.studyingmap.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LaunchReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.intent.action.PACKAGE_FIRST_LAUNCH")){
            //TODO:Загрузить из assets файлы
        }
    }
}
